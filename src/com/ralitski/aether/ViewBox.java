package com.ralitski.aether;

import com.ralitski.aether.gui.GuiGame;
import com.ralitski.util.gui.GuiManager;
import com.ralitski.util.math.geom.d2.BoundingBox2d;
import com.ralitski.util.math.geom.d2.Point2d;

public class ViewBox {
	
	public static final float SCALE_BASE = 1.5F;
	
	public static final float MIN_WIDTH = 100;
	public static final float MIN_HEIGHT = 100;

	private GuiGame game;
	private BoundingBox2d viewBox;
	
	public ViewBox(GuiGame game) {
		this.game = game;
		viewBox = new BoundingBox2d();
	}
	
	public BoundingBox2d getViewBox() {
		return viewBox;
	}

	//this seems like a lot of calculations to do per-tick just for a viewbox...oh well, it looks nice.
	public void update() {
		//local variables, amirite
		AetherWorld world = game.getWorld();
//		Player player1 = world.getPlayer1();
//		Player player2 = world.getPlayer2();
		int index = 0;
		int count = game.getContext().getPlayerCount();
		float[] xs = new float[count];
		float[] ys = new float[count];
		for(Player player : world.getPlayers()) {
			Body body = player.getBody();
			Point2d pos = body.getPosition();
			float x = pos.getX();
			float y = pos.getY();
			xs[index] = x;
			ys[index] = y;
			index++;
		}
		float minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
		for(int i = 0; i < count; i++) {
			float x = xs[i];
			float y = ys[i];
			minX = Math.min(minX, x);
			maxX = Math.max(maxX, x);
			minY = Math.min(minY, y);
			maxY = Math.max(maxY, y);
		}
		float width = maxX - minX;
		float height = maxY - minY;
		float difX = (width * SCALE_BASE) - width;
		minX -= difX;
		maxX += difX;
		float difY = (height * SCALE_BASE) - height;
		minY -= difY;
		maxY += difY;
		float aMinX = minX;
		float aMaxX = maxX;
		float aMinY = minY;
		float aMaxY = maxY;
		width = maxX - minX;
		height = maxY - minY;
		minX = Math.min(minX, aMinX);
		maxX = Math.max(maxX, aMaxX);
		minY = Math.min(minY, aMinY);
		maxY = Math.max(maxY, aMaxY);
		if(width < MIN_WIDTH) {
			float extra = MIN_WIDTH - width;
			width = MIN_WIDTH;
			extra /= 2F;
			minX -= extra;
			maxX += extra;
		}
		if(height < MIN_HEIGHT) {
			float extra = MIN_HEIGHT - height;
			height = MIN_HEIGHT;
			extra /= 2F;
			minY -= extra;
			maxY += extra;
		}
		//fit to screen ratio
		GuiManager manager = game.getOwner();
		float x = manager.getWindowWidth();
		float y = manager.getWindowHeight();
		float ratio = y / x;
		float currentRatio = height / width;
		//adjust the viewbox to be bigger than necessary
		if(ratio > currentRatio) {
			//make it taller
			float newHeight = ratio * width;
			float dif = (newHeight - height) / 2F;
			minY -= dif;
			maxY += dif;
		} else {
			//make it wider
			float newWidth = height / ratio;
			float dif = (newWidth - width) / 2F;
			minX -= dif;
			maxX += dif;
		}
		float cX = (minX + maxX) / 2F;
		float cY = (minY + maxY) / 2F;
		viewBox = new BoundingBox2d(minX - cX, minY - cY, maxX - cX, maxY - cY);
		viewBox.setCenter(new Point2d(cX, cY));
	}
}
