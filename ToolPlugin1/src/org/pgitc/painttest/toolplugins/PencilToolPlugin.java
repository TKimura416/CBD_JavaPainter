package org.pgitc.painttest.toolplugins;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import org.pgitc.painttest.AbsImageWorkspace;
import org.pgitc.painttest.PluginInterface;

public class PencilToolPlugin implements PluginInterface {

    @Override
    public String getPluginName() {
        return "Pencil";
    }

    int currentX, currentY, oldX, oldY;
    //these are gonna hold our mouse coordinates

    @Override
    public void processImage(AbsImageWorkspace pad) {
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
