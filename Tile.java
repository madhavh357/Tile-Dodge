import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Tile extends Rectangle {

    public Tile(int x, double y, Color c) {
        super(30, 30, c);
        setX(x);
        setY(y);
    }

    public void move(double velX) {
        setX(getX()-velX);
    }
    

    public void checkEdges() {
        if (getX() < -30) {
            setX(1000);
            setY(Math.random() * 800);
        }
    }
}
