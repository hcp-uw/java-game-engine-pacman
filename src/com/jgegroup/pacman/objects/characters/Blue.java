//package com.jgegroup.pacman.objects.characters;
//
//import com.jgegroup.pacman.MainScene;
//import com.jgegroup.pacman.objects.Enums.*;
//import com.jgegroup.pacman.objects.immovable.Tile;
//
//import java.util.HashMap;
//
//import static com.jgegroup.pacman.objects.MapUtils.validateMove;
//
//public class Blue extends Ghost // implements GhostMovement
//{
//
//    /** @@Author: Noah
//     *  Blue Ghost
//     */
////    public Blue(int x, int y, int spookLength) {
////        super(x, y, spookLength, Color.BLUE);
////    }
//    public Blue(MainScene scene) {
//        super(spookLength, scene);
//    }
//
//    /** @@Author Noah
//     * Chooses the next move for the blue ghost in its normal state
//     * Throws no exceptions
//     * Returns nothing
//     * @param dx x distance to the pacman
//     * @param dy y distance to the pacman
//     * @param surr surrounding tiles
//     */
//    public void normalThink(int dx, int dy, HashMap<Direction, Tile> surr) {
//        Direction dX = Direction.STOP, dY = Direction.STOP;
//        if (dx != 0) {
//            dX = dx < 0 ? Direction.LEFT : Direction.RIGHT;
//            dX = validateMove(dX, surr);
//        }
//        if (dy != 0) {
//            dY = dy > 0 ? Direction.DOWN : Direction.UP;
//            dY = validateMove(dY, surr);
//        }
//        if (dX != Direction.STOP && dY != Direction.STOP) {
//            if (dx > dy) {
//                this.setDirection(dX);
//            } else {
//                this.setDirection(dY);
//            }
//        }
//        if (dX != Direction.STOP)
//            this.setDirection(dX);
//        if (dY != Direction.STOP)
//            this.setDirection(dY);
//    }
//
//    /** @@Author: Noah
//     * Enables the blue ghost to process its next move in the spooked state
//     * Throws no exceptions
//     * Returns nothing
//     * @param dx
//     * @param dy
//     * @param surr
//     */
//    public void spookedThink(int dx, int dy, HashMap<Direction, Tile> surr) {
//        Direction dX = Direction.STOP, dY = Direction.STOP;
//        dX = dx > 0 ? Direction.LEFT : Direction.RIGHT;
//        dX = validateMove(dX, surr);
//        dY = dy < 0 ? Direction.DOWN : Direction.UP;
//        dY = validateMove(dY, surr);
//        if (dX != Direction.STOP && dY != Direction.STOP) {
//            if (dx > dy) {
//                this.setDirection(dX);
//            } else {
//                this.setDirection(dY);
//            }
//        }
//        if (dX != Direction.STOP)
//            this.setDirection(dX);
//        if (dY != Direction.STOP)
//            this.setDirection(dY);
//    }
//
//}
