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
 * handle rotating screen
 * 
 * @author ralitski
 *
 */
public class Main {
	
	public static void main(String[] args) {
		new AetherDisplay().start();
	}
}
