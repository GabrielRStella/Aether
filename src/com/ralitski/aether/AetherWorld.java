package com.ralitski.aether;

import java.util.LinkedList;
import java.util.List;

public class AetherWorld {
	
	private Player playerPlanet1;
	private Player playerPlanet2;
	
	private List<Planet> worldPlanets;
	
	public AetherWorld() {
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
	}
}
