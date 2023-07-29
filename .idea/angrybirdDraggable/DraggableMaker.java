package sample;

import javafx.scene.Node;
/** @@Author: Lucas
 * This class allows objects to be draggable.
 */
public class DraggableMaker {

    private double mouseAnchorX;
    private double mouseAnchorY;
    private double displacement; // maybe move to an object's field later
    /** @@Author: Lucas
     * Allows an object to be draggable.
     * Throws no exceptions
     * @param node: the mouse
     * @return nothing
     */
    public void makeDraggable(Node node){

        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
            displacement = getDisplacement(mouseAnchorX, mouseAnchorY, node);
        });

        node.setOnMouseDragged(mouseEvent -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            node.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
        });
    }
    /** @@Author: Lucas
     * calculates the displacement of an object when dragged.
     * Throws no exceptions
     * @param initialPositionX, initialPositionY, node: initial mouse position X when mouse pressed, initial mouse position Y when mouse pressed, the mouse
     * @return displacement.
     */
    public double[] getDisplacement(double initialPositionX, double initialPositionY, Node node) {
        double[] displacement = {initialPositionX, initialPositionY};
        node.setOnMouseReleased(mouseEvent -> {
            displacement[0] - mouseEvent.getX();
            displacement[1] - mouseEvent.getY();
        });
        return displacement;
    }
}