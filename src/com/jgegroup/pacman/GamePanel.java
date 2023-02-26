package com.jgegroup.pacman;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
   public final int NUMBER_OF_TILE_VERTICAL = 24;
   public final int NUMBER_OF_TILE_HORIZONTAL = 32;
   public final int TILESIZE = 32;

   public final int SCALE = 1;

   public final int RESOLUTION_VERTICAL = NUMBER_OF_TILE_VERTICAL* TILESIZE * SCALE; // NUMBER_OF_TILE_VERTICAL * TILESIZE = 768
   public final int RESOLUTION_HORIZONTAL = NUMBER_OF_TILE_HORIZONTAL* TILESIZE* SCALE;// =NUMBER_OF_TILE_HORIZONTAL * TILESIZE = 1024

    public GamePanel () { // Constructor GamePanel
        this.setPreferredSize(new Dimension(RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL));
        this.setDoubleBuffered(true); // improve performance
        this.setBackground(Color.pink); // pink is cool!
    }
}
