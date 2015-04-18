package com.ralitski.aether;

import java.util.Random;

import com.ralitski.util.math.geom.d2.BoundingBox2d;

public interface PlanetCreator {
	Player createPlayer1();
	Player createPlayer2();
	Planet createPlanet(BoundingBox2d check, Random random);
	float getPlanetDensity();
	/**
	 * Return true if the planet should be pruned
	 * @param planet
	 * @param viewbox
	 * @return
	 */
	boolean prune(Planet planet, BoundingBox2d viewbox, BoundingBox2d check);
	/**
	 * The ViewBox scale to be applied to the "check" BoundingBox2d passed to prune()
	 * @return
	 */
	float getBoxScale();
}
