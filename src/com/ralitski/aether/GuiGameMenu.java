package com.ralitski.aether;

import com.ralitski.util.gui.BoxPosition;
import com.ralitski.util.gui.Button;
import com.ralitski.util.gui.Component;
import com.ralitski.util.gui.ComponentEvent;
import com.ralitski.util.gui.ComponentEventListener;
import com.ralitski.util.gui.Frame;
import com.ralitski.util.gui.Gui;
import com.ralitski.util.gui.Panel;
import com.ralitski.util.gui.layout.BorderLayout;
import com.ralitski.util.gui.layout.BoxLayout;
import com.ralitski.util.gui.render.RenderStyleSimple;
import com.ralitski.util.render.img.Color;

public class GuiGameMenu extends Gui implements ComponentEventListener {
	
	private Frame top;
	
	public GuiGameMenu(Gui owner) {
		super(owner);
	}
	
	public void update() {
		super.update();
		parent.update();
	}
	
	public void init() {
		top = new Frame(this);
		top.setLayout(new BorderLayout());
		setTopLevel(top);
		Panel panel = new Panel(this);
		panel.setResizable(true);
		panel.setLayout(new BoxLayout());
		top.add(panel, "c");
		
		Button btnClose = new Button(this, 200, 50, "Close");
		panel.add(btnClose);
		btnClose.setId(1);
		btnClose.addComponentEventListener(this);

		Button btnExit = new Button(this, 200, 50, "Exit");
		panel.add(btnExit);
		btnExit.setId(2);
		btnExit.addComponentEventListener(this);
		
		RenderStyleSimple white = new RenderStyleSimple();
		white.setStyle("color", Color.WHITE);
		white.setClassType("white");
		btnExit.setRenderStyle(0, white);
		btnClose.setRenderStyle(0, white);

		top.refreshAll();
	}

	@Override
	public void onComponentEvent(ComponentEvent event) {
		Component c = event.getSource();
		int id = c.getId();
		if(id == 2) {
			//exit
			close();
			parent.close();
		} else if(id == 1) {
			close();
		}
	}
	
	public void onResize() {
		top.refresh();
	}

}
