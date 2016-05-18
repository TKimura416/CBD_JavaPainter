/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pgitc.painttest.plugins;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import org.pgitc.painttest.AbsImageWorkspace;
import org.pgitc.painttest.AbsToolbar;

/**
 *
 * @author Miracle
 */
public class DrawToolbar extends AbsToolbar {

    public static final String PROP_FILE_NAME = "attr.properties";
    
    int width = 1;
    
    ArrayList<String> widthList = new ArrayList<String>();
    
    public DrawToolbar() {
        for (int i = 0 ; i < 10 ; i ++)
            widthList.add(String.valueOf(i));

        Properties p = new Properties();

        File jarFile = new File(DrawToolbar.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String propFullPath = jarFile.getParentFile().getPath() + File.separator + PROP_FILE_NAME;

        try {
            InputStream in = new FileInputStream(new File(propFullPath));
            p.load(in);

            String floatableStr = p.getProperty("floatable", "false");
            setFloatable(Boolean.valueOf(floatableStr));

            String widthStr = p.getProperty("width");
            width = Integer.valueOf(widthStr);
        } catch (IOException ex) {
            Logger.getLogger(DrawToolbar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void configure(AbsImageWorkspace pad) {
        setName("DrawToolbar");

        ButtonGroup group = new ButtonGroup();
        AbstractButton penAction = new PencilButton("Pen", pad);
        add(penAction);
        group.add(penAction);

        AbstractButton rectAction = new RectButton("Rect", pad);
        add(rectAction);
        group.add(rectAction);

        addSeparator();
        
        JComboBox combo = new JComboBox(widthList.toArray());
        combo.setSelectedIndex(widthList.indexOf(String.valueOf(width)));
        combo.addActionListener((ActionEvent e) -> {
            try {
                int offset = ((JComboBox) e.getSource()).getSelectedIndex();
                
                Graphics2D graphics2D = pad.getGraphics2D();

                graphics2D.setStroke(new BasicStroke(Integer.valueOf(widthList.get(offset))));
                
                pad.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        add(combo);
    }

    @Override
    public String getToolbarName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
