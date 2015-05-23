package com.ralitski.aether;

import com.ralitski.util.gui.BoxPosition;
import com.ralitski.util.gui.Button;
import com.ralitski.util.gui.Component;
import com.ralitski.util.gui.ComponentEvent;
import com.ralitski.util.gui.ComponentEventListener;
import com.ralitski.util.gui.Frame;
import com.ralitski.util.gui.Gui;
import com.ralitski.util.gui.render.RenderStyleSimple;
import com.ralitski.util.render.img.Color;

public class GuiGameMenu extends Gui implements ComponentEventListener {
	
	public GuiGameMenu(Gui owner) {
		super(owner);
	}
	
	public void init() {
		Frame top = new Frame(this);
		setTopLevel(top);

		Button btnExit = new Button(this, 200, 100, "Exit");
		top.add(btnExit);
		btnExit.setId(1);
		btnExit.addComponentEventListener(this);
		
		Button btnClose = new Button(this, 200, 100, "Close");
		top.add(btnClose);
		btnClose.setId(2);
		btnClose.addComponentEventListener(this);
		
		RenderStyleSimple white = new RenderStyleSimple();
		white.setStyle("color", Color.WHITE);
		white.setClassType("white");
		btnExit.setRenderStyle(0, white);
		btnClose.setRenderStyle(0, white);
		BoxPosition.CENTER.position(btnExit.getBounds(), null, 0, top.getBounds());
		BoxPosition.CENTER.position(btnClose.getBounds(), null, 0, top.getBounds());
		BoxPosition.BELOW.position(btnClose.getBounds(), btnExit.getBounds(), 10, top.getBounds());
		
		top.refresh();
	}

	@Override
	public void onComponentEvent(ComponentEvent event) {
		Component c = event.getSource();
		int id = c.getId();
		if(id == 1) {
			//exit
			close();
			parent.close();
		} else if(id == 2) {
			close();
		}
	}

}
