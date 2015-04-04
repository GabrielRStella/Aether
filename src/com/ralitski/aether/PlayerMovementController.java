package com.ralitski.aether;

import com.ralitski.util.math.geom.d2.Vector2d;

public interface PlayerMovementController {
	void acceleratePlayer1(Vector2d accel);
	void acceleratePlayer2(Vector2d accel);
}
