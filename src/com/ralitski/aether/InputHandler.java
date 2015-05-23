package com.ralitski.aether;

import org.lwjgl.input.Keyboard;

import com.ralitski.util.input.ControllerUser;
import com.ralitski.util.input.InputUser;
import com.ralitski.util.input.event.ControllerEvent;
import com.ralitski.util.input.event.KeyEvent;
import com.ralitski.util.input.event.MouseEvent;
import com.ralitski.util.math.geom.d2.Vector2d;

public class InputHandler implements InputUser, ControllerUser {

	private static final int KEY_LEFT_1 = Keyboard.KEY_A;
	private static final int KEY_RIGHT_1 = Keyboard.KEY_D;
	private static final int KEY_UP_1 = Keyboard.KEY_W;
	private static final int KEY_DOWN_1 = Keyboard.KEY_S;
	
	private static final int KEY_LEFT_2 = Keyboard.KEY_LEFT;
	private static final int KEY_RIGHT_2 = Keyboard.KEY_RIGHT;
	private static final int KEY_UP_2 = Keyboard.KEY_UP;
	private static final int KEY_DOWN_2 = Keyboard.KEY_DOWN;
	
	private static final float speed = 0.4F;
	
	//
	
	private AetherGame game;
	private Player player1;
	private Player player2;
	
	public InputHandler(AetherGame game, AetherWorld world) {
		this(game, world.getPlayer1(), world.getPlayer2());
	}
	
	public InputHandler(AetherGame game, Player player1, Player player2) {
		this.game = game;
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void update(double timeStep) {
		//TODO: dont use input events, check per tick
		GameContext context = game.getContext();
		float rot = context.getRotationDegrees();
		Vector2d accel1 = new Vector2d();
		if(Keyboard.isKeyDown(KEY_UP_1)) {
			accel1.addY(speed);
		}
		if(Keyboard.isKeyDown(KEY_DOWN_1)) {
			accel1.addY(-speed);
		}
		if(Keyboard.isKeyDown(KEY_LEFT_1)) {
			accel1.addX(-speed);
		}
		if(Keyboard.isKeyDown(KEY_RIGHT_1)) {
			accel1.addX(speed);
		}
		accel1.rotateDegrees(-rot);
		if(!accel1.isEmpty()) accel1.setMagnitude(speed);
		player1.getBody().accelerate(accel1);
		Vector2d accel2 = new Vector2d();
		if(Keyboard.isKeyDown(KEY_UP_2)) {
			accel2.addY(speed);
		}
		if(Keyboard.isKeyDown(KEY_DOWN_2)) {
			accel2.addY(-speed);
		}
		if(Keyboard.isKeyDown(KEY_LEFT_2)) {
			accel2.addX(-speed);
		}
		if(Keyboard.isKeyDown(KEY_RIGHT_2)) {
			accel2.addX(speed);
		}
		accel2.rotateDegrees(-rot);
		if(!accel2.isEmpty()) accel2.setMagnitude(speed);
		player2.getBody().accelerate(accel2);
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
		//TODO
	}
	
}
