package com.mmclcs;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.File;
import java.io.IOException;

/**
 * Created by mackenzie on 18/05/17.
 */
public abstract class GuiElement {

    private Texture graphics;
    private File file;

    // Width, height, x, and y are all handled by the component that holds the GuiElement
    private int width;
    private int height;
    private int x;
    private int y;

    public GuiElement(File file, int x, int y, int width, int height) throws IOException {
        graphics = TextureIO.newTexture(file, false);
        this.file = file;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
