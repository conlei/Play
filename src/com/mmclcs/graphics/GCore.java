package com.mmclcs.graphics;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import java.time.LocalDateTime;

/**
 * This is the graphics core class, it initializes openGl and is used to create a new OpenGl context
 * Created by mackenzie on 16/05/17.
 */
public class GCore {

    private static boolean initialized = false;
    private static GLProfile profile;

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
    public static GContext makeNewContext() throws IllegalStateException {
        log("Request made for a new graphics context");
        if (!initialized) throw new IllegalStateException("Graphics engine not initialized yet");
        GLCapabilities capabilities = new GLCapabilities(profile);
        return new GContext(new GLCanvas(capabilities));
    }

    /**
     * Logs a msg out of the System.out PrintStream.
     *
     * @param msg message to log
     */
    static void log(String msg) {
        LocalDateTime item = LocalDateTime.now();
        System.out.println("[GCore: " + item.getMonth().name() + " " +
                item.getDayOfMonth() + ", " +
                item.getHour() + ":" +
                item.getMinute() + ":" +
                item.getSecond() + "] " + msg);
    }
}
