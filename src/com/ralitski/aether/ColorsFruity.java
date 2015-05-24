package com.ralitski.aether;

import java.util.Iterator;
import java.util.Random;

import com.ralitski.util.render.img.Color;

public class ColorsFruity implements Iterator<Color> {

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public Color next() {
			Random random = new Random();
			int main = random.nextInt(3);
			int sub = random.nextInt(2);
			
			int c1 = 230 + random.nextInt(26); //230-255
			int c2 = 100 + random.nextInt(66); //100-165
			int c3 = 50 + random.nextInt(106); //50-155
			
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
