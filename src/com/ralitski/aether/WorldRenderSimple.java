package com.ralitski.aether;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import com.ralitski.util.Ticker;
import com.ralitski.util.math.geom.d2.BoundingBox2d;
import com.ralitski.util.math.geom.d2.Circle;
import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.math.geom.d2.Shape2d;
import com.ralitski.util.render.img.Color;
import com.ralitski.util.render.img.ColorRange;
import com.ralitski.util.render.img.ColorRangeSet;
import com.ralitski.util.render.img.GLImage;
import com.ralitski.util.render.img.GLTexture;
import com.ralitski.util.render.img.Image;
import com.ralitski.util.render.img.IterableColorSet;
import com.ralitski.util.render.list.GLListHelper;
import com.ralitski.util.render.list.TexturedCenteredSquareRenderListCW;

public class WorldRenderSimple implements WorldRender {
	
	private GLTexture circle;
	
	private Pastel colorIter;
	private IterableColorSet colors;
	
	public WorldRenderSimple() {
		TexturedCenteredSquareRenderListCW.FULL.compile();
		try {
			GLImage img = new GLImage(new Image(ImageIO.read(getClass().getResourceAsStream("/res/circle.png"))));
			img.glPrepare();
			circle = new GLTexture(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		colorIter = new Pastel();
		colors = new IterableColorSet(colorIter, 20);
//		colors.setTicker(Ticker.ticksPerSecond(0.05F));
		colors.setTicker(Ticker.ticksPerSecond(0.1F));
	}

	@Override
	public void renderBackground(BoundingBox2d box) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPushMatrix();
		GL11.glTranslatef(box.getMinX(), box.getMinY(), 0);
		GL11.glScalef(box.getWidth(), box.getHeight(), 1);
		nextBgColor().glColor();
		GLListHelper.getSquareListUncenteredCW().call();
		GL11.glPopMatrix();
	}
	
	private Color nextBgColor() {
		return colors.next();
	}

	@Override
	public void renderPlanet(Planet planet) {
		Body body = planet.getBody();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		circle.glBind();
		GL11.glPushMatrix();
		Point2d pos = body.getShape().getPosition();
		GL11.glTranslatef(pos.getX(), pos.getY(), 0);
		
		float scale = 1;
		Shape2d shape = body.getShape();
		if(shape instanceof Circle) {
			scale = ((Circle)shape).getRadius();
		}
		
		float scale2 = scale + 1F;
		
		GL11.glScalef(scale2, scale2, 1);
		Color.WHITE.glColor();
		TexturedCenteredSquareRenderListCW.FULL.call();
		scale2 = 1F / scale2;
		GL11.glScalef(scale2, scale2, 1);
		
		GL11.glScalef(scale, scale, 1);
		body.getColor().glColor();
		TexturedCenteredSquareRenderListCW.FULL.call();
		GL11.glPopMatrix();
	}

	@Override
	public void renderPlayer1(Player player) {
		renderPlayer(player);
	}

	@Override
	public void renderPlayer2(Player player) {
		renderPlayer(player);
	}
	
	private void renderPlayer(Player player) {
		Body body = player.getBody();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		circle.glBind();
		GL11.glPushMatrix();
		Point2d pos = body.getShape().getPosition();
		GL11.glTranslatef(pos.getX(), pos.getY(), 0);
		
		float scale = 1;
		Shape2d shape = body.getShape();
		if(shape instanceof Circle) {
			scale = ((Circle)shape).getRadius();
		}
		
		GL11.glScalef(scale, scale, 1);
		
		body.getColor().glColor();
		TexturedCenteredSquareRenderListCW.FULL.call();

		GL11.glScalef(1F / scale, 1F / scale, 1);
		scale = scale * 0.6F;
		GL11.glScalef(scale, scale, 1);
		
		body.getColor().inverse().glColor();
		TexturedCenteredSquareRenderListCW.FULL.call();
		
		
		GL11.glPopMatrix();
	}
	
	private class Pastel implements Iterator<Color> {

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public Color next() {
				Random random = new Random();
				int main = random.nextInt(3);
				int sub = random.nextInt(2);
				
				int c1 = 220 + random.nextInt(36); //220-255
				int c2 = 190 + random.nextInt(46); //190-235
				int c3 = 170 + random.nextInt(46); //170-215
				
				int r, g, b;
				if(main == 0) {
					r = c1;
					if(sub == 0) {
						g = c2;
						b = c3;
					} else {
						g = c3;
						b = c2;
					}
				} else if(main == 1) {
					g = c1;
					if(sub == 0) {
						r = c2;
						b = c3;
					} else {
						r = c3;
						b = c2;
					}
				} else {
					b = c1;
					if(sub == 0) {
						r = c2;
						g = c3;
					} else {
						r = c3;
						g = c2;
					}
				}
				return new Color(r, g, b);
		}

		@Override
		public void remove() {
		}
		
	}

}
