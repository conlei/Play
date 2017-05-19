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

    public abstract void onAction();

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Texture getTexture() {
        return graphics;
    }
}
