package com.ralitski.aether;

import com.ralitski.util.gui.Gui;
import com.ralitski.util.gui.GuiManager;
import com.ralitski.util.gui.GuiOwnerGL;
import com.ralitski.util.gui.render.FontRenderer;
import com.ralitski.util.gui.render.ImageFontRenderer;

public class AetherDisplay extends GuiOwnerGL {
	
	private FontRenderer fontRenderer = new ImageFontRenderer(this);

	@Override
	public void onTopLevelGuiClose() {
		close();
	}

	@Override
	public FontRenderer getFontRenderer() {
		return fontRenderer;
	}

	@Override
	public void render3dUntransformed(float partial) {
	}

	@Override
	public void render3dRotated(float partial) {
	}

	@Override
	public void close() {
	}

	@Override
	public void getError(int source, Throwable e) {
		e.printStackTrace();
	}

	@Override
	protected Gui getMainMenu(GuiManager guiManager) {
		Gui gui = new Gui(guiManager);
		//TODO: add menu stuff
		return gui;
	}

}
