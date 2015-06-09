package com.ralitski.aether.mod.java;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

import com.ralitski.aether.mod.Plugin;
import com.ralitski.aether.mod.PluginLoader;

public class JavaPluginLoader implements PluginLoader {

	@Override
	public Plugin loadPlugin(File file) {
		if(file.exists() && file.getName().endsWith(".jar")) {
		}
		return null;
	}

	@Override
	public boolean enable(Plugin plugin) {
		if(plugin instanceof JavaPlugin) {
			JavaPlugin jPlugin = (JavaPlugin)plugin;
			try {
				jPlugin.jarFile = new JarFile(jPlugin.jarFileLoc);
				jPlugin.enabled = true;
				return true;
			} catch (IOException e) {
				return false;
			}
		}
		return plugin instanceof JavaPlugin;
	}

	@Override
	public boolean disable(Plugin plugin) {
		if(plugin instanceof JavaPlugin) {
			JavaPlugin jPlugin = (JavaPlugin)plugin;
			try {
				jPlugin.jarFile.close();
				jPlugin.jarFile = null;
				jPlugin.enabled = false;
				return true;
			} catch (IOException e) {
				return false;
			}
		}
		return false;
	}
	
	/*
URLClassLoader child = new URLClassLoader (myJar.toURL(), this.getClass().getClassLoader());
Class classToLoad = Class.forName ("com.MyClass", true, child);
Method method = classToLoad.getDeclaredMethod ("myMethod");
Object instance = classToLoad.newInstance ();
Object result = method.invoke (instance);
	 */
	
	/*
	 * note: Jar class loader must be kept with each plugin and trashed on refresh (so classes can be dynamically loaded; can't/shouldn't be loaded all at once)
	 */
	/*
	 * TODO: JavaPluginLoader
	 * https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/plugin/PluginLoader.html
	 */

}
