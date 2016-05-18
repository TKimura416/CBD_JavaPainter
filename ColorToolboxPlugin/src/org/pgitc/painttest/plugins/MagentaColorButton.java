package org.pgitc.painttest.plugins;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;
import org.pgitc.painttest.AbsImageWorkspace;

public class MagentaColorButton extends JToggleButton implements ActionListener {
    
    AbsImageWorkspace pad;

    public MagentaColorButton(String text, AbsImageWorkspace pad) {
        super(text);
        
        this.pad = pad;
        
        setFocusPainted(false);
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        
        Graphics2D graphics2D = pad.getGraphics2D();
        
        graphics2D.setPaint(Color.magenta);
        pad.repaint();
//            try { pane.getStyledDocument(  ).insertString(0, 
//                  "Action [" + getValue(NAME) + "] performed!\n", null);
//            } catch (Exception ex) { ex.printStackTrace(  ); }
    }
}
