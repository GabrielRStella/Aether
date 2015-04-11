package com.ralitski.aether;

import com.ralitski.util.math.geom.d2.BoundingBox2d;

public interface WorldRender {
	//methods defined in order of call
	void renderBackground(BoundingBox2d box);
	void renderPlanet(Planet planet);
	void renderPlayer1(Player player);
	void renderPlayer2(Player player);
}