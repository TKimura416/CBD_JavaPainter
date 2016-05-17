package org.pgitc.painttest.colorplugins;

import java.awt.Color;
import java.awt.Graphics2D;
import org.pgitc.painttest.AbsImageWorkspace;
import org.pgitc.painttest.PluginInterface;

public class SetGreenColorPlugin implements PluginInterface{

    @Override
    public String getPluginName() {
        return "Green";
    }

    @Override
    public void processImage(AbsImageWorkspace pad) {
        Graphics2D graphics2D = pad.getGraphics2D();
        
        graphics2D.setPaint(Color.green);
        pad.repaint();
    }
    
}
