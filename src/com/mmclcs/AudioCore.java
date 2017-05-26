package com.mmclcs;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Connor
 * Static class methods used to handle music
 */
class AudioCore {

    // initialises an Clip object
    static Clip InitSound(String filepath) {
        Clip clip;

        try {
            File inputstream = new File(filepath); // use the file path (String) to create a File object
            if (inputstream.exists()) {
                // initalise the inputstream
                AudioInputStream audio = AudioSystem.getAudioInputStream(inputstream);
                // load the inputstream into memory as a clip object
                clip = AudioSystem.getClip(null);
                clip.open(audio);

                // return the clip object, ready for use
                return clip;
            } else {
                throw new RuntimeException("AudioCore: File does not exist.");
            } // mac really doesn't like this next bit
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException("AudioCore: Unsupported filetype.");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("AudioCore: File has unsupported encoding.");
        } catch (IOException e) {
            throw new RuntimeException("AudioCore: I/O Exception.");
        } catch (LineUnavailableException e) {
            throw new RuntimeException("AudioCore: Line unavailable.");
        }
    }

    // start the sound clip
    static void StartSound(Clip inputclip) {
        inputclip.setFramePosition(0); // be kind; rewind
        inputclip.start();
    }

    // returns percentage (0-100) of the progress of the clip
    static double GetProgress(Clip inputclip) {
        return ((double) inputclip.getMicrosecondPosition() / (double) inputclip.getMicrosecondLength()) * 100.0;
    }

    // returns human-readable progress as a string
    static String GetHumanProgress(Clip inputclip) {
        return microsecondToHuman(inputclip.getMicrosecondPosition()) + " / " + microsecondToHuman(inputclip.getMicrosecondLength());
    }

    private static String microsecondToHuman(long microseconds) { // converts microseconds to human readable time
        long fullseconds = microseconds/1000000; // convert microseconds to full seconds
        long seconds = fullseconds % 60;
        long minutes = fullseconds / 60;

        return (int)minutes + ":" + String.format("%02d" ,(int)seconds);
    }
}
