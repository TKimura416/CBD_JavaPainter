package org.pgitc.painttest.colorplugins;

import java.awt.Color;
import java.awt.Graphics2D;
import org.pgitc.painttest.AbsImageWorkspace;
import org.pgitc.painttest.PluginInterface;

public class SetRedColorPlugin implements PluginInterface{

    @Override
    public String getPluginName() {
        return "Red";
    }

    @Override
    public void processImage(AbsImageWorkspace pad) {
        Graphics2D graphics2D = pad.getGraphics2D();
        
        graphics2D.setPaint(Color.red);
        pad.repaint();
    }
    
}
