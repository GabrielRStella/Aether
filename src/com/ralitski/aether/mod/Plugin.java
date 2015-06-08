package com.ralitski.aether.mod;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Interface for plugins to implement. Abstract subclasses can also be written and implemented without interfering with the system.
 * (TODO)
 * -config stuff
 * 
 * @author ralitski
 *
 */
public interface Plugin {
	
	void onLoad();
	void onEnable();
	void onDisable();
	boolean isEnabled();
	String getName();
	
	File getDataFolder();
	InputStream getResource(String name) throws IOException;
	PluginLoader getPluginLoader();
	/*
	 * https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/plugin/Plugin.html
	 */
	/*
	 * note: "refresh" and "reload" will be different
	 * refresh disables and enables plugins, but keeps classes and such
	 * reload completely trashes old plugins and reloads from disk
	 */
}
