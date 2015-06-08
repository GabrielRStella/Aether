package com.ralitski.aether.mod;

import java.io.File;

/**
 * Loads plugins from a given folder.
 * 
 * @author ralitski
 *
 */
public interface PluginLoader {

	Plugin loadPlugin(File file);
	
}
