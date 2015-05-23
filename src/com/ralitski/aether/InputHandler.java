package com.ralitski.aether;

import org.lwjgl.input.Keyboard;

import com.ralitski.util.input.ControllerUser;
import com.ralitski.util.input.InputUser;
import com.ralitski.util.input.event.ControllerEvent;
import com.ralitski.util.input.event.KeyEvent;
import com.ralitski.util.input.event.MouseEvent;
import com.ralitski.util.math.geom.d2.Vector2d;

public class InputHandler implements InputUser, ControllerUser {
	
	private static final float speed = 0.4F;
	
	//
	
	private AetherGame game;
	private AetherWorld world;
	
	public InputHandler(AetherGame game, AetherWorld world) {
		this.game = game;
		this.world = world;
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
