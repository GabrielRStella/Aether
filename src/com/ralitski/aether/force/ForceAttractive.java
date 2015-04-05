package com.ralitski.aether.force;

import com.ralitski.aether.Body;
import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.math.geom.d2.Vector2d;

public class ForceAttractive implements ForceSimple {

	@Override
	public Vector2d act(Body source, Body toForce) {
		Point2d src = source.getPosition();
		Point2d dst = toForce.getPosition();
		float dist = src.length(dst);
		Vector2d v = new Vector2d(dst, src);
		System.out.println(v + " " + source.getMass() + " " + toForce.getMass() + " " + dist + " " + (source.getMass() * toForce.getMass() / dist / dist));
		v.multiply(source.getMass() * toForce.getMass() / dist / dist);
		return v;
	}

}
