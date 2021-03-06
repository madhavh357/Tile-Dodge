import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
/**
 * creates the main player
 *
 * @author Madhav
 * @version 3
 */
public class Player extends Rectangle {
    private boolean gameOver = false;
    private PVector position;
    private PVector velocity;
    private PVector acceleration;
    private int mass;
    private int lives = 6;
    public Player(double x, double y, int mass, Color color) {
        super(mass, mass*4, color);
        this.mass = mass;
        position = new PVector(x, y);
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0);
        setX(position.x);
        setY(position.y);
    }
    /**
     * moves the player up by accelerating it
     * @param none
     * @return nothing
     */
    public void moveUp() {
        acceleration.set(0, -0.34);
    }
    /**
     * moves the player down by accelerating it
     * @param none
     * @return nothing
     */
    public void moveDown() {
        acceleration.set(0, 0.34);
    }
    /**
     * returns the amount of lives the player has
     * @param none
     * @return int
     */
    public int getLives() {
        return lives;
    }
    /**
     * sets the player's lives
     * @param int
     * @return none
     */
    public void setLives(int amt) {
        lives = amt;
    }
    /**
     * sets the player's acceleration to 0
     * @param none
     * @return nothing
     */
    public void slow() {
        acceleration.set(0, 0);
    }
    /**
     * checks for collision with tiles
     * @param Tile
     * @return nothing
     */
    public void checkForTiles(Tile tile) {
        if ((getX() + mass > tile.getX()) && (getX() < tile.getX()+30) && 
        (getY() + mass*4 > tile.getY()) && (getY() < tile.getY() + 30)) {
            
            lives--;
            tile.setX(-60);
        }
    }
    
    /**
     * updates position, velocity, lives, and checks edges
     * @param none
     * @return nothing
     */
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
    /**
     * returns gameOver
     * @param none
     * @return boolean
     */
    public boolean getGameOver() {
        return gameOver;
    }
    /**
     * sets gameOver
     * @param boolean
     * @return nothing
     */
    public void setGameOver(boolean c) {
        gameOver = c;
    }

}
