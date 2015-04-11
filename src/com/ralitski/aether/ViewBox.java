package com.ralitski.aether;

import com.ralitski.util.gui.GuiManager;
import com.ralitski.util.math.geom.d2.BoundingBox2d;
import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.math.geom.d2.Vector2d;

public class ViewBox {
	
	public static final float SCALE_BASE = 1.5F;
	public static final float SCALE_SPEED = 7F;
	
	public static final float MIN_WIDTH = 100;
	public static final float MIN_HEIGHT = 100;

	private AetherGame game;
	private BoundingBox2d viewBox;
	
	private Vector2d prevVelocity1 = new Vector2d();
	private Vector2d prevVelocity2 = new Vector2d();
	
	public ViewBox(AetherGame game) {
		this.game = game;
		viewBox = new BoundingBox2d();
	}
	
	public BoundingBox2d getViewBox() {
		return viewBox;
	}

	//this seems like a lot of calculations to do per-tick just for a viewbox...oh well, hopefully it'll look nice.
	public void update() {
		//local variables, amirite
		AetherWorld world = game.getWorld();
		Player player1 = world.getPlayer1();
		Player player2 = world.getPlayer2();
		Body body1 = player1.getBody();
		Point2d pos1 = body1.getPosition();
		Vector2d v1 = body1.getVelocity();
		Vector2d temp = prevVelocity1;
		prevVelocity1 = v1;
		v1 = v1.subtractCopy(temp);
		Body body2 = player2.getBody();
		Point2d pos2 = body2.getPosition();
		Vector2d v2 = body2.getVelocity();
		temp = prevVelocity2;
		prevVelocity2 = v2;
		v2 = v2.subtractCopy(temp);
		float x1 = pos1.getX();
		float x2 = pos2.getX();
		float y1 = pos1.getY();
		float y2 = pos2.getY();
		float minX, maxX, minY, maxY;
		float minVX, maxVX, minVY, maxVY;
		if(x1 < x2) {
			minX = x1;
			maxX = x2;
			minVX = v1.getX();
			maxVX = v2.getX();
		} else {
			minX = x2;
			maxX = x1;
			minVX = v2.getX();
			maxVX = v1.getX();
		}
		if(y1 < y2) {
			minY = y1;
			maxY = y2;
			minVY = v1.getY();
			maxVY = v2.getY();
		} else {
			minY = y2;
			maxY = y1;
			minVY = v2.getY();
			maxVY = v1.getY();
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
		minX += minVX * SCALE_SPEED;
		maxX += maxVX * SCALE_SPEED;
		minY += minVY * SCALE_SPEED;
		maxY += maxVY * SCALE_SPEED;
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
		GuiManager manager = game.getOwner().getOwner();
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
		viewBox = new BoundingBox2d(minX, minY, maxX, maxY);
	}
}
