package com.ralitski.aether;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.ralitski.util.math.geom.d2.BoundingBox2d;

public class AetherWorld {
	
	/**
	 * The distance used to produce worlds
	 */
//	private static final float DISTANCE_PLANET = 50F; /* placeholder value. TODO: tune this */
	
	//
	
	private AetherGame game;
	
	private Player[] playerPlanets;
//	private Force playerBounds;
	private List<Planet> worldPlanets;
	private Random random;
	private PlanetCreator planetCreator;
	//will be used to detect collisions in consumption mode
//	private CollisionDetector detector;
	
	public AetherWorld(AetherGame game, GameContext context) {
		this.game = game;
		this.planetCreator = context.getPlanetCreator();
		int pCount = context.getPlayerCount();
		playerPlanets = new Player[pCount];
		for(int i = 0; i < pCount; i++) {
			playerPlanets[i] = planetCreator.createPlayer(i, pCount);
		}
		worldPlanets = new LinkedList<>();
		random = new Random();
//		playerBounds = new ForceRedirect(new Boundary(context.getPlayerBoundaryDistance(), context.getBoundaryStrength()));
//		this.detector = context.getCollisionDetector();
	}
	
	public Player[] getPlayers() {
		return playerPlanets;
	}
	
	public Iterable<Planet> getPlanets() {
		return worldPlanets;
	}
	
	public void update(double timeStep) {
//		timeStep *= 100F;
		//apply forces to players
		//remove planets a certain distance away from players
		//add planets in direction of player motion
		BoundingBox2d box = game.getViewBox().getViewBox();
		BoundingBox2d check = box.scale(planetCreator.getBoxScale());
		Iterator<Planet> planets = worldPlanets.iterator();
		while(planets.hasNext()) {
			Planet planet = planets.next();
			Force force = planet.getForce();
			Body body = planet.getBody();
//			if(!detector.detectCollision(playerPlanet1, planet)) force.act(body, playerPlanet1.getBody());
//			if(!detector.detectCollision(playerPlanet2, planet)) force.act(body, playerPlanet2.getBody());
			for(Player player : playerPlanets) {
				force.act(body, player.getBody(), timeStep);
			}
			
			//remove planets a certain distance away from the player (when they are not visible and unlikely to have a noticeable force)
			if(planetCreator.prune(planet, box, check)) {
				planets.remove();
			}
		}
		for(Player player : playerPlanets) {
//			playerBounds.act(player.getBody(), playerPlanet2.getBody(), timeStep);
			float slow = 0.9F * (1F - (float)timeStep);
			player.getBody().accelerate(slow);
			player.move(timeStep);
		}
		int size = (int)Math.sqrt(check.getWidth() * check.getHeight());
		float fill = worldPlanets.size() / (size == 0 ? 1 : size);
		if(fill < planetCreator.getPlanetDensity() && random.nextInt(10) == 0) {
			//spawn a few more planets
			int toSpawn = random.nextInt(4) + 1;
			for(int i = 0; i <= toSpawn; i++) {
				Planet planet = planetCreator.createPlanet(check, random);
//				if(checkLocation(planet.getBody().getPosition()))
					worldPlanets.add(planet);
			}
		}
	}
	
//	public boolean checkLocation(Point2d p) {
//		for(Planet planet : worldPlanets) {
//			if(planet.getBody().getPosition().length(p) < DISTANCE_PLANET) return false;
//		}
//		return true;
//	}
}
