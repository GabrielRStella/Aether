package com.ralitski.aether;

import java.util.Random;

import com.ralitski.util.gui.Box;

public interface PlanetCreator {
	Player createPlayer1();
	Player createPlayer2();
	Planet createPlanet(Box boundary, Random random);
}
