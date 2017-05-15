package com.mmclcs;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GUICore {
    private Display display;
    private Scale progress;
    private Clip music;


    private static void UpdateScale(final Display display, final Scale target, final int value) {
        display.asyncExec(new Runnable() {
            @Override
            public void run() {
                if (!target.isDisposed()) {
                    target.setSelection(value);
                    target.getParent().layout();
                }
            }
        });
    }

    public static void GUIInit() {
        // normally i wouldn't put this here but I need to for the demo
        Clip music = AudioCore.InitSound("res/music.wav");

        Display display = new Display();
        Shell shell = new Shell(display, SWT.SHELL_TRIM & (~SWT.RESIZE));

        // create a new GridLayout with two columns
        // of different size
        RowLayout layout = new RowLayout();

        // set the layout to the shell
        shell.setLayout(layout);

        //create progress bar
        Scale progress = new Scale(shell, SWT.HORIZONTAL);
        progress.setLayoutData(new RowData(360, 40));

        //register listener
        progress.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                // when we move the playhead pause the music so I don't audio jumpscare myself at 2:39 IN THE MORNING AGAIN
                music.stop();
                // numbers are hard, touch any of this and it'll explode and stop working
                double percentbuffer = ((double)progress.getSelection()/100.0)*(music.getFrameLength());
                music.setFramePosition((int)percentbuffer);
            }
        });

        // create play button
        Button play = new Button(shell, SWT.PUSH);
        play.setText("Play");

        // register listener
        play.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AudioCore.PlaySound(music);
            }
        });

        // create a right aligned button
        Button pause = new Button(shell, SWT.PUSH);
        pause.setText("Pause");

        // register listener
        pause.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                AudioCore.PauseSound(music);
            }
        });

        shell.pack();
        shell.open();

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int xProgress = (int)AudioCore.GetProgress(music);
                UpdateScale(display, progress, xProgress);
                System.out.println(xProgress);
            }
        }, 200, 200);

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}

