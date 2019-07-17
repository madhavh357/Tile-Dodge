import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Player extends Rectangle {
    private boolean gameOver = false;
    private PVector position;
    private PVector velocity;
    private PVector acceleration;
    private int mass;
    private int lives = 4;
    public Player(double x, double y, int mass, Color color) {
        super(mass, mass*4, color);
        this.mass = mass;
        position = new PVector(x, y);
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0);
        setX(position.x);
        setY(position.y);
    }

    public void moveUp() {
        acceleration.set(0, -0.34);
        //System.out.println(acceleration.y);
    }
    
    public int getLives() {
        return lives;
    }

    public void setLives(int amt) {
        lives = amt;
    }
    
    public void moveDown() {
        acceleration.set(0, 0.34);
        //System.out.println(acceleration.y);
    }

    public void slow() {
        acceleration.set(0, 0);
    }

    public void checkForTiles(Tile tile) {
        if ((getX() + mass > tile.getX()) && (getX() < tile.getX()+30) && 
        (getY() + mass*4 > tile.getY()) && (getY() < tile.getY() + 30)) {
            
            lives--;
            tile.setX(-60);
        }
    }
    

    public void update() {
        velocity.add(acceleration);
        setX(getX()+velocity.x);
        setY(getY()+velocity.y);
        //System.out.println("Updating: " + velocity.y);
        if (velocity.y > 25) {
            velocity.set(0, 25);
        } else if (velocity.y < -25) {
            velocity.set(0, -25);
        }
        if (lives <= 0) {
            gameOver = true;
        }
        if (getY() > 800) {
            setY(0);
        }
        if (getY()+mass*4 < 0) {
            setY(780);
        }
    }
    
    

}
