package com.ralitski.aether;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.ralitski.aether.gui.GuiGame;
import com.ralitski.aether.gui.GuiMainMenu;
import com.ralitski.util.gui.Gui;
import com.ralitski.util.gui.GuiManager;

public class AetherDisplay extends AetherDisplayParent implements WindowListener {
	
	private static AetherDisplay instance;
	
	public static AetherDisplay getInstance() {
		return instance;
	}
	
	//
	
	private Profiler profiler = new Profiler();
	
	public AetherDisplay() {
		super(1200, 600);
	}

    @Override
    public boolean update(boolean tick, float partial, float partialFromLast) {
		profiler.startTick();
    	boolean flag = super.update(tick, partial, partialFromLast);
		profiler.endTick();
		
		for(String s : profiler.getSections()) {
			String s2 = "tick" + s;
			System.out.println(s2 + " " + profiler.getTime(s));
		}
		//TODO: pass profiler to lag calculators and whatever
		
		return flag;
    }

	@Override
	protected Gui getMainMenu(GuiManager guiManager) {
		instance = this;
		setTitle("Aether");
		GuiMainMenu gui = new GuiMainMenu(guiManager, this);
		gui.init();
		return gui;
	}
	
	public void newGame(Gui gui) {
		GuiGame game = new GuiGame(gui, this, getGameContext());
		GameContext context = getGameContext();
		context.setup();
		this.guiManager.openScreen(game);
	}
	
	private GameContext getGameContext() {
		return new GameContextDefault();
	}
	
	public Profiler getProfiler() {
		return profiler;
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

	@Override
	public void windowOpened(WindowEvent e) {
		if(guiManager.getCurrentScreen() instanceof WindowListener) ((WindowListener)guiManager.getCurrentScreen()).windowOpened(e);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if(guiManager.getCurrentScreen() instanceof WindowListener) ((WindowListener)guiManager.getCurrentScreen()).windowClosing(e);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if(guiManager.getCurrentScreen() instanceof WindowListener) ((WindowListener)guiManager.getCurrentScreen()).windowClosed(e);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		if(guiManager.getCurrentScreen() instanceof WindowListener) ((WindowListener)guiManager.getCurrentScreen()).windowIconified(e);
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		if(guiManager.getCurrentScreen() instanceof WindowListener) ((WindowListener)guiManager.getCurrentScreen()).windowDeiconified(e);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		if(guiManager.getCurrentScreen() instanceof WindowListener) ((WindowListener)guiManager.getCurrentScreen()).windowActivated(e);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		if(guiManager.getCurrentScreen() instanceof WindowListener) ((WindowListener)guiManager.getCurrentScreen()).windowActivated(e);
	}

}
