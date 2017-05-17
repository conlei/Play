package com.mmclcs.vismusic;

import com.jogamp.opengl.GL2;
import com.mmclcs.graphics.Drawable;

import java.util.ArrayList;

/**
 * Grid plane on which the animation will exist.  This is essentially the world for the effects
 * Created by mackenzie on 16/05/17.
 */
class GridPlane extends Thread implements Drawable {

    // Draw offset in pixels
    public static final int offset = 10;

    // Vertices objects for grid
    private final Vertex[][] vertices;
    private final ArrayList<Wave> waves;
    private boolean running;

    GridPlane() {
        running = true;
        // Initialize vertices objects
        vertices = new Vertex[100][100];
        for (Vertex[] aVertice : vertices) {
            for (Vertex vertice : aVertice) {
                vertice = new Vertex();
            }
        }
        // Init waves
        waves = new ArrayList<>();
    }

    @Override
    public void run() {
        while (running) {
            for (int index = 0; index < waves.size(); index++) {
                Wave item = waves.get(index);
                item.update();
                float travel = item.getTravel();

            }
        }
    }

    @Override
    public void draw(GL2 gl, int width, int height) {
        float unitX = (float) width / ((float) vertices.length + 1.0f);
        float unitY = (float) height / ((float) vertices.length + 1.0f);
        float zOffset = 0.5f;
        float yOffset = unitY / 2.0f;
        float xOffset = unitX / 2.0f;
        gl.glColor3f(0.2f, 0.2f, 0.2f);
        for (int vArrayIndex = 0; vArrayIndex < vertices.length; vArrayIndex++) {
            for (int index = 0; index < vertices[vArrayIndex].length - 1; index++) {
                gl.glBegin(GL2.GL_LINES);
                /*
                0 ----- 1
                |       |
                |       |
                |       |
                3 ----- 2
                 */
                // 0 to 1
                gl.glVertex3f(xOffset + (vArrayIndex * unitX), yOffset + (index * unitY), zOffset);
                gl.glVertex3f(xOffset + ((vArrayIndex + 1) * unitX), yOffset + (index * unitY), zOffset);
                // 1 to 2
                gl.glVertex3f(xOffset + ((vArrayIndex + 1) * unitX), yOffset + (index * unitY), zOffset);
                gl.glVertex3f(xOffset + ((vArrayIndex + 1) * unitX), yOffset + ((index + 1) * unitY), zOffset);
                // 2 to 3
                gl.glVertex3f(xOffset + ((vArrayIndex + 1) * unitX), yOffset + ((index + 1) * unitY), zOffset);
                gl.glVertex3f(xOffset + (vArrayIndex * unitX), yOffset + ((index + 1) * unitY), zOffset);
                // 3 to 0
                gl.glVertex3f(xOffset + (vArrayIndex * unitX), yOffset + ((index + 1) * unitY), zOffset);
                gl.glVertex3f(xOffset + (vArrayIndex * unitX), yOffset + (index * unitY), zOffset);
                gl.glEnd();
            }
        }
    }
}
