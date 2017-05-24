package com.mmclcs;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.Scanner;

public class Main {

    private static Clip music;
    private static GuiCore window;

    public static void main(String[] args) {
        // Initialize music and gui
        if (args.length == 0) {
            music = AudioCore.InitSound("res/music.wav");
        } else {
            music = AudioCore.InitSound(args[0]);
        }
        GuiCore.init();
        window = GuiCore.makeNewContext();
        window.setAudioInput(music);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setAlwaysOnTop(true);
        window.setBounds(100, 100, 500, 70);
        window.setVisible(true);
        AudioCore.StartSound(music);

        // Looks for input
        while (true) {
            Scanner userinput = new Scanner(System.in);

            String input = userinput.nextLine();
            if (input.toLowerCase().equals("q") || input.toLowerCase().equals("quit")) { System.exit(0); } // if input "q" quit the program
            if (input.toLowerCase().equals("play")) { music.start(); } // if input "play" play or resume music
            if (input.toLowerCase().equals("pause")) { music.stop(); } // if input "pause" pause music
            if (input.toLowerCase().equals("progress")) {
                System.out.println(AudioCore.GetHumanProgress(music));
            } // if input "progress" print progress of music

            if (System.getProperty("os.name").contains("Windows")) {
                if (input.matches("^([A-Z]:\\\\)(.+)")) {
                    changeClip(input);
                }
            } else if (input.startsWith("/")) {
                changeClip(input);
            }

            String[] timeCheck = input.split(":");
            if(timeCheck.length == 2) {
                if (isNumber(timeCheck[0]) && isNumber(timeCheck[1])) {
                    long microseconds;
                    long minutes = Long.parseLong(timeCheck[0]);
                    long seconds = Long.parseLong(timeCheck[1]);

                    seconds = seconds + (minutes * 60);
                    microseconds = seconds * 1000000;

                    music.setMicrosecondPosition(microseconds);
                }
            }
        }
    }

    /**
     * Checks if an input is a number
     *
     * @param input String input to parse
     * @return whether or not it is a number
     */
    private static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Changes the clip to another file
     *
     * @param file path to file
     */
    private static void changeClip(String file) {
        // Close the current music
        music.stop();
        music.close();

        // Init new music
        music = AudioCore.InitSound(file);
        window.setAudioInput(music);
        AudioCore.StartSound(music);
    }
}
