package com.ralitski.aether;

public interface Force {
	void act(Body source, Body toForce, double timeStep);
	Force getOpposite();
}
