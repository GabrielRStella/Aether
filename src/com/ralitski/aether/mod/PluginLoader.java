package com.ralitski.aether.mod;

/**
 * Loads plugins from a given folder.
 * 
 * @author ralitski
 *
 */
public class PluginLoader {

	
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
	
}
