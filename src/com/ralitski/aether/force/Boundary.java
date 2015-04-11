package com.ralitski.aether.force;

import com.ralitski.aether.Body;
import com.ralitski.util.math.geom.d2.Vector2d;

public class Boundary implements ForceSimple {
	
	private float maxDist;
	private float maxValue;
	
	public Boundary(float maxDist, float maxValue) {
		this.maxDist = maxDist;
		this.maxValue = 1F / (maxValue + 1F);
	}

	@Override
	public Vector2d act(Body source, Body toForce) {
		float dist = source.getPosition().length(toForce.getPosition());
		dist = maxDist - dist;
		dist = Math.max(dist, maxValue);
		dist = 1F / dist;
		dist -= 1F;
		if(dist > 0) {
			Vector2d v = new Vector2d(toForce.getPosition(), source.getPosition());
			v.setMagnitude(dist);
			return v;
		}
		return new Vector2d();
	}

}
