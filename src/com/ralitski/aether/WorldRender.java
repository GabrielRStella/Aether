package com.ralitski.aether;

import com.ralitski.util.math.geom.d2.BoundingBox2d;

public interface WorldRender {
	//methods defined in order of call
	void renderBackground(BoundingBox2d box, float rot);
	void renderPlanet(Planet planet);
	/**
	 * 
	 * @param player
	 * @param index The index of this player (range 0 - (count - 1))
	 * @param count The total number of players
	 */
	void renderPlayer(Player player, int index, int count);
}