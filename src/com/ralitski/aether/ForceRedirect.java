package com.ralitski.aether;

public class ForceRedirect implements Force {
	
	private ForceSimple force;
	
	public ForceRedirect(ForceSimple force) {
		this.force = force;
	}

	@Override
	public void act(Body source, Body toForce) {
		toForce.accelerate(force.act(source, toForce));
	}

}
