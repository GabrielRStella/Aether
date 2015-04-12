package com.ralitski.aether.force;

import com.ralitski.aether.Body;
import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.math.geom.d2.Vector2d;

public class ForceAttractive implements ForceSimple {
	
	private static final float STRENGTH = 0.1F;
	
	private float scale;
	
	public ForceAttractive() {
		this(1);
	}
	
	public ForceAttractive(float scale) {
		this.scale = scale;
	}

	@Override
	public Vector2d act(Body source, Body toForce, double timeStep) {
		Point2d src = source.getPosition();
		Point2d dst = toForce.getPosition();
		float dist = src.length(dst);
		Vector2d v = new Vector2d(dst, src);
		v.multiply((float)timeStep * scale * STRENGTH * source.getMass() * toForce.getMass() / dist / dist / dist);
		return v;
	}

}
