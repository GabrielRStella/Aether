package com.ralitski.aether.force;

import com.ralitski.aether.Body;
import com.ralitski.aether.Force;

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
