package com.ralitski.aether;

import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.math.geom.d2.Shape2d;
import com.ralitski.util.math.geom.d2.Vector2d;
import com.ralitski.util.math.var.Variable;
import com.ralitski.util.math.var.VariableFixed;
import com.ralitski.util.render.img.Color;

public class Body {
	
	private Color color;
	private Shape2d shape;
	private Vector2d velocity;
	
	//just in case I have something that changes mass, idk...
	private Variable mass;
	
	public Body(Color color, float mass) {
		this(color, Point2d.origin(), mass);
	}
	
	public Body(Color color, Variable mass) {
		this(color, Point2d.origin(), mass);
	}

	public Body(Color color, Shape2d shape, float mass) {
		this(color, shape, new Vector2d(), mass);
	}

	public Body(Color color, Shape2d shape, Variable mass) {
		this(color, shape, new Vector2d(), mass);
	}

	public Body(Color color, Vector2d velocity, float mass) {
		this(color, Point2d.origin(), velocity, mass);
	}

	public Body(Color color, Vector2d velocity, Variable mass) {
		this(color, Point2d.origin(), velocity, mass);
	}

	public Body(Color color, Shape2d shape, Vector2d velocity, float mass) {
		this(color, shape, velocity, new VariableFixed(mass));
	}

	public Body(Color color, Shape2d shape, Vector2d velocity, Variable mass) {
		this.color = color;
		this.shape = shape;
		this.velocity = velocity;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Shape2d getShape() {
		return shape;
	}
	
	public Point2d getPosition() {
		return shape.getPosition();
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
