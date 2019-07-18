import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
/**
 * an obstacle of the player
 *
 * @author Madhav
 * @version 3
 */
public class Tile extends Rectangle {

    public Tile(int x, double y, Color c) {
        super(30, 30, c);
        setX(x);
        setY(y);
    }
    /**
     * moves the tile by a certain amount
     * @param double
     * @return nothing
     */
    public void move(double velX) {
        setX(getX()-velX);
    }
    
    /**
     * checks if it is on the edge
     * @param none
     * @return nothing
     */
    public void checkEdges() {
        if (getX() < -30) {
            setX(1000);
            setY(Math.random() * 800);
        }
    }
}
