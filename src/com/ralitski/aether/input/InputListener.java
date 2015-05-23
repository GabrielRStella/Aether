package com.ralitski.aether.input;

public interface InputListener {
	void onInputChange(Input input, float prev, float next, long time);
}
