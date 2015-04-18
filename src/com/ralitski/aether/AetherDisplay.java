package com.ralitski.aether;

import com.ralitski.util.gui.Gui;
import com.ralitski.util.gui.GuiManager;

public class AetherDisplay extends AetherDisplayParent {

	@Override
	protected Gui getMainMenu(GuiManager guiManager) {
		setTitle("Aether");
		GameContext context = getGameContext();
		context.setup();
		setTitle(context.getTitle());
		Gui gui = new Gui(guiManager);
		GuiGame game = new GuiGame(gui, this, context);
		//TODO: add menu stuff
		return game;
	}
	
	private GameContext getGameContext() {
		return new GameContextDefault();
	}

	@Override
	public void onTopLevelGuiClose() {
		stop();
	}

	@Override
	public void close() {
	}

	@Override
	public void getError(int source, Throwable e) {
		e.printStackTrace();
	}

}
