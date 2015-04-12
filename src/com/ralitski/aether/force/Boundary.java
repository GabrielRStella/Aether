package com.ralitski.aether.force;

import com.ralitski.aether.Body;
import com.ralitski.util.math.geom.d2.Vector2d;

public class Boundary implements ForceSimple {
	
	private float maxDist;
	private float maxValue;
	
	public Boundary(float maxDist, float strength) {
		this.maxDist = maxDist;
		this.maxValue = strength;
	}

	@Override
	public Vector2d act(Body source, Body toForce, double timeStep) {
		float dist = source.getPosition().length(toForce.getPosition());
		dist -= maxDist;
		if(dist > 0) {
			Vector2d v = new Vector2d(toForce.getPosition(), source.getPosition());
			float mag = (float)Math.sqrt(dist * maxValue);
			v.setMagnitude(mag * (float)timeStep);
			return v;
		}
		return new Vector2d();
	}

}
