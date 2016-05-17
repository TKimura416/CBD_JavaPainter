package org.pgitc.painttest.colorplugins;

import java.awt.Color;
import java.awt.Graphics2D;
import org.pgitc.painttest.PanelInterface;
import org.pgitc.painttest.PluginInterface;

public class SetGreenColorPlugin implements PluginInterface{

    @Override
    public String getPluginName() {
        return "Green";
    }

    @Override
    public void processImage(PanelInterface pad) {
        Graphics2D graphics2D = pad.getGraphics2D();
        
        graphics2D.setPaint(Color.green);
        pad.repaint();
    }
    
}
