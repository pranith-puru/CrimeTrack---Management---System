package com.crimetrack;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("CrimeTrack - Login");
        stage.setScene(LoginView.create(stage));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
