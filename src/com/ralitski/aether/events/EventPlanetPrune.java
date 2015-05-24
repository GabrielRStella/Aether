package com.ralitski.aether.events;

import com.ralitski.aether.Planet;

public class EventPlanetPrune {
	
	private Planet planet;
	
	public EventPlanetPrune(Planet planet) {
		this.planet = planet;
	}
	
	public Planet getPlanet() {
		return planet;
	}
}
