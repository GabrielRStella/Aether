package com.ralitski.aether;

/**
 * TODO:
 * GameContext
 * modding/plugins (loader, hooks)
 * ViewBox
 * replace timer with timestep calculator
 * possibly add GameContext rendering? idk
 * allow player to switch between GameContexts and WorldRenderers
 * 
 * @author ralitski
 *
 */
public class Main {
	
	public static void main(String[] args) {
		new AetherDisplay().start();
	}
}
