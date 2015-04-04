package com.ralitski.aether;

import java.util.Random;

import com.ralitski.aether.force.ForceEmpty;
import com.ralitski.util.gui.Box;
import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.render.img.Color;

public class PlanetCreatorSimple implements PlanetCreator {

	@Override
	public Planet createPlanet(Box boundary, Random random) {
		Point2d position = new Point2d(boundary.getMinX() + random.nextFloat() * boundary.getWidth(),
				boundary.getMinY() + random.nextFloat() * boundary.getHeight());
		Body body = new Body(Color.GRAY, position, 1);
		Force force = new ForceEmpty();
		return new Planet(body, force);
	}

}
