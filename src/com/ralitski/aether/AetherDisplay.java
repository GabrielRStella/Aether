package com.ralitski.aether;

import com.ralitski.util.gui.Gui;
import com.ralitski.util.gui.GuiManager;

public class AetherDisplay extends AetherDisplayParent {

	@Override
	protected Gui getMainMenu(GuiManager guiManager) {
		setTitle("Aether");
		GuiMainMenu gui = new GuiMainMenu(guiManager, this);
		gui.init();
		//TODO: add menu stuff
		return gui;
	}
	
	public void newGame(Gui gui) {
		GuiGame game = new GuiGame(gui, this, getGameContext());
		GameContext context = getGameContext();
		context.setup();
		setTitle("Aether - " + context.getTitle());
		this.guiManager.openScreen(game);
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
	
	public void resize() {
		super.resize();
		guiManager.getCurrentScreen().onResize();
	}

}
