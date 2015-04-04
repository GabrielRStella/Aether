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

	@Override
	public Force getOpposite() {
		return new ForceRedirectOpposite();
	}
	
	private class ForceRedirectOpposite implements Force {

		@Override
		public void act(Body source, Body toForce) {
			toForce.accelerate(force.act(source, toForce).negateCopy());
		}

		@Override
		public Force getOpposite() {
			//return original
			return ForceRedirect.this;
		}
		
	}

}
