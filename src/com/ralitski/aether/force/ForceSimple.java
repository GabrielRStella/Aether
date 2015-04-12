package com.ralitski.aether.force;

import com.ralitski.aether.Body;
import com.ralitski.util.math.geom.d2.Vector2d;

public interface ForceSimple {
	Vector2d act(Body source, Body toForce, double timeStep);
}
