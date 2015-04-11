package com.ralitski.aether;

public interface CollisionDetector {
	/**
	 * returns true if the player and planet are overlapping.
	 * @param player
	 * @param planet
	 * @return
	 */
	boolean detectCollision(Player player, Planet planet);
}
