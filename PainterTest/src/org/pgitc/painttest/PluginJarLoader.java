package org.pgitc.painttest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public final class PluginJarLoader extends URLClassLoader {

    protected String path;

    /**
     * Creates a new PluginClassLoader that searches in the directory path
     * passed as a parameter. The constructor automatically finds all JAR and
     * ZIP files in the path and first level of subdirectories. The JAR and ZIP
     * files are stored in a Vector for future searches.
     *
     * @param path the path to the plugins directory.
     */
    public PluginJarLoader(String path) {
        super(new URL[0], MainWindow.class.getClassLoader());
        init(path);
    }

    /**
     * This version of the constructor is used when ImageJ is launched using
     * Java WebStart.
     * @param path
     * @param callSuper
     */
    public PluginJarLoader(String path, boolean callSuper) {
        super(new URL[0], Thread.currentThread().getContextClassLoader());
        init(path);
    }

    void init(String path) {
        this.path = path;

        //find all JAR files on the path and subdirectories
        File f = new File(path);
        try {
            // Add plugin directory to search path
            addURL(f.toURI().toURL());
        } catch (MalformedURLException e) {
        }
        String[] list = f.list();
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(".rsrc")) {
                continue;
            }
            File f2 = new File(path, list[i]);
            if (f2.isDirectory()) {
                addDirectory(f2);
            } else {
                addJar(f2);
            }
        }
        addDirectory(f, "jars"); // add ImageJ/jars; requested by Wilhelm Burger
    }

    private void addDirectory(File f) {
        //if (IJ.debugMode) IJ.log("PluginClassLoader.addDirectory: "+f);
        try {
            // Add first level subdirectories to search path
            addURL(f.toURI().toURL());
        } catch (MalformedURLException e) {
        }
        String[] innerlist = f.list();
        if (innerlist == null) {
            return;
        }
        for (int j = 0; j < innerlist.length; j++) {
            File g = new File(f, innerlist[j]);
            if (g.isFile()) {
                addJar(g);
            }
        }
    }

    private void addJar(File f) {
        if (f.getName().endsWith(".jar") || f.getName().endsWith(".zip")) {
            //if (IJ.debugMode) IJ.log("PluginClassLoader.addJar: "+f);
            try {
                addURL(f.toURI().toURL());
            } catch (MalformedURLException e) {
            }
        }
    }

    private void addDirectory(File f, String name) {
        f = f.getParentFile();
        if (f == null) {
            return;
        }
        f = new File(f, name);
        if (f.isDirectory()) {
            addDirectory(f);
        }
    }
}
