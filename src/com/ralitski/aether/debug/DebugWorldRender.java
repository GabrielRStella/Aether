package com.ralitski.aether.debug;

import org.lwjgl.opengl.GL11;

import com.ralitski.aether.Body;
import com.ralitski.aether.Planet;
import com.ralitski.aether.Player;
import com.ralitski.aether.WorldRender;
import com.ralitski.util.math.geom.d2.BoundingBox2d;
import com.ralitski.util.math.geom.d2.Point2d;
import com.ralitski.util.render.img.Color;
import com.ralitski.util.render.list.GLListHelper;
import com.ralitski.util.render.list.TexturedCenteredSquareRenderListCW;

public class DebugWorldRender implements WorldRender {
	
	public DebugWorldRender() {
	}

	@Override
	public void renderBackground(BoundingBox2d box, float rot) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPushMatrix();
		GL11.glTranslatef(box.getMinX(), box.getMinY(), 0);
		GL11.glScalef(box.getWidth(), box.getHeight(), 1);
		Color.GRAY.glColor();
		GLListHelper.getSquareListUncenteredCW().call();
		GL11.glPopMatrix();
	}

	@Override
	public void renderPlanet(Planet planet) {
		renderBody(planet.getBody());
	}

	@Override
	public void renderPlayer1(Player player) {
		renderBody(player.getBody());
	}

	@Override
	public void renderPlayer2(Player player) {
		renderBody(player.getBody());
	}
	
	private void renderBody(Body body) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPushMatrix();
		Point2d pos = body.getShape().getPosition();
		GL11.glTranslatef(pos.getX(), pos.getY(), 0);
		GL11.glScalef(10, 10, 1);
		body.getColor().glColor();
		TexturedCenteredSquareRenderListCW.FULL.call();
//		GLListHelper.getSquareListCenteredCW().call();
		GL11.glPopMatrix();
	}

}
