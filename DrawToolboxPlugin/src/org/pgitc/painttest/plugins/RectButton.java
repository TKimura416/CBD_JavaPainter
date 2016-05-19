package org.pgitc.painttest.plugins;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;
import org.pgitc.painttest.AbsImageWorkspace;

public class RectButton extends JToggleButton implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 2719884230307830000L;

    AbsImageWorkspace pad;

    public RectButton(String text, AbsImageWorkspace pad) {
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

            @Override
            public void mouseReleased(MouseEvent e) {
                Graphics2D graphics2D = pad.getGraphics2D();
                currentX = e.getX();
                currentY = e.getY();

                int startX = Math.min(oldX, currentX);
                int startY = Math.min(oldY, currentY);
                int width = Math.abs(oldX - currentX);
                int height = Math.abs(oldY - currentY);

                if (graphics2D != null) {
                    graphics2D.drawRect(startX, startY, width, height);
                }
                pad.repaint();
            }
        });
    }
}
