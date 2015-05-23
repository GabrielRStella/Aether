package com.ralitski.aether;

import com.ralitski.util.gui.BoxPosition;
import com.ralitski.util.gui.Button;
import com.ralitski.util.gui.Component;
import com.ralitski.util.gui.ComponentEvent;
import com.ralitski.util.gui.ComponentEventListener;
import com.ralitski.util.gui.Frame;
import com.ralitski.util.gui.Gui;
import com.ralitski.util.gui.GuiManager;
import com.ralitski.util.gui.render.RenderStyleSimple;
import com.ralitski.util.render.img.Color;

public class GuiMainMenu extends Gui implements ComponentEventListener {

	private AetherDisplay display;
	
	public GuiMainMenu(GuiManager owner, AetherDisplay display) {
		super(owner);
		this.display = display;
	}
	
	public void init() {
		Frame top = new Frame(this);
		setTopLevel(top);
		
		Button btnPlay = new Button(this, 200, 100, "Play asdhga sdhjasgdasdkjag ");
		top.add(btnPlay);
		btnPlay.setId(1);
		btnPlay.addComponentEventListener(this);
		
		RenderStyleSimple white = new RenderStyleSimple();
		white.setStyle("color", Color.WHITE);
		white.setClassType("white");
		btnPlay.setRenderStyle(0, white);
		BoxPosition.CENTER.position(btnPlay.getBounds(), null, 0, top.getBounds());
		
		RenderStyleSimple black = new RenderStyleSimple();
		black.setStyle("color", Color.GRAY);
		black.setClassType("black");
		top.setRenderStyle(0, black);
		top.setRenderSelf(true);
		
		top.refresh();
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
	
	

}
