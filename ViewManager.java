import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import java.util.List;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.text.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.DropShadow;
import javafx.event.ActionEvent;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;

/**
 * shows the menu scene
 * @author Madhav
 * @version 3
 */

public class ViewManager {

    private static final int HEIGHT = 800;
    private static final int WIDTH = 1000;
    //width and height of canvas
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTONS_START_X = 60;
    private final static int MENU_BUTTONS_START_Y = 440;

    private boolean isThere = false; //for instructions text
    private Rectangle fullScreen = new Rectangle(WIDTH, HEIGHT, Color.rgb(165, 20, 255));
    // the background

    List<Button> menuButtons;
    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.setTitle("Tile Dodge");
        fullScreen.setX(0);
        fullScreen.setY(0);
        mainPane.getChildren().add(fullScreen);
        createButtons();
        createTitle();

    }
    /**
     * gives the mainStage for acces from TileDodgeMain class
     * @param none
     * @return mainStage
     */
    public Stage getMainStage() {
        return mainStage;
    }

    /**
     * creates all buttons in menu
     * @param Button
     * @return nothing
     */
    private void addMenuButton(Button button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 50);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    /**
     * creates start button and actions for it
     * @param none
     * @return nothing
     */
    private void createStartButton() {
        Button startButton = new Button("Play");

        addMenuButton(startButton);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    GameViewManager gameManager = new GameViewManager();
                    gameManager.createNewGame(mainStage);
                }
            });

    }

    /**
     * creates help button with pop up text
     * @param none
     * @return nothing
     */
    private void createHelpButton() {
        Button helpButton = new Button("How to Play");
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Rectangle r = new Rectangle(350, 85, Color.rgb(165, 20, 255));
                    r.setX(500);
                    r.setY(340);
                    Text instructions = new Text(500, 360,
                            "-Use arrows keys to move\n-Avoid the tiles for as long as possible\n-You have 6 lives\n-If you go off the edge you appear on the other side\n-Every 15 seconds the tiles speed up and you gain a life");
                    instructions.setFill(Color.WHITE);
                    mainPane.getChildren().add(instructions);

                    if (!isThere) {
                        instructions.setFill(Color.WHITE);
                        mainPane.getChildren().remove(r);
                        isThere = true;
                    } else {
                        mainPane.getChildren().add(r);
                        isThere = false;
                    }
                }
            });
    }
    /**
     * method that creates both buttons
     * @param none
     * @return nothing
     */
    private void createButtons() {
        createStartButton();
        createHelpButton();
    }
    /**
     * creates the title with drop shadow effect when the mouse touches it
     * @param none
     * @return nothing
     */
    private void createTitle() {
        Font font = new Font(35);
        Text title = new Text("Tile Dodge");
        title.setX(60);
        title.setY(300);
        title.setFont(font);
        title.setFill(Color.WHITE);
        title.setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    title.setEffect(new DropShadow());
                }
            });

        title.setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    title.setEffect(null);
                }
            });

        mainPane.getChildren().add(title);
    }
}
