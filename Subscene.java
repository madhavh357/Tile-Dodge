import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
/**
 * Write a description of class Subscene here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */



public class Subscene extends SubScene
{
    private boolean isHidden = true;
    public Subscene() {
        super(new AnchorPane(), 1000, 800);
        prefWidth(1000);
        prefHeight(800);
        AnchorPane root2 = (AnchorPane) this.getRoot();
        
        setLayoutX(348);
        setLayoutY(180);
        Text banner = new Text(1054, 200, "HOW TO PLAY");
        Font big = new Font(30);
        banner.setFont(big);
        Text instructions = new Text(1031, 400,
        "-Use arrows keys to move\n-Avoid the tiles for as long as possible\n-You have 4 lives");
        Font normal = new Font(15);
        instructions.setFont(normal);
        root2.getChildren().add(banner);
        root2.getChildren().add(instructions);
    }
    
    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        
        if(isHidden) {
            transition.setToX(-676);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }
        transition.play();
    }
}
