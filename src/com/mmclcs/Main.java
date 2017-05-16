package com.mmclcs;

import com.mmclcs.graphics.GContext;
import com.mmclcs.graphics.GCore;
import com.mmclcs.vismusic.MusicDrawable;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        GUICore.GUIInit();
        // Create GContext pane and draws it
        GContext pane = createGraphics();
        pane.addDrawable(new MusicDrawable());
        pane.setVisible(true);
        pane.repaintGL();
    }

    /**
     * Creates a new GContext object to run and configures it to default settings
     *
     * @return newly created GContext
     */
    private static GContext createGraphics() {
        GCore.init();
        GContext visScreen = GCore.makeNewContext();
        visScreen.setBounds(0, 0, 1000, 1000);
        visScreen.setTitle("Music Display");
        visScreen.setUndecorated(true);
        visScreen.setExtendedState(JFrame.MAXIMIZED_BOTH);
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(visScreen);
        return visScreen;
    }
}
