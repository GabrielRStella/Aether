package com.ralitski.aether;

/**
 * TODO:
 * 
 * get rid of old GLImage objects
 * test lag profiler
 * fix lag on game start
 * pass profiler data to lag calculators
 * 
 * modding/plugins (loader, hooks)
 * allow player to switch between GameContexts before game starts
 * add support for random textures (ie, circle1.png circle2.png circle3.png) - or differently shaped planets
 * fix BoundingBox2d collision detector
 * keep track of controller joystick as mouse movement in gui
 * menus and options
 * better input API
 * shaders (antialias, diffusion)
 * rotating world
 * add goal
 * 
 * @author ralitski
 *
 */
public class Main {
	
	public static void main(String[] args) {
		new AetherDisplay().start();
	}
}
