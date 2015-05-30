package com.ralitski.aether;

import org.lwjgl.opengl.GL11;

import com.ralitski.util.gui.Box;
import com.ralitski.util.gui.Component;
import com.ralitski.util.gui.Gui;
import com.ralitski.util.gui.GuiManager;
import com.ralitski.util.gui.GuiOwner;
import com.ralitski.util.gui.render.FontRenderer;
import com.ralitski.util.gui.render.ImageFontRenderer;
import com.ralitski.util.gui.render.RenderList;
import com.ralitski.util.gui.render.RenderStyle;
import com.ralitski.util.input.InputMonitor;
import com.ralitski.util.render.DepthFunc;
import com.ralitski.util.render.RenderManagerUserAbstract;
import com.ralitski.util.render.camera.Camera;
import com.ralitski.util.render.img.Color;
import com.ralitski.util.render.img.GLImage;
import com.ralitski.util.render.img.Image;
import com.ralitski.util.render.list.GLListHelper;
import com.ralitski.util.render.list.RunnableListMaker;
import com.ralitski.util.render.list.TexturedUncenteredSquareRenderListCCW;
import com.ralitski.util.render.list.TexturedUncenteredSquareRenderListCW;

/**
 * A copy of the GuiOwnerGL class using a separate input API.
 * @author ralitski
 *
 */
public abstract class AetherDisplayParent extends RenderManagerUserAbstract implements GuiOwner {
	
	private FontRenderer fontRenderer;
	private InputMonitor input;
	
	protected GuiManager guiManager;
	private boolean flipTextures = true;
	private boolean isCW = true;

    public AetherDisplayParent(int width, int height) {
    	super(width, height, false);
    }
	
	public void setup() {
		super.setup();
		renderManager.setEnableCull(false);
		renderManager.setDepthFunc(DepthFunc.GL_LEQUAL);
		renderManager.setBlendAlpha();
		fontRenderer = new ImageFontRenderer(this);
		guiManager = new GuiManager(this);
		guiManager.openScreen(getMainMenu(guiManager));
		input = new InputMonitor(guiManager);
		if(isCW) TexturedUncenteredSquareRenderListCW.FULL.compile();
		else TexturedUncenteredSquareRenderListCCW.FULL.compile();
	}
	
	@Override
	public void updateTick() {
		input.update();
		guiManager.update();
	}
	
	protected abstract Gui getMainMenu(GuiManager guiManager);
	
	/*
	 * RenderManagerUser methods and get/set
	 */

	@Override
	public void render3dTransformed(float partial) {
		guiManager.render3d(partial);
	}

	@Override
	public void render2d(float partial) {
		guiManager.render2d(partial);
	}

	@Override
	public void render3dUntransformed(float partial) {
	}

	@Override
	public void render3dRotated(float partial) {
	}

	@Override
	public Camera getCamera() {
		return null;
	}

	public boolean isFlipTextures() {
		return flipTextures;
	}

	public void setFlipTextures(boolean flipTextures) {
		this.flipTextures = flipTextures;
	}

	public boolean isCW() {
		return isCW;
	}

	public void setCW(boolean isCW) {
		this.isCW = isCW;
	}
	
	/*
	 * GuiOwner rendering methods
	 */

	@Override
	public void drawBox(Box box, Color c) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		c.glColor();
		GL11.glPushMatrix();
		GL11.glTranslatef(box.getMinX(), box.getMinY(), 0);
		GL11.glScalef(box.getWidth(), box.getHeight(), 1);
		if(isCW) GLListHelper.getSquareListUncenteredCW().call();
		else GLListHelper.getSquareListUncenteredCCW().call();
		GL11.glPopMatrix();
	}

	@Override
	public void drawBox(Box box, Component c, RenderStyle style) {
		boolean useImage = false;
		if(style == null) {
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glColor4f(1, 1, 1, 1);
		} else {
			if(style.getStyle(c, "shadow") == Boolean.TRUE) {

				GL11.glPushMatrix();
				Integer off = style.getStyle(c, "shadow-offset");
				GL11.glTranslatef(off, -off, 0);
				RenderStyle shadowStyle = style.getStyle(c, "shadow-style");
				drawBox(box, c, shadowStyle);
				GL11.glPopMatrix();
			}
			drawBorder(box, c, style);
			Image image = (Image)style.getStyle(c, "image");
			Color color = (Color)style.getStyle(c, "color");
			if(color == null) {
				GL11.glColor4f(1, 1, 1, 1);
			} else color.glColor();
			if(image == null) {
				GL11.glDisable(GL11.GL_TEXTURE_2D);
			} else {
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GLImage glImage = new GLImage(image);
				glImage.glPrepare();
				glImage.glBind();
				useImage = true;
			}
		}
		GL11.glPushMatrix();
		GL11.glTranslatef(box.getMinX(), box.getMinY(), 0);
		GL11.glScalef(box.getWidth(), box.getHeight(), 1);
		
		if(useImage) {
			if(isCW) TexturedUncenteredSquareRenderListCW.FULL.call();
			else TexturedUncenteredSquareRenderListCCW.FULL.call();
		} else {
			if(isCW) GLListHelper.getSquareListUncenteredCW().call();
			else GLListHelper.getSquareListUncenteredCCW().call();
		}
		
		GL11.glPopMatrix();
	}

	@Override
	public void drawImage(Image image, Box box, Component c, RenderStyle style) {
		if(style == null) {
			GL11.glColor4f(1, 1, 1, 1);
		} else {
			if(style.getStyle(c, "shadow") == Boolean.TRUE) {
				GL11.glPushMatrix();
				Integer off = style.getStyle(c, "shadow-offset");
				GL11.glTranslatef(off, -off, 0);
				RenderStyle shadowStyle = style.getStyle(c, "shadow-style");
				drawImage(image, box, c, shadowStyle);
				GL11.glPopMatrix();
			}
			drawBorder(box, c, style);
			//creating a new GLImage each time builds up massive lag, so we cache it
			Color color = (Color)style.getStyle(c, "color");
			if(color == null) {
				GL11.glColor4f(1, 1, 1, 1);
			} else color.glColor();
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GLImage glImage = new GLImage(image);
		glImage.glPrepare();
		glImage.glBind();
		GL11.glPushMatrix();
		if(flipTextures) {
			GL11.glTranslatef(box.getMinX(), box.getMinY() + box.getHeight(), 0);
			GL11.glScalef(box.getWidth(), -box.getHeight(), 1);
		} else {
			GL11.glTranslatef(box.getMinX(), box.getMinY(), 0);
			GL11.glScalef(box.getWidth(), box.getHeight(), 1);
		}
		if(isCW) TexturedUncenteredSquareRenderListCW.FULL.call();
		else TexturedUncenteredSquareRenderListCCW.FULL.call();
		GL11.glPopMatrix();
	}
	
	private void drawBorder(Box box, Component c, RenderStyle style) {
		if(style.getStyle(c, "border") == Boolean.TRUE) {
			Integer i = style.getStyle(c, "border-width");
			if(i != null) {
				int width = i.intValue();
				Color bColor = style.getStyle(c, "border-color");
				if(bColor != null) {
					//draw border
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					bColor.glColor();
					
					//left border
					GL11.glPushMatrix();
					GL11.glTranslatef(box.getMinX() - width, box.getMinY() - width, 0);
					GL11.glScalef(width, box.getHeight() + width * 2, 1);
					if(isCW) GLListHelper.getSquareListUncenteredCW().call();
					else GLListHelper.getSquareListUncenteredCCW().call();
					GL11.glPopMatrix();
					
					//right border
					GL11.glPushMatrix();
					GL11.glTranslatef(box.getMaxX(), box.getMinY() - width, 0);
					GL11.glScalef(width, box.getHeight() + width * 2, 1);
					if(isCW) GLListHelper.getSquareListUncenteredCW().call();
					else GLListHelper.getSquareListUncenteredCCW().call();
					GL11.glPopMatrix();
					
					//top border
					GL11.glPushMatrix();
					GL11.glTranslatef(box.getMinX() - width, box.getMinY() - width, 0);
					GL11.glScalef(box.getWidth() + width * 2, width, 1);
					if(isCW) GLListHelper.getSquareListUncenteredCW().call();
					else GLListHelper.getSquareListUncenteredCCW().call();
					GL11.glPopMatrix();
					
					//bottom border
					GL11.glPushMatrix();
					GL11.glTranslatef(box.getMinX() - width, box.getMaxY(), 0);
					GL11.glScalef(box.getWidth() + width * 2, width, 1);
					if(isCW) GLListHelper.getSquareListUncenteredCW().call();
					else GLListHelper.getSquareListUncenteredCCW().call();
					GL11.glPopMatrix();
				}
			}
		}
	}

	@Override
	public boolean supportLists() {
		return true;
	}

	@Override
	public RenderList newList(Runnable renderer) {
		return new RunnableListMaker(renderer);
	}

	@Override
	public FontRenderer getFontRenderer() {
		return fontRenderer;
	}

}
