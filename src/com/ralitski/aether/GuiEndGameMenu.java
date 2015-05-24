package com.ralitski.aether;

import com.ralitski.util.gui.Button;
import com.ralitski.util.gui.Component;
import com.ralitski.util.gui.ComponentEvent;
import com.ralitski.util.gui.ComponentEventListener;
import com.ralitski.util.gui.Gui;
import com.ralitski.util.gui.Panel;
import com.ralitski.util.gui.Text;
import com.ralitski.util.gui.render.RenderStyleSimple;
import com.ralitski.util.render.img.Color;

public class GuiEndGameMenu extends GuiMenu implements ComponentEventListener {

	public GuiEndGameMenu(Gui parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	public void update() {
		super.update();
		parent.update();
	}
	
	public void doInit(Panel panel) {
		Text gameOver = new Text(this, "Game Over");
		panel.add(gameOver);

		Button btnExit = new Button(this, 200, 50, "Exit");
		panel.add(btnExit);
		btnExit.setId(2);
		btnExit.addComponentEventListener(this);

		btnExit.setRenderStyle(0, style_button);
		gameOver.setRenderStyle(0, style_text);
	}

	@Override
	public void onComponentEvent(ComponentEvent event) {
		Component c = event.getSource();
		int id = c.getId();
		if(id == 2) {
			//exit
			close();
			parent.close();
		}
	}
	
	protected boolean renderBg() {
		return false;
	}

}
