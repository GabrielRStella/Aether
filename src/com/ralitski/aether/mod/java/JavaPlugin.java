package com.ralitski.aether.mod.java;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.ralitski.aether.mod.Plugin;
import com.ralitski.aether.mod.PluginLoader;

public abstract class JavaPlugin implements Plugin {
	
	JavaPluginLoader loader;
	File jarFileLoc;
	JarFile jarFile;
	File dataFolder;
	boolean enabled;

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getDataFolder() {
		return dataFolder;
	}

	@Override
	public InputStream getResource(String name) throws IOException {
		JarEntry entry = jarFile.getJarEntry(name);
		return entry != null ? jarFile.getInputStream(entry) : null;
	}

	@Override
	public PluginLoader getPluginLoader() {
		return loader;
	}

}
