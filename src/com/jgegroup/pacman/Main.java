package com.jgegroup.pacman;
import javax.swing.*;

public class Main {
    //Code starts here
    // hello
    public static void main(String[] args) {

        JFrame frame = new JFrame();

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
