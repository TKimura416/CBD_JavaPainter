package org.pgitc.painttest;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class MainWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 2719884340307829998L;

    final Workspace drawPad = new Workspace();
    //creates a new padDraw, which is pretty much the paint program

    private JarFile jarFile;

    private ClassLoader cl;

    // a list where we keep an initialized object of each plugin class
    public MainWindow(String title) {
        super(title);

        addMenu();

        setContent();

        loadToolbar();

        if (false)
            loadPlugins();
        
        setSize(600, 600);
        //sets the size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void decorateButton(final AbstractButton button) {
        Dimension BUTTON_SIZE = new Dimension(50, 80);
        button.putClientProperty("hideActionText", Boolean.TRUE);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setBackground(null);
        button.setOpaque(true);
        button.setPreferredSize(BUTTON_SIZE);
        button.setMaximumSize(BUTTON_SIZE);
        button.setMinimumSize(BUTTON_SIZE);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
//            button.setBackground(COLOR_BUTTON_MOUSEOVER);
            }

            @Override
            public void mousePressed(MouseEvent e) {
//            button.setBackground(COLOR_BUTTON_PRESSED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//            button.setBorder(button.isEnabled() ? BORDER_BUTTON_MOUSEOVER_ENABLED : BORDER_BUTTON_MOUSEOVER_DISABLED);
//            button.setBackground(COLOR_BUTTON_MOUSEOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setBackground(null);
            }
        });
    }

    private void loadToolbar() {

        String pluginsDir = "plugins";

        File mainJarFile = new File(MainWindow.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String pluginDirPath = mainJarFile.getParentFile().getPath() + File.separator + pluginsDir;

        File pluginDir = new File(pluginDirPath);
        if (!pluginDir.exists()) {
            return;
        }
        File[] directories = pluginDir.listFiles((File file) -> file.isDirectory());
        cl = new PluginJarLoader(pluginDir.getPath());
        for (File directory : directories) {
            if (directory.exists() && directory.isDirectory()) {
                // we'll only load classes directly in this directory -
                // no subdirectories, and no classes in packages are recognized
                String[] files = directory.list();
                for (String file : files) {
                    try {
                        // only consider files ending in ".jar"
                        if (!file.endsWith(".jar")) {
                            continue;
                        }
                        jarFile = new JarFile(directory.getPath() + File.separator + file);
                        Enumeration<?> allEntries = jarFile.entries();
                        while (allEntries.hasMoreElements()) {
                            JarEntry entry = (JarEntry) allEntries.nextElement();
                            // only consider files ending in ".class"
                            if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                                String name = entry.getName();
                                String className = name.replace('/', '.');
                                className = className.substring(0, className.length() - ".class".length());

                                Class<?> c = cl.loadClass(className);
                                Class<?> intf = c.getSuperclass();
//                                for (Class<?> intf1 : intf) {
                                if (intf.getName().contains("AbsToolbar")) {
                                    try {
                                        final AbsToolbar toolbar = (AbsToolbar) c.newInstance();
                                        toolbar.configure(drawPad);

                                        getContentPane().add(toolbar, BorderLayout.NORTH);
                                    } catch (InstantiationException | IllegalAccessException ex) {
                                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
//                                }
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.err.println("File " + file + " does not contain a valid PluginInterface class.");
                    }
                }
            }
        }
    }

    private void loadPlugins() {
        String pluginsDir = "plugins";

        File mainJarFile = new File(MainWindow.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String pluginDirPath = mainJarFile.getParentFile().getPath() + File.separator + pluginsDir;

        File pluginDir = new File(pluginDirPath);
        if (!pluginDir.exists()) {
            return;
        }
        MenuBar menubar = this.getMenuBar();

        File[] directories = pluginDir.listFiles((File file) -> file.isDirectory());
        cl = new PluginJarLoader(pluginDir.getPath());
        for (File directory : directories) {
            Menu pluginMenu = new Menu(directory.getName());

            if (directory.exists() && directory.isDirectory()) {
                // we'll only load classes directly in this directory -
                // no subdirectories, and no classes in packages are recognized
                String[] files = directory.list();
                for (String file : files) {
                    try {
                        // only consider files ending in ".jar"
                        if (!file.endsWith(".jar")) {
                            continue;
                        }
                        jarFile = new JarFile(directory.getPath() + File.separator + file);
                        Enumeration<?> allEntries = jarFile.entries();
                        while (allEntries.hasMoreElements()) {
                            JarEntry entry = (JarEntry) allEntries.nextElement();
                            // only consider files ending in ".class"
                            if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                                String name = entry.getName();
                                String className = name.replace('/', '.');
                                className = className.substring(0, className.length() - ".class".length());

                                Class<?> c = cl.loadClass(className);
                                Class<?>[] intf = c.getInterfaces();
                                for (Class<?> intf1 : intf) {
                                    if (intf1.getName().contains("PluginInterface")) {
                                        try {
                                            final PluginInterface drawInterface = (PluginInterface) c.newInstance();
                                            pluginMenu.add(new MenuItem(drawInterface.getPluginName())).addActionListener((ActionEvent e) -> {
                                                drawInterface.processImage(drawPad);
                                            });

                                        } catch (InstantiationException | IllegalAccessException ex) {
                                            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.err.println("File " + file + " does not contain a valid PluginInterface class.");
                    }
                }
            }

            if (pluginMenu.getItemCount() > 0) {
                menubar.add(pluginMenu);
            }
        }
    }

    /**
     * This method creates menu bar and menu items and then attach the menu bar
     * with the frame of this drawing tool.
     */
    private void addMenu() {
        //Add menu bar to our frame
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");

        //now add menu items to these Menu objects
        file.add(new MenuItem("Exit")).addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        edit.add(new MenuItem("Clear")).addActionListener((ActionEvent e) -> {
            drawPad.clear();
        });

        //add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);
        //menuBar.setVisible(true);
        if (null == this.getMenuBar()) {
            this.setMenuBar(menuBar);
        }
    }//addMenu()

    private void setContent() {
        Container content = getContentPane();
        //Creates a new container
        content.setLayout(new MultiBorderLayout(0, 0));
        //sets the layout

        content.add(drawPad, BorderLayout.CENTER);
        //sets the padDraw in the center
    }
}
