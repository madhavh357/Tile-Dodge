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
/**
 * Write a description of class GameScene here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameViewManager
{

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private static final int GAME_WIDTH = 1000;
    private static final int GAME_HEIGHT = 800;

    private Stage menuStage;

    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private AnimationTimer gameTimer;

    private Player player = new Player(30, 393, 20, Color.rgb(0, 255, 0));
    private Tile tiles[] = new Tile[25];
    private Shooter shooters[] = new Shooter[10];

    private int level = 0;
    private double t = 0;

    public GameViewManager() {
        initializeStage();
        createKeyListener();
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);

    }

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

    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        gamePane.getChildren().add(player);

        for (int i = 0; i < tiles.length; i++) {
            int x = i * 40 + 500;
            double y = Math.random() * GAME_HEIGHT;
            tiles[i] = new Tile(x, y, Color.rgb(200, 50, 240));
            gamePane.getChildren().add(tiles[i]);
        }

        for (int i = 0; i < shooters.length; i++) {
            int x = i * 40 + 500;
            double y = Math.random() * GAME_HEIGHT;
            shooters[i] = new Shooter(x, y, Color.BLUE);
            gamePane.getChildren().add(shooters[i]);
        }

        createGameLoop();
        gameStage.show();
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
               update();
            }
        };

        gameTimer.start();
    }

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

    private void update() {
        t += 0.016;
        levelUp();
        double tileSpeed = (level + 1) * 1.5;
        player.update();
        movePlayer();
        for (int i = 0; i < tiles.length; i++) {
            player.checkForTiles(tiles[i]);
            tiles[i].move(tileSpeed);
            tiles[i].checkEdges();
        }

        for (int i = 0; i < shooters.length; i++) {
          player.checkForTiles(shooters[i]);
          shooters[i].move(tileSpeed);
          shooters[i].checkEdges();
        }
    }

    private void levelUp() {
        if ((int)t % 15 == 0) {
            level = (int)t/15;
        }
    }
}
