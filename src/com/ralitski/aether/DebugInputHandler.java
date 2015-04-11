package com.ralitski.aether;

import org.lwjgl.input.Keyboard;

import com.ralitski.util.input.event.ControllerAxisEvent;
import com.ralitski.util.input.event.ControllerAxisEvent.ControllerAxisType;
import com.ralitski.util.input.event.ControllerEvent;
import com.ralitski.util.input.event.KeyEvent;
import com.ralitski.util.input.event.MouseEvent;
import com.ralitski.util.input.event.KeyEvent.KeyEventType;
import com.ralitski.util.math.geom.d2.Vector2d;

public class DebugInputHandler implements InputHandler {

	private static final int KEY_LEFT_1 = Keyboard.KEY_A;
	private static final int KEY_RIGHT_1 = Keyboard.KEY_D;
	private static final int KEY_UP_1 = Keyboard.KEY_W;
	private static final int KEY_DOWN_1 = Keyboard.KEY_S;
	
	private static final int KEY_LEFT_2 = Keyboard.KEY_LEFT;
	private static final int KEY_RIGHT_2 = Keyboard.KEY_RIGHT;
	private static final int KEY_UP_2 = Keyboard.KEY_UP;
	private static final int KEY_DOWN_2 = Keyboard.KEY_DOWN;
	
	private static final float speed2 = 2F;
	private static final Vector2d UP2 = new Vector2d(0, speed2);
	private static final Vector2d DOWN2 = new Vector2d(0, -speed2);
	private static final Vector2d LEFT2 = new Vector2d(-speed2, 0);
	private static final Vector2d RIGHT2 = new Vector2d(speed2, 0);
	
	//
	
	private Player player1;
	private Player player2;
	
	public DebugInputHandler(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void update() {
		//just in case...
	}

	@Override
	public void onControllerEvent(ControllerEvent event) {
		//TODO
		System.out.println(event);
		if(event instanceof ControllerAxisEvent) {
			ControllerAxisEvent aEvent = (ControllerAxisEvent)event;
			ControllerAxisType type = aEvent.getType();
			System.out.println(aEvent.getValue());
			switch(type) {
			case AXIS_X:
			case AXIS_Y:
			case POV_X:
			case POV_Y:
			}
		}
	}

	@Override
	public void onMouseEvent(MouseEvent event) {
		//probably nothing to put here
	}

	@Override
	public void onKeyEvent(KeyEvent event) {
		//TODO
		if(event.getType() == KeyEventType.DOWN) {
			//burst of speed?
			keyDown(event.getKey());
		} else if(event.getType() == KeyEventType.HOLD) {
			//continued acceleration
			keyDown(event.getKey());
		}
	}
	
	private void keyDown(int key) {
		switch(key) {
		case KEY_UP_1:
			player1.getBody().accelerate(UP2);
			break;
		case KEY_UP_2:
			player2.getBody().accelerate(UP2);
			break;
		case KEY_DOWN_1:
			player1.getBody().accelerate(DOWN2);
			break;
		case KEY_DOWN_2:
			player2.getBody().accelerate(DOWN2);
			break;
		case KEY_LEFT_1:
			player1.getBody().accelerate(LEFT2);
			break;
		case KEY_LEFT_2:
			player2.getBody().accelerate(LEFT2);
			break;
		case KEY_RIGHT_1:
			player1.getBody().accelerate(RIGHT2);
			break;
		case KEY_RIGHT_2:
			player2.getBody().accelerate(RIGHT2);
			break;
		}
	}

//	switch(key) {
//	case KEY_UP_1:
//		move.acceleratePlayer1(UP);
//		break;
//	case KEY_UP_2:
//		move.acceleratePlayer2(UP);
//		break;
//	case KEY_DOWN_1:
//		move.acceleratePlayer1(DOWN);
//		break;
//	case KEY_DOWN_2:
//		move.acceleratePlayer2(DOWN);
//		break;
//	case KEY_LEFT_1:
//		move.acceleratePlayer1(LEFT);
//		break;
//	case KEY_LEFT_2:
//		move.acceleratePlayer2(LEFT);
//		break;
//	case KEY_RIGHT_1:
//		move.acceleratePlayer1(RIGHT);
//		break;
//	case KEY_RIGHT_2:
//		move.acceleratePlayer2(RIGHT);
//		break;
//	}
	
}
