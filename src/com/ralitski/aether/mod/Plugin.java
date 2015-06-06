package com.ralitski.aether.mod;

/**
 * Interface for plugins to implement. Abstract subclasses can also be written and implemented without interfering with the system.
 * (TODO)
 * 
 * @author ralitski
 *
 */
public interface Plugin {
	void onLoad();
	void onEnable();
	void onDisable();
	void getName();
}
