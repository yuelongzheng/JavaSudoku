package com.example.javasudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Sudoku-Website-Selection.fxml"));
        Parent root = fxmlLoader.load();
        String cssLocation = this.getClass().getResource("SceneStyle.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(cssLocation);
        stage.setTitle("Sudoku Selection");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}