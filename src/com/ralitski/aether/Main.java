package com.ralitski.aether;

/**
 * TODO:
 * GameContext
 * modding/plugins (loader, hooks)
 * ViewBox
 * replace timer with timestep calculator
 * possibly add GameContext rendering? idk
 * allow player to switch between GameContexts and WorldRenderers
 * add support for random textures (ie, circle1.png circle2.png circle3.png)
 * change BoundingBox2d to support rotation (for screen and world stuff)
 * change input to support rotated movement
 * 
 * @author ralitski
 *
 */
public class Main {
	
	public static void main(String[] args) {
		new AetherDisplay().start();
	}
}
