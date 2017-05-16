package com.mmclcs.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * JFrame with a GLCanvas set on top of the content pane
 * Created by mackenzie on 16/05/17.
 */
public class GContext extends JFrame implements GLEventListener {
    private CopyOnWriteArrayList<Drawable> drawables;
    private GLCanvas canvas;

    GContext(GLCanvas canvas) {
        super();
        drawables = new CopyOnWriteArrayList<>();
        this.canvas = canvas;
        canvas.addGLEventListener(this);
        getContentPane().add(canvas, BorderLayout.CENTER);
    }

    public void repaintGL() {
        canvas.display();
    }


    /**
     * Add a drawable to be drawn on top of every other drawable object
     *
     * @param d drawable object to be added
     */
    public void addDrawable(Drawable d) {
        GCore.log("New drawable of type: " + d.getClass().getSimpleName());
        drawables.add(d);
    }

    /**
     * Add a drawable to be drawn in a certain place
     *
     * @param d     drawable object to be added
     * @param index order in which it should be drawn
     */
    @SuppressWarnings("unused")
    public void addDrawable(Drawable d, int index) {
        GCore.log("New drawable of type: " + d.getClass().getTypeName() + " at index: " + index);
        drawables.add(index, d);
    }

    @SuppressWarnings("unused")
    public void removeDrawable(int index) {
        drawables.remove(index);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        // Sets the clear color to black
        gl.glClearColor(0, 0, 0, 0);
        //Setup viewports
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // coordinate system origin at lower left with width and height same as the window
        int width = glAutoDrawable.getSurfaceWidth();
        int height = glAutoDrawable.getSurfaceHeight();

        GLU glu = new GLU();
        glu.gluOrtho2D(0.0f, width, 0.0f, height);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        int height = glAutoDrawable.getSurfaceHeight();
        int width = glAutoDrawable.getSurfaceWidth();
        gl.glClear(GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT);
        // TODO draw code here
        for (Drawable item : drawables) {
            item.draw(gl, width, height);
        }
        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
    }
}
