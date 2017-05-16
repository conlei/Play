package com.mmclcs.vismusic;

import com.jogamp.opengl.GL2;
import com.mmclcs.graphics.Drawable;

/**
 * This is the main drawing class for the music graphics
 * Created by mackenzie on 16/05/17.
 */
public class MusicDrawable implements Drawable {

    /**
     * Initializes a new music drawable object, does not start it.
     */
    public MusicDrawable() {
        // TODO
    }

    @Override
    public void draw(GL2 gl, int width, int height) {
        gl.glClear(0);
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
    }

}
