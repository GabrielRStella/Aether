package com.ralitski.aether;

import com.ralitski.util.gui.Button;
import com.ralitski.util.gui.Component;
import com.ralitski.util.gui.ComponentEvent;
import com.ralitski.util.gui.ComponentEventListener;
import com.ralitski.util.gui.GuiManager;
import com.ralitski.util.gui.ImageCanvas;
import com.ralitski.util.gui.Panel;
import com.ralitski.util.gui.layout.CenterLayout;
import com.ralitski.util.render.img.Image;

public class GuiMainMenu extends GuiMenu implements ComponentEventListener {

	private AetherDisplay display;
	
	public GuiMainMenu(GuiManager owner, AetherDisplay display) {
		super(owner);
		this.display = display;
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
		btnPlay.setRenderStyle(1, style_button_text);
		canvas.setRenderStyle(0, style_colored);
		canvas2.setRenderStyle(0, style_white);
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
		super.update();
	}
}
