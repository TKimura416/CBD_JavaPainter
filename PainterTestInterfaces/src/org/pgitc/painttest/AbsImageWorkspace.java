package org.pgitc.painttest;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public abstract class AbsImageWorkspace extends JComponent {

    /**
     *
     */
    private static final long serialVersionUID = -6111794225114604701L;

    public abstract void removeAllMouseListeners();

    public abstract Graphics2D getGraphics2D();
    
    public abstract void setPenWidth(int width);
    
    public abstract void setPenColor(Color color);
}
