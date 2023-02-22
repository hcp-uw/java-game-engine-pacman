import com.jgegroup.pacman.objects.Enums.*;
import com.jgegroup.pacman.objects.Ghost;
import com.jgegroup.pacman.objects.Position;
import com.jgegroup.pacman.objects.Tile;
import com.jgegroup.pacman.objects.immovable.Path;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class movementTests {
    public static final String testImageUrl =
            "https://developer.mozilla.org/en-US/docs/Games/Techniques/Tilemaps/screen_shot_2015-10-06_at_15.56.05.png";
    class PinkGhostMovement {

        public HashMap<Direction, Tile> pseudoSurr;
        public Ghost pinkie;
        public PinkGhostMovement() {
            pinkie = new Ghost.Pink(0,0,10, Color.PINK);
            pseudoSurr = new HashMap<Direction, Tile>();
        }

        @Test
        void checkOnlyLeftMovement() {

        }
    }


}
