package com.ralitski.aether;

public class Planet {
	
	private Body body;
	private Force force;
	
	public Planet(Body body, Force force) {
		this.body = body;
		this.force = force;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public Force getForce() {
		return force;
	}

	public void setForce(Force force) {
		this.force = force;
	}
}
