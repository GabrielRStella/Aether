package com.ralitski.aether;

import com.ralitski.util.Ticker;
import com.ralitski.util.gui.Frame;
import com.ralitski.util.gui.Gui;
import com.ralitski.util.gui.GuiManager;
import com.ralitski.util.gui.GuiOwner;
import com.ralitski.util.gui.Panel;
import com.ralitski.util.gui.layout.BorderLayout;
import com.ralitski.util.gui.layout.BoxLayout;
import com.ralitski.util.gui.render.ImmutableStyle;
import com.ralitski.util.gui.render.RenderStyle;
import com.ralitski.util.gui.render.RenderStyleSimple;
import com.ralitski.util.render.img.Color;

public abstract class GuiMenu extends Gui {

	public static final RenderStyle style_button = new ImmutableStyle(
			new RenderStyleSimple()
			.setStyle("color", Color.WHITE)
			.setStyle("font-size", 30)
			.setClassType("menu_button")
		);
	public static final RenderStyle style_text = new ImmutableStyle(
			new RenderStyleSimple()
			.setStyle("color", Color.WHITE)
			.setStyle("font-color", Color.WHITE)
			.setStyle("font-size", 70)
			.setClassType("menu_text")
		);
	
	private static final ColorTransition colors = new ColorTransition(new ColorsPastel(), Ticker.ticksPerSecond(0.1F));
	
	/* 
	 * TODO: standard cascading styles
	 */

	private Frame top;
	
	public GuiMenu(GuiManager owner) {
		super(owner);
	}
	
	public GuiMenu(Gui parent) {
		super(parent);
	}
	
	public void init() {
		top = new Frame(this);
		top.setLayout(new BorderLayout());
		top.setResizable(true);
		setTopLevel(top);
		Panel panel = new Panel(this);
		panel.setResizable(true);
		BoxLayout layout = new BoxLayout();
		layout.setSpaceX(10);
		layout.setSpaceY(10);
		panel.setLayout(layout);
		top.add(panel, "c");
		
		doInit(panel);
		
		top.refreshAll();
	}
	
	protected abstract void doInit(Panel panel);
	
	public void onOpen(boolean reentry) {
		top.refresh();
		colors.next();
	}
	
	public void onResize() {
		top.refresh();
	}
	
	protected boolean renderBg() {
		return true;
	}
	
	public void render2d(float partial) {
		//render bg
		if(renderBg()) {
			GuiManager manager = getOwner();
			GuiOwner owner = manager.getGuiOwner();
			owner.drawBox(manager.getWindow(), colors.next());
		}
		//render stuff
		super.render2d(partial);
	}

}
