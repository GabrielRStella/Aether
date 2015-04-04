package com.ralitski.aether;

//TODO: input methods? idk.
public class Player {
	
	private Body body;
	
	public Player(Body body) {
		this.body = body;
	}
	
	public void setBody(Body body) {
		this.body = body;
	}
	
	public Body getBody() {
		return body;
	}

	public void move() {
		body.move();
	}
}
