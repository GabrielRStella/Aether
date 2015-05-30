package com.ralitski.aether.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.ralitski.aether.AetherDisplay;
import com.ralitski.aether.AetherGame;
import com.ralitski.aether.GameContext;
import com.ralitski.util.Ticker;
import com.ralitski.util.gui.Gui;
import com.ralitski.util.input.ControllerMonitor;
import com.ralitski.util.input.event.KeyEvent;
import com.ralitski.util.input.event.MouseEvent;

public class GuiGame extends Gui implements WindowListener {
	
	private ControllerMonitor controller;
//	private GameContext context; //may be used...eventually...idk
	private AetherGame game;
	private Ticker ticker;

	public GuiGame(Gui parent, AetherDisplay display, GameContext context) {
		super(parent);
		display.setTitle("Aether (" + context.getTitle() + ")");
//		this.context = context;
		this.game = new AetherGame(this, display, context);
		controller = new ControllerMonitor(game);
		ticker = Ticker.ticksPerSecond(10);
		ticker.time();
	}
	
	public boolean renderParent() {
		return false;
	}
	
	public void render2d(float partial) {
		super.render2d(partial);
		game.render();
	}

	@Override
	public void onMouseEvent(MouseEvent event) {
		game.onMouseEvent(event);
	}

	@Override
	public void onKeyEvent(KeyEvent event) {
		game.onKeyEvent(event);
	}
	
	@Override
	public void update() {
		double d = ticker.time();
		boolean tick = getOwner().getCurrentScreen() == this;
		if(tick) controller.update();
		if(d != 0D) game.update(d, tick);
	}

    @Override
    public void windowOpened(WindowEvent event) {
        // TODO window created
    }

    @Override
    public void windowActivated(WindowEvent event) {
        // TODO window focused
    }

    @Override
    public void windowDeactivated(WindowEvent event) {
        // TODO window focus lost
    	game.pause();
    }

    @Override
    public void windowClosed(WindowEvent event) {
        // TODO window closed via call to dispose()
    }

    @Override
    public void windowClosing(WindowEvent event) {
        //red X button clicked
    }

    @Override
    public void windowDeiconified(WindowEvent event) {
        // TODO window selected from taskbar
    }

    @Override
    public void windowIconified(WindowEvent event) {
        // TODO window minimized to taskbar
    	game.pause();
    }

}
