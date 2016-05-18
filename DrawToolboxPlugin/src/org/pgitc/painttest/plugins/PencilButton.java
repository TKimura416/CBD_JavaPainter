package org.pgitc.painttest.plugins;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JToggleButton;
import org.pgitc.painttest.AbsImageWorkspace;

public class PencilButton extends JToggleButton implements ActionListener{

    AbsImageWorkspace pad;

    public PencilButton(String text, AbsImageWorkspace pad) {
        super(text);
        
        this.pad = pad;
        
        setFocusPainted(false);
        this.addActionListener(this);
    }

    int currentX, currentY, oldX, oldY;
    //these are gonna hold our mouse coordinates

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        pad.removeAllMouseListeners();
        
        pad.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });
        pad.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics2D graphics2D = pad.getGraphics2D();
                currentX = e.getX();
                currentY = e.getY();
                if (graphics2D != null) {
                    graphics2D.drawLine(oldX, oldY, currentX, currentY);
                }
                pad.repaint();
                oldX = currentX;
                oldY = currentY;
            }
        });
    }
}
