package com.jgegroup.pacman;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
   public final int NUMBER_OF_TILE_VERTICAL = 36;
   public final int NUMBER_OF_TILE_HORIZONTAL = 28;
   public final int TileSize = 32;

   public final int scale = 2;

   public final int RESOLUTION_VERTICAL = NUMBER_OF_TILE_VERTICAL*TileSize; // NUMBER_OF_TILE_VERTICAL * TileSize = 1152
   public final int RESOLUTION_HORIZONTAL = NUMBER_OF_TILE_HORIZONTAL*TileSize;// =NUMBER_OF_TILE_HORIZONTAL * Tilesize = 896

    public GamePanel () { // Constructor GamePanel
        this.setPreferredSize(new Dimension(RESOLUTION_HORIZONTAL, RESOLUTION_VERTICAL));
        this.setDoubleBuffered(true); // improve performance
        this.setBackground(Color.pink); // pink is cool!
    }
}
