package com.ralitski.aether.force;

import com.ralitski.aether.Body;
import com.ralitski.aether.Force;

public class ForceEmpty implements Force {
	
	public static ForceEmpty INSTANCE = new ForceEmpty();
	
	private ForceEmpty(){}

	@Override
	public void act(Body source, Body toForce, double timeStep) {
	}

	@Override
	public Force getOpposite() {
		return this;
	}

}
