import com.jgegroup.pacman.objects.Enums.*;
import com.jgegroup.pacman.objects.characters.Pink;
import com.jgegroup.pacman.objects.immovable.Tile;
import com.jgegroup.pacman.objects.immovable.Path;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestSource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class movementTests {
    public static final String testImageUrl =
            "https://developer.mozilla.org/en-US/docs/Games/Techniques/Tilemaps/screen_shot_2015-10-06_at_15.56.05.png";
    class PinkGhostMovementAllPath implements TestSource {

        public HashMap<Direction, Tile> pseudoSurr;
        public Pink pinkie;
        public PinkGhostMovementAllPath() {
            pinkie = new Pink(0,0,10, Color.PINK);
            pseudoSurr = new HashMap<Direction, Tile>();
            pseudoSurr.put(Direction.UP, new Path(false, false, new Image(testImageUrl)));
            pseudoSurr.put(Direction.RIGHT, new Path(false, false, new Image(testImageUrl)));
            pseudoSurr.put(Direction.DOWN, new Path(false, false, new Image(testImageUrl)));
            pseudoSurr.put(Direction.LEFT, new Path(false, false, new Image(testImageUrl)));
        }

        @Test
        void checkPacmanNorthWest() {
            int dx = -1;
            int dy = 1;
            pinkie.think(Direction.LEFT, Direction.UP, dx, dy, pseudoSurr);
            assertEquals(pinkie.getDirection(), Direction.DOWN);
        }
    }
}
