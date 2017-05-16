package com.mmclcs.vismusic;

/**
 * Created by mackenzie on 16/05/17.
 */
public class Vertex {

    private volatile float height;

    public Vertex() {
        height = 0;
    }

    /**
     * Sets the height of the vertex (affects color)
     *
     * @param height strength of the waves underneath
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * @return height of the vertex
     */
    public float getHeight() {
        return height;
    }

}
