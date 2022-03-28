package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Controller {

    @FXML
    Button onButton;

    private boolean off = true;
    private boolean mining = false;
    private int directionFacing = 0;

    @FXML
    void toggleBeeSwarm() {
        if (off) {
            off = false;

            onButton.setText("Turn Off Bee Swarm");


            //Token grabber
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1500);

                        Robot sus = new Robot();
                        for (int i = 0; i < 20; i++) {
                            if(mining) sus.keyPress(KeyEvent.VK_D);
                            Thread.sleep(5000);
                            if(mining) sus.keyRelease(KeyEvent.VK_D);
                            if(mining) sus.keyPress(KeyEvent.VK_A);
                            Thread.sleep(5000);
                            if(mining) sus.keyRelease(KeyEvent.VK_A);
                        }

                    } catch (AWTException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            //Thread that controls autoclick
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1500);

                        Robot sus = new Robot();

                        for (int i = 0; i < 18; i++) {
                            sus.keyPress(KeyEvent.VK_D);
                            Thread.sleep(100);
                            sus.keyRelease(KeyEvent.VK_D);
                        }
                        Thread.sleep(1000);
                        for (int i = 0; i < 30; i++) {
                            sus.keyPress(KeyEvent.VK_W);
                            Thread.sleep(100);
                            sus.keyRelease(KeyEvent.VK_W);
                        }

                        //The robot will dig pollen for 200 seconds
                        mining = true;
                        for (int i = 0; i < 100; i++) {
                            sus.mousePress(InputEvent.BUTTON1_MASK);
                            Thread.sleep(1000);
                            sus.mouseRelease(InputEvent.BUTTON1_MASK);
                            Thread.sleep(1000);
                        }
                        mining = false;

                    } catch (AWTException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


            //Add new controls for every possible field you want to grind on (Dandelion, Sunflower, Mushroom....)
        } else {
            System.exit(0);
        }
    }
}
