package com.jgegroup.pacman;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
   public final int NUMBER_OF_TILE_VERTICAL = 24;
   public final int NUMBER_OF_TILE_HORIZONTAL = 32;
   public final int tileSize = 32;

   public final int scale = 1;

   public final int RESOLUTION_VERTICAL = NUMBER_OF_TILE_VERTICAL* tileSize * scale; // NUMBER_OF_TILE_VERTICAL * TileSize = 768
   public final int RESOLUTION_HORIZONTAL = NUMBER_OF_TILE_HORIZONTAL* tileSize* scale;// =NUMBER_OF_TILE_HORIZONTAL * Tilesize = 1024

    public GamePanel () { // Constructor GamePanel
        this.setPreferredSize(new Dimension(RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL));
        this.setDoubleBuffered(true); // improve performance
        this.setBackground(Color.pink); // pink is cool!
    }
}
