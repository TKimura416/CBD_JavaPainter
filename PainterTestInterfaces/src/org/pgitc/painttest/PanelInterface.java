package org.pgitc.painttest;

import java.awt.Graphics2D;
import javax.swing.JComponent;

public abstract class PanelInterface extends JComponent{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6111794225114604701L;

	public abstract void removeAllMouseListeners();
    
    public abstract Graphics2D getGraphics2D();
}
