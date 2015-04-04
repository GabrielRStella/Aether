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
	
	private Variable density;
	
	public Body(Color color, float density) {
		this(color, Point2d.origin(), density);
	}
	
	public Body(Color color, Variable density) {
		this(color, Point2d.origin(), density);
	}

	public Body(Color color, Shape2d shape, float density) {
		this(color, shape, new Vector2d(), density);
	}

	public Body(Color color, Shape2d shape, Variable density) {
		this(color, shape, new Vector2d(), density);
	}

	public Body(Color color, Vector2d velocity, float density) {
		this(color, Point2d.origin(), velocity, density);
	}

	public Body(Color color, Vector2d velocity, Variable density) {
		this(color, Point2d.origin(), velocity, density);
	}

	public Body(Color color, Shape2d shape, Vector2d velocity, float density) {
		this(color, shape, velocity, new VariableFixed(density));
	}

	public Body(Color color, Shape2d shape, Vector2d velocity, Variable density) {
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

	public void move() {
		Point2d p = shape.getPosition();
		p.add(velocity);
	}
	
	public float getMass() {
		return density.value() * shape.getArea();
	}
	
	public float getDensity() {
		return density.value();
	}
	
	public Variable getDensityVariable() {
		return density;
	}
	
	public void accelerate(Vector2d acceleration) {
		velocity.add(acceleration);
	}
	
	//F = ma
	//a = F/m
	public void applyForce(Vector2d force) {
		velocity.add(force.descaleCopy(getMass()));
	}
	
	public void accelerate(float scale) {
		velocity.multiply(scale);
	}
}
