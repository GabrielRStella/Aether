package com.ralitski.aether.mod;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract class JavaPlugin implements Plugin {
	
	JavaPluginLoader loader;
	File jarFile;
	File dataFolder;

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
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
		return loader.getResource(jarFile, name);
	}

	@Override
	public PluginLoader getPluginLoader() {
		return loader;
	}

}
