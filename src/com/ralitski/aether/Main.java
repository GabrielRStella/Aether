package com.ralitski.aether;

/**
 * TODO:
 * 
 * fix gui system RenderList bug
 * 
 * modding/plugins (loader, hooks)
 * allow player to switch between GameContexts before game starts
 * add support for random textures (ie, circle1.png circle2.png circle3.png) - or differently shaped planets
 * change BoundingBox2d to support rotation (for screen and world stuff)
 * keep track of controller joystick as mouse movement in gui
 * menus and options
 * better input API
 * rotating world
 * add goal
 * menu text...?
 * 
 * @author ralitski
 *
 */
public class Main {
	
	public static void main(String[] args) {
		new AetherDisplay().start();
	}
}
