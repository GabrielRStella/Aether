package com.ralitski.aether;

import java.util.Random;

import com.ralitski.aether.force.ForceAttractive;
import com.ralitski.aether.force.ForceEmpty;
import com.ralitski.aether.force.ForceRedirect;
import com.ralitski.util.math.geom.d2.BoundingBox2d;
import com.ralitski.util.math.geom.d2.Circle;
import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.render.img.Color;

public class PlanetCreatorSimple implements PlanetCreator {

	@Override
	public Planet createPlanet(BoundingBox2d boundary, Random random) {
		Point2d position = new Point2d(boundary.getMinX() + random.nextFloat() * boundary.getWidth(),
				boundary.getMinY() + random.nextFloat() * boundary.getHeight());
		if(random.nextBoolean()) {
			position.setX(random.nextBoolean() ? boundary.getMinX() : boundary.getMaxX());
		} else {
			position.setY(random.nextBoolean() ? boundary.getMinY() : boundary.getMaxY());
		}
		Body body = new Body(Color.GREEN, new Circle(position, 1));
		Force force = getRandomForce(random);
		return new Planet(body, force);
	}

	@Override
	public Player createPlayer1() {
		Body body = new Body(Color.RED, new Circle(Point2d.origin(), 1));
		return new Player(body);
	}

	@Override
	public Player createPlayer2() {
		Body body = new Body(Color.BLUE, new Circle(Point2d.origin(), 1));
		return new Player(body);
	}
	
	private Force getRandomForce(Random random) {
//		return new ForceEmpty();
		return random.nextBoolean() ? new ForceEmpty() : (random.nextBoolean() ? new ForceRedirect(new ForceAttractive()) : new ForceRedirect(new ForceAttractive()).getOpposite());
	}

}
