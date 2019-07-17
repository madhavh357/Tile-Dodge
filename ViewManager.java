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
public class ViewManager {

    private static final int HEIGHT = 800;
    private static final int WIDTH = 1000;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTONS_START_X = 10;
    private final static int MENU_BUTTONS_START_Y = 440;
    private Subscene helpSubScene;

    private Subscene sceneToHide;
    List<Button> menuButtons;
    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT, Color.BLUE);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        createButtons();
        createTitle();
        createSubScenes();

    }

    private void showSubScene(Subscene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }

        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createSubScenes() {
        helpSubScene = new Subscene();
        mainPane.getChildren().add(helpSubScene);
    }

    public Stage getMainStage() {

        return mainStage;
    }

    private void addMenuButton(Button button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 50);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButtons() {
        createStartButton();
        createHelpButton();
    }

    private void createStartButton() {
        Button startButton = new Button("Play");

        addMenuButton(startButton);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        });

    }
    private void createHelpButton() {
        Button helpButton = new Button("How to Play");
        addMenuButton(helpButton);
        helpButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mainPane.getChildren().add(helpSubScene);
                }
            });
    }

    private void createTitle() {
        Font font = new Font(25);
        Text title = new Text("Tile Dodge");
        title.setX(10);
        title.setY(300);
        title.setFont(font);
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