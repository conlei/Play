package com.mmclcs;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.time.LocalDateTime;

/**
 * Jogl initalizer and window creator
 * Created by mackenzie on 17/05/17.
 */
public class GuiCore extends JFrame implements GLEventListener {

    private static boolean initialized = false;
    private static GLProfile profile;
    private int width;
    private int height;

    /**
     * Initialize open gl, must be done before using any other method this class offers
     */
    public static void init() {
        profile = GLProfile.get(GLProfile.GL2);
        initialized = true;
    }

    /**
     * Creates a new OpenGl context
     *
     * @return OpenGl JFrame
     * @throws IllegalStateException if GCore has not been initialized.
     */
    public static GuiCore makeNewContext() throws IllegalStateException {
        log("Request made for a new graphics context");
        if (!initialized) throw new IllegalStateException("Graphics engine not initialized yet");
        GLCapabilities capabilities = new GLCapabilities(profile);
        return new GuiCore(new GLCanvas(capabilities));
    }

    private static void log(String msg) {
        LocalDateTime item = LocalDateTime.now();
        System.out.println("[GCore: " + item.getMonth().name() + " " +
                item.getDayOfMonth() + ", " +
                item.getHour() + ":" +
                item.getMinute() + ":" +
                item.getSecond() + "] " + msg);
    }

    private GuiCore(GLCanvas glCanvas) {
        glCanvas.addGLEventListener(this);
        getContentPane().add(glCanvas);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        width = i2;
        height = i3;
    }
}
