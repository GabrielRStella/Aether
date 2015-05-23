package com.ralitski.aether;

/**
 * TODO:
 * modding/plugins (loader, hooks)
 * allow player to switch between GameContexts before game starts
 * add support for random textures (ie, circle1.png circle2.png circle3.png) - or differently shaped planets
 * change BoundingBox2d to support rotation (for screen and world stuff)
 * keep track of controller joystick as mouse movement in gui
 * add objects that keep track of a few keyboard/controller states and return a vector() on update
 * support for n player planets (using above-mentioned input objects)
 * menus and options
 * better input API
 * 
 * @author ralitski
 *
 */
public class Main {
	
	public static void main(String[] args) {
		new AetherDisplay().start();
	}
}
