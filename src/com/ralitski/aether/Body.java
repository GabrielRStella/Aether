package com.ralitski.aether;

import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.math.geom.d2.Vector2d;
import com.ralitski.util.math.var.Variable;
import com.ralitski.util.math.var.VariableFixed;
import com.ralitski.util.render.img.Color;

public class Body {
	
	private Color color;
	private Point2d position;
	private Vector2d velocity;
	
	//just in case I have something that changes mass, idk...
	private Variable mass;
	
	public Body(Color color, float mass) {
		this(color, Point2d.origin(), mass);
	}
	
	public Body(Color color, Variable mass) {
		this(color, Point2d.origin(), mass);
	}

	public Body(Color color, Point2d position, float mass) {
		this(color, position, new Vector2d(), mass);
	}

	public Body(Color color, Point2d position, Variable mass) {
		this(color, position, new Vector2d(), mass);
	}

	public Body(Color color, Vector2d velocity, float mass) {
		this(color, Point2d.origin(), velocity, mass);
	}

	public Body(Color color, Vector2d velocity, Variable mass) {
		this(color, Point2d.origin(), velocity, mass);
	}

	public Body(Color color, Point2d position, Vector2d velocity, float mass) {
		this(color, position, velocity, new VariableFixed(mass));
	}

	public Body(Color color, Point2d position, Vector2d velocity, Variable mass) {
		this.color = color;
		this.position = position;
		this.velocity = velocity;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Point2d getPosition() {
		return position;
	}
	
	public Vector2d getVelocity() {
		return velocity;
	}
	
	public float getMass() {
		return mass.value();
	}
	
	public Variable getMassVariable() {
		return mass;
	}
	
	public void accelerate(Vector2d acceleration) {
		velocity.add(acceleration);
	}
	
	//F = ma
	//a = F/m
	public void applyForce(Vector2d force) {
		velocity.add(force.scaleCopy(mass.value()));
	}
	
	public void accelerate(float scale) {
		velocity.multiply(scale);
	}
}
