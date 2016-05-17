package org.pgitc.painttest.colorplugins;

import java.awt.Color;
import java.awt.Graphics2D;
import org.pgitc.painttest.AbsImageWorkspace;
import org.pgitc.painttest.PluginInterface;

public class SetMagentaColorPlugin implements PluginInterface{

    @Override
    public String getPluginName() {
        return "Magenta";
    }

    @Override
    public void processImage(AbsImageWorkspace pad) {
        Graphics2D graphics2D = pad.getGraphics2D();
        
        graphics2D.setPaint(Color.magenta);
        pad.repaint();
    }
}
