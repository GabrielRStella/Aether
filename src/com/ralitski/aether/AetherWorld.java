package com.ralitski.aether;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.ralitski.aether.force.Boundary;
import com.ralitski.aether.force.ForceRedirect;
import com.ralitski.util.math.geom.d2.BoundingBox2d;
import com.ralitski.util.math.geom.d2.Point2d;

public class AetherWorld {
	
	/**
	 * The distance used to prune and produce worlds
	 */
	private static final float DISTANCE_PLAYER = 30F; /* placeholder value. TODO: tune this */
	/**
	 * The distance used to prune and produce worlds
	 */
	private static final float DISTANCE_PLANET = 50F; /* placeholder value. TODO: tune this */
	/**
	 * scale used to not prune worlds near viewbox
	 */
	private static final float SCALE_PRUNE = 1.3F;
	
	private AetherGame game;
	
	private Player playerPlanet1;
	private Player playerPlanet2;
	private Force playerBounds;
	private List<Planet> worldPlanets;
	private Random random;
	private PlanetCreator planetCreator;
	//will be used to detect collisions in consumption mode
	private CollisionDetector detector;
	
	public AetherWorld(AetherGame game, GameContext context, PlanetCreator planetCreator) {
		this.game = game;
		this.planetCreator = planetCreator;
		playerPlanet1 = planetCreator.createPlayer1();
		playerPlanet2 = planetCreator.createPlayer2();
		worldPlanets = new LinkedList<>();
		random = new Random();
		playerBounds = new ForceRedirect(new Boundary(context.getPlayerBoundaryDistance(), context.getBoundaryStrength()));
		this.detector = context.getCollisionDetector();
	}
	
	public Player getPlayer1() {
		return playerPlanet1;
	}
	
	public Player getPlayer2() {
		return playerPlanet2;
	}
	
	public Iterable<Planet> getPlanets() {
		return worldPlanets;
	}
	
	public void update(double timeStep) {
		//apply forces to players
		//remove planets a certain distance away from players
		//add planets in direction of player motion
		BoundingBox2d box = game.getViewBox().getViewBox();
		BoundingBox2d check = box.scale(SCALE_PRUNE);
		Iterator<Planet> planets = worldPlanets.iterator();
		while(planets.hasNext()) {
			Planet planet = planets.next();
			Force force = planet.getForce();
			Body body = planet.getBody();
//			if(!detector.detectCollision(playerPlanet1, planet)) force.act(body, playerPlanet1.getBody());
//			if(!detector.detectCollision(playerPlanet2, planet)) force.act(body, playerPlanet2.getBody());
			force.act(body, playerPlanet1.getBody(), timeStep);
			force.act(body, playerPlanet2.getBody(), timeStep);
			
			//remove planets a certain distance away from the player (when they are not visible and unlikely to have a noticeable force)
			Point2d p = body.getPosition();
			int x = (int)p.getX();
			int y = (int)p.getY();
//			if(!check.contains(x, y) && !checkDist(p, playerPlanet1.getBody().getPosition()) && !checkDist(p, playerPlanet2.getBody().getPosition())) {
//				planets.remove();
//			}
			if(!check.contains(x, y)) {
				planets.remove();
			}
		}
		playerBounds.act(playerPlanet1.getBody(), playerPlanet2.getBody(), timeStep);
		playerBounds.act(playerPlanet2.getBody(), playerPlanet1.getBody(), timeStep);
		float slow = 0.4F / (float)timeStep;
		playerPlanet1.getBody().accelerate(slow);
		playerPlanet2.getBody().accelerate(slow);
		playerPlanet1.move();
		playerPlanet2.move();
		int size = (int)Math.sqrt(check.getWidth() * check.getHeight());
		float fill = worldPlanets.size() / (size == 0 ? 1 : size);
		if(fill < planetCreator.getPlanetDensity() && random.nextInt(10) == 0) {
			//spawn a few more planets
			int toSpawn = random.nextInt(4) + 1;
			for(int i = 0; i <= toSpawn; i++) {
				Planet planet = planetCreator.createPlanet(check, random);
				if(checkLocation(planet.getBody().getPosition())) worldPlanets.add(planet);
			}
		}
	}
	
	private boolean checkDist(Point2d point, Point2d check) {
		return point.length(check) < DISTANCE_PLAYER;
	}
	
	public boolean checkLocation(Point2d p) {
		for(Planet planet : worldPlanets) {
			if(planet.getBody().getPosition().length(p) < DISTANCE_PLANET) return false;
		}
		return true;
	}
}
