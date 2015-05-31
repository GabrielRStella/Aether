package com.ralitski.aether;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Profiler {
	
	private Map<String, Long> times = Collections.synchronizedMap(new HashMap<String, Long>());
	private String section = "";
	
	public Profiler() {
	}
	
	public Set<String> getSections() {
		return times.keySet();
	}
	
	public long getTime(String section) {
		return times.containsKey(section) ? times.get(section) : 0;
	}
	
	/**
	 * Clears all times.
	 */
	public void startTick() {
		times.clear();
		section = "";
		times.put(section, getTime());
	}
	
	/**
	 * Sets times and clears sections.
	 */
	public void endTick() {
		while(!section.isEmpty()) endSection();
		set();
	}
	
	public String getCurrentSection() {
		return section;
	}
	
	public void startSection(String section) {
		this.section = this.section + "." + section;
		times.put(this.section, getTime());
	}
	
	public void endSection() {
		set();
		int i = section.lastIndexOf(".");
		section = section.substring(0, i);
	}
	
	private void set() {
		long l = times.get(section);
		l = getTime() - l;
		times.put(section, l);
	}
	
	private long getTime() {
		return System.nanoTime();
	}
}
