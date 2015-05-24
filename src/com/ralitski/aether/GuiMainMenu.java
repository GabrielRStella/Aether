package com.ralitski.aether;

import com.ralitski.util.Ticker;
import com.ralitski.util.gui.Button;
import com.ralitski.util.gui.Component;
import com.ralitski.util.gui.ComponentEvent;
import com.ralitski.util.gui.ComponentEventListener;
import com.ralitski.util.gui.GuiManager;
import com.ralitski.util.gui.ImageCanvas;
import com.ralitski.util.gui.Panel;
import com.ralitski.util.gui.layout.CenterLayout;
import com.ralitski.util.gui.render.RenderStyleSimple;
import com.ralitski.util.render.img.Color;
import com.ralitski.util.render.img.Image;

public class GuiMainMenu extends GuiMenu implements ComponentEventListener {

	private AetherDisplay display;
	private ColorTransition colors;
	private Color color;
	
	public GuiMainMenu(GuiManager owner, AetherDisplay display) {
		super(owner);
		this.display = display;
		colors = new ColorTransition(new ColorsFruity(), Ticker.ticksPerSecond(0.2F));
	}
	
	public void doInit(Panel panel) {
		
		Panel imgPanel = new Panel(this);
		imgPanel.setResizable(true);
		imgPanel.setLayout(new CenterLayout());
		panel.add(imgPanel);

		Image img = Image.loadImage("./src/res/logo_bg_solid.png");
		ImageCanvas canvas = new ImageCanvas(this, img, 0.75F);
		imgPanel.add(canvas);
		
		Image img2 = Image.loadImage("./src/res/logo.png");
		ImageCanvas canvas2 = new ImageCanvas(this, img2, 0.75F);
		imgPanel.add(canvas2);
		
		Button btnPlay = new Button(this, 200, 50, "Play");
		panel.add(btnPlay);
		btnPlay.setId(1);
		btnPlay.addComponentEventListener(this);
		
		btnPlay.setRenderStyle(0, style_button);
		canvas.setRenderStyle(0, style_button);

		RenderStyleSimple colored = new RenderStyleSimple();
		colored.setStyle("color", color = colors.next());
		colored.setClassType("colored");
		canvas2.setRenderStyle(0, colored);
	}

	@Override
	public void onComponentEvent(ComponentEvent event) {
		Component c = event.getSource();
		int id = c.getId();
		if(id == 1) {
			//play
			display.newGame(this);
		}
	}
	
	public void update() {
		Color c = colors.next();
		color.setRed(c.getRed());
		color.setGreen(c.getGreen());
		color.setBlue(c.getBlue());
	}
}
