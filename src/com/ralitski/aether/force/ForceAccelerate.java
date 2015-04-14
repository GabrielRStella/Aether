package com.ralitski.aether.force;

import com.ralitski.aether.Body;
import com.ralitski.aether.Force;
import com.ralitski.util.math.geom.d2.Point2d;

public class ForceAccelerate implements Force {
	
	private static final float STRENGTH = 0.1F;
	
	private boolean inverse;
	private float scale;
	
	public ForceAccelerate() {
		this(true);
	}

	public ForceAccelerate(boolean inverse) {
		this(inverse, 1);
	}
	
	public ForceAccelerate(float scale) {
		this(true, scale);
	}

	public ForceAccelerate(boolean inverse, float scale) {
		this.inverse = inverse;
		this.scale = scale;
	}

	@Override
	public void act(Body source, Body toForce, double timeStep) {
		float accel = source.getMass() * toForce.getMass() * scale * STRENGTH * (float)timeStep;
		Point2d src = source.getPosition();
		Point2d dst = toForce.getPosition();
		float dist = src.length(dst);
		accel = accel / dist;
		accel += 1F;
		if(inverse) accel = 1F / accel;
		toForce.accelerate(accel);
	}

	@Override
	public Force getOpposite() {
		return new ForceAccelerate(!inverse, scale);
	}

}
