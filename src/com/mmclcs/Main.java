package com.mmclcs;

import javax.sound.sampled.Clip;
import java.util.Scanner;

public class Main {

    public static Clip music;

    public static void main(String[] args) {
        if (args.length == 0) {
            music = AudioCore.InitSound("res/music.wav");
        } else {
            music = AudioCore.InitSound(args[0]);
        }
        AudioCore.StartSound(music);

        GuiCore.init();
        GuiCore window = GuiCore.makeNewContext();
        window.setBounds(100, 100, 500, 150);
        window.setVisible(true);

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
                    music.stop();
                    music.close();

                    music = AudioCore.InitSound(input);
                    AudioCore.StartSound(music);
                }
            } else if (input.startsWith("/")) {
                music.stop();
                music.close();

                music = AudioCore.InitSound(input);
                AudioCore.StartSound(music);
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

    private static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
