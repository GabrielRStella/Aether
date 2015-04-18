package com.ralitski.aether.debug;

import java.util.Random;

import com.ralitski.aether.Body;
import com.ralitski.aether.Force;
import com.ralitski.aether.Planet;
import com.ralitski.aether.PlanetCreator;
import com.ralitski.aether.Player;
import com.ralitski.aether.force.ForceAccelerate;
import com.ralitski.aether.force.ForceAttractive;
import com.ralitski.aether.force.ForceEmpty;
import com.ralitski.aether.force.ForceRedirect;
import com.ralitski.aether.force.ForceTorque;
import com.ralitski.util.math.geom.d2.BoundingBox2d;
import com.ralitski.util.math.geom.d2.Circle;
import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.math.geom.d2.Vector2d;
import com.ralitski.util.render.img.Color;

public class DebugPlanetCreator implements PlanetCreator {

	@Override
	public float getPlanetDensity() {
		return 0.001F;
	}

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
		body.accelerate(new Vector2d(-5, 5));
		return new Player(body);
	}

	@Override
	public Player createPlayer2() {
		Body body = new Body(Color.BLUE, new Circle(Point2d.origin(), 1));
		body.accelerate(new Vector2d(5, -5));
		return new Player(body);
	}
	
	private Force getRandomForce(Random random) {
		Force force = forces[random.nextInt(forces.length)];
		return random.nextBoolean() ? force : force.getOpposite();
	}
	
	private Force[] forces = new Force[]{
			new ForceRedirect(new ForceAttractive()),
			ForceEmpty.INSTANCE,
			new ForceRedirect(new ForceTorque()),
			new ForceAccelerate()
	};

	@Override
	public float getBoxScale() {
		// TODO Auto-generated method stub
		return 1.3F;
	}

	@Override
	public boolean prune(Planet planet, BoundingBox2d viewbox, BoundingBox2d check) {
		return !check.contains(planet.getBody().getPosition());
	}
//	if(!check.contains(x, y) && !checkDist(p, playerPlanet1.getBody().getPosition()) && !checkDist(p, playerPlanet2.getBody().getPosition())) {
//	planets.remove();
//}
	
//	private boolean checkDist(Point2d point, Point2d check) {
//		return point.length(check) < DISTANCE_PLAYER;
//	}

}
