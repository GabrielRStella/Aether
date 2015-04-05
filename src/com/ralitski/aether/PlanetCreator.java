package com.ralitski.aether;

import java.util.Random;

import com.ralitski.util.math.geom.d2.BoundingBox2d;

public interface PlanetCreator {
	Player createPlayer1();
	Player createPlayer2();
	Planet createPlanet(BoundingBox2d check, Random random);
}
