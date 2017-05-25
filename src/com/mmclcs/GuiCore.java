package com.mmclcs;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * Jogl initalizer and window creator
 * Created by mackenzie on 17/05/17.
 */
public class GuiCore extends JFrame implements GLEventListener {

    private static boolean initialized = false;
    private static GLProfile profile;
    private Clip input;

    /**
     * Initialize open gl, must be done before using any other method this class offers
     */
    static void init() {
        profile = GLProfile.get(GLProfile.GL2);
        initialized = true;
    }

    /**
     * Creates a new OpenGl context
     *
     * @return OpenGl JFrame
     * @throws IllegalStateException if GCore has not been initialized.
     */
    static GuiCore makeNewContext() throws IllegalStateException {
        if (!initialized) throw new IllegalStateException("Graphics engine not initialized yet");
        GLCapabilities capabilities = new GLCapabilities(profile);
        return new GuiCore(new GLCanvas(capabilities));
    }

    /**
     * Creates a guiCore object, should only be created via GuiCore.createNewContext()
     *
     * @param glCanvas gl drawing object
     */
    private GuiCore(GLCanvas glCanvas) {
        super();
        glCanvas.addGLEventListener(this);
        getContentPane().add(glCanvas);
        setResizable(false);
        Thread updater = new Thread(() -> {
            while (true) {
                glCanvas.display();
            }
        });
        updater.setDaemon(true);
        updater.start();
    }

    /**
     * Sets the audio clip to poll from for checking music progress (should be the currently playing music clip)
     *
     * @param music current music clip
     */
    void setAudioInput(Clip music) {
        this.input = music;
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(0.9f, 0.9f, 0.9f, 1f);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        // Draw basic bar
        gl.glBegin(GL2.GL_POLYGON);
        gl.glColor3f(0, 0, 0.3f);
        gl.glVertex2f(-0.98f, -0.8f);
        gl.glVertex2f(-0.98f, 0.8f);
        gl.glColor3f(0, 0.3f, 0);
        gl.glVertex2f(0.98f, 0.8f);
        gl.glVertex2f(0.98f, -0.8f);
        gl.glEnd();
        // Draw input bar (if such exists)
        if (input != null) {
            float progress = (float) AudioCore.GetProgress(input) / 100.0f;
            if (progress > 0) {
                gl.glBegin(GL2.GL_POLYGON);
                gl.glColor3f(0, 0, 1);
                gl.glVertex2f(-0.98f, -0.8f);
                gl.glVertex2f(-0.98f, 0.8f);
                // SET COLOR
                gl.glColor3f(0, progress, 1 - progress);
                // SET END VERTEX
                gl.glVertex2f(-0.98f + (1.8f * progress), 0.8f);
                gl.glVertex2f(-0.98f + (1.8f * progress), -0.8f);
                gl.glEnd();
            }
        }
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }
}
