package com.example.javasudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DifficultySelectionController implements Initializable {
    @FXML
    private ChoiceBox<String> difficultyChoiceBox;
    @FXML
    private Button difficultyConfirmationButton;
    private WebsiteSelection websiteSelection;
    private Parent root;

    public DifficultySelectionController(WebsiteSelection websiteSelect){
        websiteSelection = websiteSelect;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        difficultyChoiceBox.getItems().addAll(websiteSelection.getDifficultyArray());
        difficultyConfirmationButton.setOnAction((ActionEvent e) -> {
            try {
                if(difficultyChoiceBox.getValue() != null) {
                    websiteSelection.setDifficulty(difficultyChoiceBox.getValue());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Final.fxml"));
                    FinalController finalController = new FinalController(websiteSelection);
                    loader.setController(finalController);
                    root = loader.load();
                    difficultyConfirmationButton.getScene().setRoot(root);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
