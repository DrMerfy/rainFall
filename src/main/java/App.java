import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;

/**
 * Created by George Melissourgos on ${Date}
 * melissourgos@outlook.com
 * geomelkon@csd.auth.gr
 */
public class App extends Application {

    private Pane mainPane;

    public static void startGui(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        mainPane = new Pane();

        changeBackgroundColor("#090049");

        Scene scene = new Scene(mainPane, 800,400);

        stage.setScene(scene);
        stage.setTitle("Rain Fall");
        stage.show();


        for(int i =0; i<=800; i++) {
            RainDrop drop = new RainDrop(mainPane.widthProperty(), mainPane.heightProperty());

            mainPane.getChildren().add(drop);

            drop.fall();
        }
    }

    private void changeBackgroundColor(String color){
        mainPane.setStyle("-fx-background-color: "+color);
    }
}
