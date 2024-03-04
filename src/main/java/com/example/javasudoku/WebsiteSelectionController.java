package com.example.javasudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WebsiteSelectionController implements Initializable {

    @FXML
    private ChoiceBox<String> websiteChoiceBox;
    @FXML
    private Label selectWebsiteText;
    private WebsiteSelection websiteSelection = new WebsiteSelection();
    private Stage stage;
    private Scene scene;
    private Parent root;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        websiteChoiceBox.getItems().addAll(websiteSelection.getAvailableSties());
    }

    public void confirmWebsite(ActionEvent event) throws IOException {
        if(websiteChoiceBox.getValue() != null) {
            websiteSelection.chooseWebsite(websiteChoiceBox.getValue());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Difficulty-Selection.fxml"));
            DifficultySelectionController difficultySelectionController =
                    new DifficultySelectionController(websiteSelection);
            loader.setController(difficultySelectionController);
            root = loader.load();
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            String cssLocation = this.getClass().getResource("SceneStyle.css").toExternalForm();
            scene = new Scene(root);
            scene.getStylesheets().add(cssLocation);
            stage.setScene(scene);
            stage.show();
        }

    }
}