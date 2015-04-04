package com.ralitski.aether;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.ralitski.util.gui.Box;
import com.ralitski.util.gui.BoxScaled;
import com.ralitski.util.math.geom.d2.Point2d;

public class AetherWorld {
	
	/**
	 * The distance used to prune and produce worlds
	 */
	private static final float DISTANCE = 50F; /* placeholder value. TODO: tune this */
	/**
	 * scale used to not prune worlds near viewbox
	 */
	private static final float SCALE = 1.3F; /* placeholder value. TODO: tune this */
	/**
	 * scale used to add planets
	 */
	private static final float SPAWN_SCALE = 0.01F; /* placeholder value. TODO: tune this */
	/**
	 * highest of planets spawned at a time
	 */
	private static final int SPAWN_COUNT = 5; /* placeholder value. TODO: tune this */
	
	private AetherGame game;
	
	private Player playerPlanet1;
	private Player playerPlanet2;
	private PlayerMovementController movement;
	private List<Planet> worldPlanets;
	private Random random;
	private PlanetCreator planetCreator;
	
	public AetherWorld(AetherGame game, PlanetCreator planetCreator) {
		this.game = game;
		this.planetCreator = planetCreator;
		playerPlanet1 = planetCreator.createPlayer1();
		playerPlanet2 = planetCreator.createPlayer2();
		movement = new PlayerMovementControllerDefault(playerPlanet1, playerPlanet2);
		worldPlanets = new LinkedList<>();
		random = new Random();
	}
	
	public Player getPlayer1() {
		return playerPlanet1;
	}
	
	public Player getPlayer2() {
		return playerPlanet2;
	}

	public PlayerMovementController getPlayerMovementController() {
		return movement;
	}
	
	public Iterable<Planet> getPlanets() {
		return worldPlanets;
	}
	
	public void update() {
		//apply forces to players
		//remove planets a certain distance away from players
		//add planets in direction of player motion
		Box box = game.getViewBox().getViewBox();
		BoxScaled check = new BoxScaled(box, SCALE);
		Iterator<Planet> planets = worldPlanets.iterator();
		while(planets.hasNext()) {
			Planet planet = planets.next();
			Force force = planet.getForce();
			Body body = planet.getBody();
			force.act(body, playerPlanet1.getBody());
			force.act(body, playerPlanet2.getBody());
			
			//remove planets a certain distance away from the player (when they are not visible and unlikely to have a noticeable force)
			Point2d p = body.getPosition();
			int x = (int)p.getX();
			int y = (int)p.getY();
			if(!check.contains(x, y) && !checkDist(p, playerPlanet1.getBody().getPosition()) && !checkDist(p, playerPlanet2.getBody().getPosition())) {
				planets.remove();
			}
		}
		playerPlanet1.move();
		playerPlanet2.move();
		float slow = 0.9F;
		playerPlanet1.getBody().accelerate(slow);
		playerPlanet2.getBody().accelerate(slow);
		int size = (int)Math.sqrt(check.getWidth() * check.getHeight());
		float fill = worldPlanets.size() / (size == 0 ? 1 : size);
		if(fill < SPAWN_SCALE && random.nextInt(10) == 0) {
			//spawn a few more planets
			int toSpawn = random.nextInt(SPAWN_COUNT);
			for(int i = 0; i <= toSpawn; i++) {
				Planet planet = planetCreator.createPlanet(check, random);
				worldPlanets.add(planet);
			}
		}
	}
	
	private boolean checkDist(Point2d point, Point2d check) {
		return point.length(check) < DISTANCE;
	}
}
