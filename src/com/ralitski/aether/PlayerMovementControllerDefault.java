package com.ralitski.aether;

import com.ralitski.util.math.geom.d2.Vector2d;

public class PlayerMovementControllerDefault implements PlayerMovementController {
	
	private Player player1;
	private Player player2;

	public PlayerMovementControllerDefault(Player playerPlanet1, Player playerPlanet2) {
		this.player1 = playerPlanet1;
		this.player2 = playerPlanet2;
	}

	@Override
	public void acceleratePlayer1(Vector2d accel) {
		player1.getBody().accelerate(accel);
	}

	@Override
	public void acceleratePlayer2(Vector2d accel) {
		player2.getBody().accelerate(accel);
	}

}
