package com.ralitski.aether;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.ralitski.util.Generator;
import com.ralitski.util.math.geom.d2.BoundingBox2d;
import com.ralitski.util.math.geom.d2.Circle;
import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.math.geom.d2.Vector2d;
import com.ralitski.util.render.img.Color;

public class PlanetCreatorSimple implements PlanetCreator {
	
	private Set<Type> types = new HashSet<>();
	
	public void addType(Color c1, Color c2, Force force) {
		addType(new ColorRange(c1, c2), force);
	}
	
	public void addType(Color c1, Color c2, Generator<Force> force) {
		addType(new ColorRange(c1, c2), force);
	}
	
	public void addType(ColorRange range, Force force) {
		
	}
	
	public void addType(ColorRange range, Generator<Force> force) {
		
	}

	@Override
	public Player createPlayer1() {
		Body body = new Body(Color.WHITE, new Circle(Point2d.origin(), 1));
		body.accelerate(new Vector2d(-5, 5));
		return new Player(body);
	}

	@Override
	public Player createPlayer2() {
		Body body = new Body(Color.BLACK, new Circle(Point2d.origin(), 1));
		body.accelerate(new Vector2d(5, -5));
		return new Player(body);
	}

	@Override
	public Planet createPlanet(BoundingBox2d check, Random random) {
		
		return null;
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
