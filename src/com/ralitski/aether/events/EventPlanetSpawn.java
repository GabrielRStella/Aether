package com.ralitski.aether.events;

import com.ralitski.aether.Planet;
import com.ralitski.util.Cancellable;

public class EventPlanetSpawn implements Cancellable {
	
	private boolean cancelled;
	private Planet planet;
	
	public EventPlanetSpawn(Planet planet) {
		this.planet = planet;
	}
	
	public Planet getPlanet() {
		return planet;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
}
