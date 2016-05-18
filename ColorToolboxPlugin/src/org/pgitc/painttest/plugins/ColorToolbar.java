/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pgitc.painttest.plugins;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import org.pgitc.painttest.AbsImageWorkspace;
import org.pgitc.painttest.AbsToolbar;

/**
 *
 * @author Miracle
 */
public class ColorToolbar extends AbsToolbar {
    
    @Override
    public void configure(AbsImageWorkspace pad) {
        setName("ColorToolbar");
        
        ButtonGroup group = new ButtonGroup();
        AbstractButton blackAction = new BlackColorButton(pad);
        this.add(blackAction);
        group.add(blackAction);
        
        AbstractButton blueColorAction = new BlueColorButton(pad);
        this.add(blueColorAction);
        group.add(blueColorAction);
        
        AbstractButton greenColorAction = new GreenColorButton(pad);
        this.add(greenColorAction);
        group.add(greenColorAction);
        
        AbstractButton redColorAction = new RedColorButton(pad);
        this.add(redColorAction);
        group.add(redColorAction);
        
        AbstractButton magentaColorAction = new MagentaColorButton(pad);
        this.add(magentaColorAction);
        group.add(magentaColorAction);
    }

    @Override
    public String getToolbarName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
