package org.pgitc.painttest;

public interface PluginInterface {
    public String getPluginName();
    
    public void processImage(AbsImageWorkspace pad);
}
