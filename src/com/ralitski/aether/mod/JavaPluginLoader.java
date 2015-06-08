package com.ralitski.aether.mod;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JavaPluginLoader implements PluginLoader {

	@Override
	public Plugin loadPlugin(File file) {
		return null;
	}

	public File getDataFolder(JavaPlugin javaPlugin) {
		// TODO Auto-generated method stub
		return null;
	}

	public InputStream getResource(File jarFile, String name) throws IOException {
		JarFile file = new JarFile(jarFile);
		JarEntry entry = file.getJarEntry(name);
		return entry != null ? file.getInputStream(entry) : null;
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
