/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pgitc.painttest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public class Workspace extends AbsImageWorkspace {

    /**
     *
     */
    private static final long serialVersionUID = -3389679010006873237L;

    Image image;
    //this is gonna be your image that you draw on
    Graphics2D graphics2D;
    //this is what we'll be using to draw on

    //Now for the constructors
    public Workspace() {
        setDoubleBuffered(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            graphics2D = (Graphics2D) image.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }
    //this is the painting bit
    //if it has nothing on it then
    //it creates an image the size of the window
    //sets the value of Graphics as the image
    //sets the rendering
    //runs the clear() method
    //then it draws the image

    public void clear() {
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
        repaint();
    }

    @Override
    public void removeAllMouseListeners() {
        MouseListener[] mouseListeners = getMouseListeners();
        for (MouseListener mouseListener : mouseListeners) {
            removeMouseListener(mouseListener);
        }

        MouseMotionListener[] mouseMotionListeners = getMouseMotionListeners();
        for (MouseMotionListener mouseMotionListener : mouseMotionListeners) {
            removeMouseMotionListener(mouseMotionListener);
        }

        MouseWheelListener[] mouseWheelListeners = getMouseWheelListeners();
        for (MouseWheelListener mouseWheelListener : mouseWheelListeners) {
            removeMouseWheelListener(mouseWheelListener);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Graphics2D getGraphics2D() {
        return graphics2D;
    }
}
