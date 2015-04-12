package com.ralitski.aether.force;

import com.ralitski.aether.Body;
import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.math.geom.d2.Vector2d;

public class ForceTorque implements ForceSimple {
	
	private float scale;
	
	public ForceTorque() {
		this(1);
	}
	
	public ForceTorque(float scale) {
		this.scale = scale;
	}

	@Override
	public Vector2d act(Body source, Body toForce, double timeStep) {
		Point2d src = source.getPosition();
		Point2d dst = toForce.getPosition();
		float dist = src.length(dst);
		Vector2d v = new Vector2d(dst, src);
		v.multiply((float)timeStep * scale * source.getMass() * toForce.getMass() / toForce.getMass() / dist / dist);
		v.rotateDegrees(-90);
		return v;
	}

}
