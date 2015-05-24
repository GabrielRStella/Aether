package com.ralitski.aether;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.ralitski.aether.events.EventPlanetPrune;
import com.ralitski.aether.events.EventPlanetSpawn;
import com.ralitski.aether.force.Boundary;
import com.ralitski.aether.force.ForceRedirect;
import com.ralitski.util.math.geom.d2.BoundingBox2d;
import com.ralitski.util.math.geom.d2.Point2d;

public class AetherWorld {
	
	/**
	 * The distance used to produce worlds
	 */
	private static final float DISTANCE_PLANET = 35F; /* placeholder value. TODO: tune this */
	
	//
	
	private AetherGame game;
	
	private Player[] playerPlanets;
	private Body center;
	private Force playerBounds;
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
		playerBounds = new ForceRedirect(new Boundary(context.getPlayerBoundaryDistance(), context.getBoundaryStrength()));
		center = new Body(null);
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
			for(Player player : playerPlanets) {
				force.act(body, player.getBody(), timeStep);
			}
			
			//remove planets a certain distance away from the player (when they are not visible and unlikely to have a noticeable force)
			if(planetCreator.prune(planet, box, check)) {
				EventPlanetPrune prune = new EventPlanetPrune(planet);
				game.getEventSystem().callEvent(prune);
				planets.remove();
			}
		}
		float slow = 0.9F * (1F - (float)timeStep);
		for(Player player : playerPlanets) {
			playerBounds.act(player.getBody(), center, timeStep);
			playerBounds.act(center, player.getBody(), timeStep);
			player.getBody().accelerate(slow);
			player.move(timeStep);
		}
//		center.accelerate(slow);
		center.move(timeStep);
		int size = (int)Math.sqrt(check.getWidth() * check.getHeight());
		float fill = worldPlanets.size() / (size == 0 ? 1 : size);
		if(fill < planetCreator.getPlanetDensity() && random.nextInt(10) == 0) {
			//spawn a few more planets
			int toSpawn = random.nextInt(4) + 1;
			for(int i = 0; i <= toSpawn; i++) {
				Planet planet = planetCreator.createPlanet(check, random);
				if(checkLocation(planet.getBody().getPosition())) {
					EventPlanetSpawn spawn = new EventPlanetSpawn(planet);
					game.getEventSystem().callEvent(spawn);
					if(!spawn.isCancelled()) worldPlanets.add(planet);
				}
			}
		}
	}
	
	public boolean checkLocation(Point2d p) {
		for(Planet planet : worldPlanets) {
			if(planet.getBody().getPosition().length(p) < DISTANCE_PLANET + (random.nextFloat() * DISTANCE_PLANET)) return false;
		}
		return true;
	}
}
