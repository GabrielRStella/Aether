package com.ralitski.aether;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.ralitski.util.gui.Box;
import com.ralitski.util.gui.BoxScaled;
import com.ralitski.util.math.geom.d2.Point2d;

public class AetherWorld {
	
	/**
	 * The distance used to prune and produce worlds
	 */
	private static final float DISTANCE = 10F; /* placeholder value. TODO: tune this */
	/**
	 * scale used to not prune worlds near viewbox
	 */
	private static final float SCALE = 1.2F; /* placeholder value. TODO: tune this */
	
	private AetherGame game;
	
	private Player playerPlanet1;
	private Player playerPlanet2;
	
	private List<Planet> worldPlanets;
	
	public AetherWorld(AetherGame game) {
		this.game = game;
		worldPlanets = new LinkedList<>();
	}
	
	public Player getPlayer1() {
		return playerPlanet1;
	}
	
	public Player getPlayer2() {
		return playerPlanet2;
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
			//remove planets a certain distance away from the player (when they are unlikely to have a noticeable force)
			Point2d p = body.getPosition();
			int x = (int)p.getX();
			int y = (int)p.getY();
			if(!checkDist(p, playerPlanet1.getBody().getPosition()) && !checkDist(p, playerPlanet1.getBody().getPosition()) && !check.contains(x, y)) {
				planets.remove();
			}
		}
	}
	
	private boolean checkDist(Point2d point, Point2d check) {
		return point.length(check) < DISTANCE;
	}
}
