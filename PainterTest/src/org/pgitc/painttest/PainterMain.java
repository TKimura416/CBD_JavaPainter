package org.pgitc.painttest;

import javax.swing.JFrame;

public class PainterMain extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -2591794482534888084L;

    public static void main(String[] args) {
        MainWindow frame = new MainWindow("Paint Test");

        frame.setLocationRelativeTo(null);
        //makes it so you can close
        frame.setVisible(true);
        //makes it so you can see it
    }
}
