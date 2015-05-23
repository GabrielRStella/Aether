package com.ralitski.aether.input;

public interface Input {
	/**
	 * For buttons, 1.0 means down, 0.0 means up. Sticks can have any range, though 0-1 is recommended.
	 * @return
	 */
	float down();
	void update();
	void addListener(InputListener listener);
	void removeListener(InputListener listener);
	String getTypeName();
	String getSimpleName();
	String getName();
}
