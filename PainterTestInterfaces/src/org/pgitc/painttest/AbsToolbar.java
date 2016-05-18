/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pgitc.painttest;

import javax.swing.JToolBar;

public abstract class AbsToolbar extends JToolBar {
    
    public abstract String getToolbarName();
    
    public abstract void configure(AbsImageWorkspace pad);
}
