package com.mypass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mypass/fxml/login.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);

        scene.getStylesheets().add(getClass().getResource("/com/mypass/css/styles.css").toExternalForm());

        primaryStage.setTitle("MyPass - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}