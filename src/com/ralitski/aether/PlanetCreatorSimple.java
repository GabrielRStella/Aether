package com.ralitski.aether;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ralitski.aether.force.ForceEmpty;
import com.ralitski.aether.force.ForceRedirect;
import com.ralitski.aether.force.ForceSimple;
import com.ralitski.util.Generator;
import com.ralitski.util.math.geom.d2.BoundingBox2d;
import com.ralitski.util.math.geom.d2.Circle;
import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.math.geom.d2.Vector2d;
import com.ralitski.util.render.img.Color;
import com.ralitski.util.render.img.ColorRange;

public class PlanetCreatorSimple implements PlanetCreator {
	
	private List<Type> types = new ArrayList<>();
	
	public void addType(Color c1, Color c2, ForceSimple force) {
		addType(new ColorRange(c1, c2), force);
	}
	
	public void addType(Color c1, Color c2, Force force) {
		addType(new ColorRange(c1, c2), force);
	}
	
	public void addType(Color c1, Color c2, Generator<Force> force) {
		addType(new ColorRange(c1, c2), force);
	}
	
	public void addType(ColorRange range, ForceSimple force) {
		addType(range, new ForceGenerator(new ForceRedirect(force)));
	}
	
	public void addType(ColorRange range, Force force) {
		addType(range, new ForceGenerator(force));
	}
	
	public void addType(ColorRange range, Generator<Force> force) {
		types.add(new Type(range, force));
	}

	@Override
	public float getPlanetDensity() {
		return 0.0005F;
	}

	@Override
	public Player createPlayer1() {
		Body body = new Body(Color.WHITE, new Circle(Point2d.origin(), 3));
		body.accelerate(new Vector2d(-5, 5));
		return new Player(body);
	}

	@Override
	public Player createPlayer2() {
		Body body = new Body(Color.BLACK, new Circle(Point2d.origin(), 3));
		body.accelerate(new Vector2d(5, -5));
		return new Player(body);
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
		position.rotate(boundary.getAngleDegrees());
		
		float size = random.nextFloat() * 3F + 1F;
		
		if(!types.isEmpty()) {
			int index = random.nextInt(types.size());
			Type type = types.get(index);
			ColorRange range = type.range;
			Color color = random.nextBoolean() ? range.createSmooth(random) : range.createRough(random);
			Body body = new Body(color, new Circle(position, size));
			Force force = type.generator.next();
			int minDif = Color.difference(range.getMin(), color);
			int maxDif = Color.difference(range.getMax(), color);
			if(maxDif < minDif) force = force.getOpposite();
			return new Planet(body, force);
		} else {
			Body body = new Body(Color.GREEN, new Circle(position, size));
			return new Planet(body, ForceEmpty.INSTANCE);
		}
	}

	@Override
	public float getBoxScale() {
		return 1.3F;
	}

	@Override
	public boolean prune(Planet planet, BoundingBox2d viewbox, BoundingBox2d check) {
		return !check.contains(planet.getBody().getPosition());
	}
	
	private class ForceGenerator implements Generator<Force> {
		
		private Force force;
		
		ForceGenerator(Force force) {
			this.force = force;
		}

		@Override
		public Force next() {
			return force;
		}
		
	}
	
	private class Type {
		ColorRange range;
		Generator<Force> generator;
		
		Type(ColorRange range, Generator<Force> generator) {
			this.range = range;
			this.generator = generator;
		}
	}

}
