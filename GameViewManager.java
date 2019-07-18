 import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import javafx.event.ActionEvent;
/**
 * shows the scene where the game occurs
 *
 * @author Madhav
 * @version 3
 */
public class GameViewManager
{

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private static final int GAME_WIDTH = 1000;
    private static final int GAME_HEIGHT = 800;

    private Rectangle screen = new Rectangle(GAME_WIDTH, GAME_HEIGHT, Color.rgb(8, 215, 252));
    //gives the canvas a color
    
    private Stage menuStage;

    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private AnimationTimer gameTimer;
    // for movement of player
    
    private boolean canShoot = false;
    // to determine whether a tile can shoot

    private Player player = new Player(30, 393, 20, Color.rgb(0, 255, 0));
    private Tile tiles[] = new Tile[25];
    private Shooter shooters[] = new Shooter[10];
    private Rectangle missile = new Rectangle(15, 10, Color.rgb(8, 215, 252));
    //creates the objects

    private int mCount = 0; // what iteration the shoot method is in

    private int level = 0;
    private double t = 0; // time

    private Text time = new Text(10, 780, "Time: " + (int)t);
    private Text lives = new Text(10, 20, "Lives: " + player.getLives());
    private Text tLevel = new Text(940, 20, "Level " + (level + 1));

    private boolean shouldAddLives = false;
    private boolean shouldDisplayText = false;

    private int timerMult = 1; // for the levelUp() method

    public GameViewManager() {
        initializeStage();
        createKeyListener();
    }
    /**
     * creates the initial stage
     * @param none
     * @return nothing
     */
    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        screen.setX(0);
        screen.setY(0);
        gamePane.getChildren().add(screen);
    }
    /**
     * listens for key presses and responds to them
     * @param none
     * @return nothing
     */
    private void createKeyListener() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
                        isUpKeyPressed = true;
                    } else if  (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
                        isDownKeyPressed = true;
                    }
                }
            });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
                        isUpKeyPressed = false;
                    } else if  (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
                        isDownKeyPressed = false;
                    }
                }
            });
    }
    /**
     * changes the scene from the menu scene to the play scene
     * @param Stage menuStage
     * @return nothing
     */
    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        player.setLives(6);
        player.setX(30);
        player.setY(393);
        player.setGameOver(false);
        gamePane.getChildren().add(player);
        missile.setX(100);
        missile.setY(100);
        gamePane.getChildren().add(missile);
        t = 0;

        for (int i = 0; i < tiles.length; i++) {
            int x = i * tiles.length * 9 + 400;
            double y = Math.random() * GAME_HEIGHT;
            tiles[i] = new Tile(x, y, Color.rgb(200, 50, 240));
            gamePane.getChildren().add(tiles[i]);
        }

        for (int i = 0; i < shooters.length; i++) {
            int x = i * shooters.length * 15 + 400;
            double y = Math.random() * GAME_HEIGHT;
            shooters[i] = new Shooter(x, y, Color.BLUE);
            gamePane.getChildren().add(shooters[i]);
        }


        gamePane.getChildren().add(time);
        gamePane.getChildren().add(lives);
        gamePane.getChildren().add(tLevel);
        //adds these elements to the screen

        createGameLoop();
        gameStage.show();
    }
    /**
     * creates the animation
     * @param none
     * @return nothing
     */
    private void createGameLoop() {
        gameTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                update();
            }
        };

        gameTimer.start();
    }
    /**
     * moves the player in the correct direction
     * @param none
     * @return nothing
     */
    private void movePlayer() {
        if (isUpKeyPressed) {
            player.moveUp();
        }

        if (isDownKeyPressed) {
            player.moveDown();
        }

        if (!isUpKeyPressed && !isDownKeyPressed) {
            player.slow();
        }

    }
    /**
     * shoots the missiles with gravitational attraction
     * @param Shooter, Player, Rectangle, double
     * @return nothing
     */
    private void shoot(Shooter s, Player p, Rectangle missile, double speed) {
        if (mCount == 0) { // used so that the missile is not always set to the shooter's x and y
                           // on every iteration
            missile.setX(s.getX());
            missile.setY(s.getY());
            canShoot = true;
        }
        missile.setFill(Color.ORANGE); // sets the color
        PVector force = new PVector(p.getX() - missile.getX(), p.getY() - missile.getY());
        double distance = force.mag(); //takes the magnitude of the force vector
        double strength = (1.3 * 100 * 170) / (distance * distance);
        //calculates the force of the gravitational attraction to the player
        
        force.normalize();
        force.mult(strength); // multiplies strength by force's x and y
        missile.setX(missile.getX() - speed); // moves the x by a fixed about
        missile.setY(missile.getY() + force.y); // accelerates toward the player's y
        mCount++;
        if ((p.getX()+20 > missile.getX() && p.getX() < missile.getX() + 15) &&
        (p.getY()+80 > missile.getY() && p.getY() < missile.getY()+10)) {
            p.setLives(p.getLives()-1);
            missile.setFill(Color.rgb(8, 215, 252));
            canShoot = false;
            mCount = 0;
        } //collision with player

        if (missile.getX() + 15 < 0) {
            missile.setFill(Color.rgb(8, 215, 252));
            canShoot = false;
            mCount = 0;
        } //off the edge
    }
    /**
     * contains movement of player, tiles, and collision
     * @param none
     * @return nothing
     */
    private void update() {
        t += 0.016;

        levelUp();
        double tileSpeed = (level + 1) * 1.15;
        player.update();
        movePlayer();
        time.setText("Time: " + (int)t);
        lives.setText("Lives: " + player.getLives());
        tLevel.setText("Level " + (level + 1));
        double m = Math.random(); // to make shooting less likely


        for (int i = 0; i < tiles.length; i++) {
            player.checkForTiles(tiles[i]);
            tiles[i].move(tileSpeed);
            tiles[i].checkEdges();
        }

        for (int i = 0; i < shooters.length; i++) {
            player.checkForTiles(shooters[i]);
            shooters[i].move(tileSpeed);
            shooters[i].checkEdges();
            if (level >= 2) {
                if ((m > 0.1 && m < 0.15) || canShoot) {
                    shoot(shooters[i], player, missile, 1);
                }
            }
        }

        if (player.getGameOver()) { //game over scene
            Rectangle r = new Rectangle(1200, 1000, Color.BLUE);
            gamePane.getChildren().add(r);
            t-=0.016;
            Text message = new Text(200, 400, "You survived for " + (int)t + " seconds and made it to level " + (level + 1) +"!");
            message.setFill(Color.WHITE);
            message.setFont(new Font(25));
            gamePane.getChildren().add(message);

        }
    }
    /**
     * increases the tile speed and amount of lives every fifteen seconds
     * @param none
     * @return nothing
     */
    private void levelUp() { 
        if ((((int)t % 15 == 0) && (t >= 15 * timerMult - 0.5 && timerMult <= 15 * t - 0.484)) && (int)t != 0) {
            level = (int)t/15;
            shouldAddLives = true;
            player.setLives(player.getLives() + 1);
            shouldDisplayText = true;
            timerMult++;
        }
    }
}
