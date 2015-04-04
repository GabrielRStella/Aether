package com.ralitski.aether;

import java.util.Random;

import com.ralitski.util.gui.Box;

public interface PlanetCreator {
	Planet createPlanet(Box boundary, Random random);
}
