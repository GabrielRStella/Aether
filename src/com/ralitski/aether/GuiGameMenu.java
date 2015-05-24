package com.ralitski.aether;

import com.ralitski.util.gui.Button;
import com.ralitski.util.gui.Component;
import com.ralitski.util.gui.ComponentEvent;
import com.ralitski.util.gui.ComponentEventListener;
import com.ralitski.util.gui.Gui;
import com.ralitski.util.gui.Panel;
import com.ralitski.util.gui.Text;

public class GuiGameMenu extends GuiMenu implements ComponentEventListener {
	
	public GuiGameMenu(Gui owner) {
		super(owner);
	}
	
	public void update() {
		super.update();
		parent.update();
	}
	
	public void doInit(Panel panel) {
		Text paused = new Text(this, "Paused");
		panel.add(paused);
		
		Button btnClose = new Button(this, 200, 50, "Close");
		panel.add(btnClose);
		btnClose.setId(1);
		btnClose.addComponentEventListener(this);

		Button btnExit = new Button(this, 200, 50, "Exit");
		panel.add(btnExit);
		btnExit.setId(2);
		btnExit.addComponentEventListener(this);
		
		btnExit.setRenderStyle(0, style_button);
		btnExit.setRenderStyle(1, style_button_text);
		btnClose.setRenderStyle(0, style_button);
		btnClose.setRenderStyle(1, style_button_text);
		paused.setRenderStyle(0, style_text);
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
	
	protected boolean renderBg() {
		return false;
	}

}
