package org.pgitc.painttest.plugins;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JToggleButton;
import org.pgitc.painttest.AbsImageWorkspace;

public class MagentaColorButton extends JToggleButton implements ActionListener {
    
    AbsImageWorkspace pad;

    final String name = "Magenta";
    final Color color = Color.magenta;
    
    public MagentaColorButton(AbsImageWorkspace pad) {
        this.pad = pad;
        
        Icon icon = new DiamondIcon(color, true, 20, 20);
        setIcon(icon);
        setActionCommand(name);
        
        setFocusPainted(false);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pad.setPenColor(color);
    }
}
