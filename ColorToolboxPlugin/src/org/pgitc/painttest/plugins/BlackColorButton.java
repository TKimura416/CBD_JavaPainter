package org.pgitc.painttest.plugins;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JToggleButton;
import org.pgitc.painttest.AbsImageWorkspace;

public class BlackColorButton extends JToggleButton implements ActionListener {

    AbsImageWorkspace pad;

    final String name = "Black";
    final Color color = Color.black;

    public BlackColorButton(AbsImageWorkspace pad) {
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
