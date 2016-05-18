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
public class DrawToolbar extends AbsToolbar {
    
    @Override
    public void configure(AbsImageWorkspace pad) {
        setName("DrawToolbar");

        ButtonGroup group = new ButtonGroup();
        AbstractButton penAction = new PencilButton("Pen", pad);
        this.add(penAction);
        group.add(penAction);
        
        AbstractButton rectAction = new RectButton("Rect", pad);
        this.add(rectAction);
        group.add(rectAction);
    }

    @Override
    public String getToolbarName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
