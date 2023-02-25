package com.jgegroup.pacman;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    //Code starts here
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JButton button = new JButton("Click to Start");
        button.setBounds(20,40,5,5);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Stage stage = new Stage();
                GameCycle gamecycle = new GameCycle();
                try {
                    gamecycle.start(stage);
                }
                catch(Exception error) {

                }
            }
        });
        frame.add(button);
        //Visibility
        frame.setTitle("Pac Chilling Game");
        frame.setVisible(true);

        //Others Stuff
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // if map out of the way, add this frame.setResizeable(false);

        // Add Gamepanel to Jpanel
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.pack();  // make Jframe window size same as largest Jpanel size. So frame'size = gamePanel's size.
    }
}
