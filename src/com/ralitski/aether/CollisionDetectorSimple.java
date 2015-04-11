package com.ralitski.aether;

import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.math.geom.d2.Shape2d;

public class CollisionDetectorSimple implements CollisionDetector {

	@Override
	public boolean detectCollision(Player player, Planet planet) {
		Shape2d playerShape = player.getBody().getShape();
		Shape2d planetShape = planet.getBody().getShape();
		Point2d playerPoint = playerShape.getPosition();
		Point2d planetPoint = planetShape.getPosition();
		return playerShape.getState(planetPoint) != Shape2d.POINT_OUTSIDE || planetShape.getState(playerPoint) != Shape2d.POINT_OUTSIDE;
	}

}
