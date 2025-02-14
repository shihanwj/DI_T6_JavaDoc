package es.ieslosmontecillos.javadoc;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);

        Temporizador t1 = new Temporizador();
        t1.setTiempo(15);
        root.getChildren().addAll(t1);
        t1.play();

        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("App Java Doc");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}