package com.ralitski.aether;

import org.lwjgl.input.Keyboard;

import com.ralitski.aether.gui.GuiGame;
import com.ralitski.aether.input.InputBundle;
import com.ralitski.util.input.ControllerUser;
import com.ralitski.util.input.InputUser;
import com.ralitski.util.input.event.ControllerEvent;
import com.ralitski.util.input.event.KeyEvent;
import com.ralitski.util.input.event.MouseEvent;
import com.ralitski.util.input.event.KeyEvent.KeyEventType;
import com.ralitski.util.math.geom.d2.Vector2d;

public class InputHandler implements InputUser, ControllerUser {
	
	private static final float speed = 0.4F;
	
	//
	
	private GuiGame game;
	private AetherWorld world;
	
	private InputBundle[] input;
	
	public InputHandler(GuiGame game, AetherWorld world) {
		this.game = game;
		this.world = world;
		input = new InputBundle[game.getContext().getPlayerCount()];
	}
	
	public void setInput(int playerIndex, InputBundle bundle) {
		input[playerIndex] = bundle;
	}
	
	public void update(double timeStep) {
		GameContext context = game.getContext();
		float rot = context.getRotationDegrees();
		
		int count = input.length;
		Player[] players = world.getPlayers();
		for(int i = 0; i < count; i++) {
			if(input[i] != null) {
				Player player = players[i];
				Vector2d v = input[i].get();
				v.rotateDegrees(-rot);
				if(!v.isEmpty()) v.setMagnitude(speed);
				player.getBody().accelerate(v);
			}
		}
	}

	@Override
	public void onControllerEvent(ControllerEvent event) {
		//TODO: press start button to escape
		System.out.println(event);
	}

	@Override
	public void onMouseEvent(MouseEvent event) {
		//probably nothing to put here
	}

	@Override
	public void onKeyEvent(KeyEvent event) {
		if(event.getType() == KeyEventType.DOWN && event.getKey() == Keyboard.KEY_ESCAPE) {
			//open game menu
			game.pause();
		}
	}
	
}
