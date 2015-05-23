package com.ralitski.aether;

import java.util.Iterator;

import com.ralitski.util.Ticker;
import com.ralitski.util.render.img.Color;
import com.ralitski.util.render.img.ColorHelper;

public class ColorTransition {

	private Iterator<Color> colors;
	private Color current;
	private Color next;
	private double partial;
	private Ticker ticker;
	
	public ColorTransition(Iterator<Color> colors, Ticker ticker) {
		this.colors = colors;
		this.ticker = ticker;
		ticker.time();
		current = colors.next();
		next = colors.next();
	}
	
	public Color next() {
		partial += ticker.time();
		if(partial > 1) {
			partial -= 1;
			current = next;
			next = colors.next();
		}
		return ColorHelper.blend(next, current, (float)partial);
	}

}
