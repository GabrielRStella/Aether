package com.ralitski.aether.input;

import org.lwjgl.Sys;

public class Timer {
	
	private static Timer instance = new Timer();
	
	public static Timer getInstance() {
		return instance;
	}
	
	public long getTime() {
		return Sys.getTime();
	}
	
	//number of ticks per second
	public long getTimerResolution() {
		return Sys.getTimerResolution();
	}
}
