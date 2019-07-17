

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Write a description of JavaFX class TileDodgeMain here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TileDodgeMain extends Application
{

    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage)
    {
        try {
            ViewManager manager = new ViewManager();
            primaryStage = manager.getMainStage();
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
