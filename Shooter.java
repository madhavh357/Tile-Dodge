import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
/**
 * Write a description of class ShooterTile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Shooter extends Tile
{
    public Shooter(int x, double y, Color c) {
        super(x, y, c);
    }
    
    public void shoot(Player p, Pane root, double speed) {
        Rectangle r = new Rectangle(15, 10, Color.ORANGE);
        r.setX(getX());
        r.setY(getY());
        root.getChildren().add(r);
        while (r.getX() > 0) {
            PVector force = new PVector(p.getX() - r.getX(), p.getY() - r.getY());
            double distance = force.mag();
            double strength = (1.3 * 100 * 170) / (distance * distance);
            force.normalize();
            force.mult(strength);
            r.setX(r.getX()+speed);
            r.setY(r.getY()+force.y);
            if ((p.getX()+20 > r.getX() && p.getX() < r.getX() + 15) &&
            (p.getY()+80 > r.getY() && p.getY() < r.getY()+10)) { 
                p.setLives(p.getLives()-1);
                break;
            }
        }
        root.getChildren().remove(r);
    }
}
