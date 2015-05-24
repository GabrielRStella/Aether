package com.ralitski.aether;

import java.util.Iterator;
import java.util.Random;

import com.ralitski.util.render.img.Color;

public class ColorsPastel implements Iterator<Color> {

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public Color next() {
			Random random = new Random();
			int main = random.nextInt(3);
			int sub = random.nextInt(2);
			
			int c1 = 220 + random.nextInt(36); //220-255
			int c2 = 190 + random.nextInt(46); //190-235
			int c3 = 170 + random.nextInt(46); //170-215
			
			int r, g, b;
			if(main == 0) {
				r = c1;
				if(sub == 0) {
					g = c2;
					b = c3;
				} else {
					g = c3;
					b = c2;
				}
			} else if(main == 1) {
				g = c1;
				if(sub == 0) {
					r = c2;
					b = c3;
				} else {
					r = c3;
					b = c2;
				}
			} else {
				b = c1;
				if(sub == 0) {
					r = c2;
					g = c3;
				} else {
					r = c3;
					g = c2;
				}
			}
			return new Color(r, g, b);
	}

	@Override
	public void remove() {
	}
}
