package com.mmclcs.vismusic;

/**
 * Whenever a certain note is played it creates a wave which is what interacts with the particles
 * Created by mackenzie on 16/05/17.
 */
public class Wave {

    // TODO OPTIMAL SPEED
    private static final float speed = 0.5f / 60.0f;

    private final float amplitude;
    private final float xOrigin;
    private final float yOrigin;
    private float travel;

    public Wave(float amplitude, float xOrigin, float yOrigin) {

        // Init properties
        this.amplitude = amplitude;
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        travel = 0;

    }

    // TODO CREATE UPDATE FUNCTION
    void update() {
        travel += speed;
    }

    public float getTravel() {
        return travel;
    }

}
