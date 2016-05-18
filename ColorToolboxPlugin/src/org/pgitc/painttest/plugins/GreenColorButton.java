package org.pgitc.painttest.plugins;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JToggleButton;
import org.pgitc.painttest.AbsImageWorkspace;

public class GreenColorButton extends JToggleButton implements ActionListener {
    
    AbsImageWorkspace pad;

    final String name = "Green";
    final Color color = Color.green;
    
    public GreenColorButton(AbsImageWorkspace pad) {
        this.pad = pad;
        
        Icon icon = new DiamondIcon(color, true, 20, 20);
        setIcon(icon);
        setActionCommand(name);
        
        setFocusPainted(false);
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        
        Graphics2D graphics2D = pad.getGraphics2D();
        
        graphics2D.setPaint(color);
        pad.repaint();
//            try { pane.getStyledDocument(  ).insertString(0, 
//                  "Action [" + getValue(NAME) + "] performed!\n", null);
//            } catch (Exception ex) { ex.printStackTrace(  ); }
    }
}
