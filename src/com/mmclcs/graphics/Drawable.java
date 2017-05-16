package com.mmclcs.graphics;

import com.jogamp.opengl.GL2;

/**
 * Interface for GContext draw objects
 * Created by mackenzie on 16/05/17.
 */
public interface Drawable {
    void draw(GL2 gl, int width, int height);
}
