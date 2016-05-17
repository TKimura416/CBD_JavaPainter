package org.pgitc.painttest.toolplugins;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.pgitc.painttest.PanelInterface;
import org.pgitc.painttest.PluginInterface;

public class RectToolPlugin implements PluginInterface {

    @Override
    public String getPluginName() {
        return "Rect";
    }

    int currentX, currentY, oldX, oldY;
    //these are gonna hold our mouse coordinates

    @Override
    public void processImage(PanelInterface pad) {
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
