package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;
/** @@Author: Lucas
 * Controls objects under given anchorPane and make them draggable.
 * Implements Initializable library.
 */
public class Controller implements Initializable {

    @FXML
    private Rectangle rectangle;

    @FXML
    private AnchorPane anchorPane;


    DraggableMaker draggableMaker = new DraggableMaker();

    @Override
    /** @@Author: Lucas
     * Initializes objects to make them draggable.
     * Throws no exceptions
     * @param obj: url from library, resourcebundle from library.
     * @return nothing
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        draggableMaker.makeDraggable(rectangle);
        draggableMaker.makeDraggable(anchorPane);
    }
}