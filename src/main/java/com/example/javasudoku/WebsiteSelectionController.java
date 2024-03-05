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

public class WebsiteSelectionController implements Initializable {

    @FXML
    private ChoiceBox<String> websiteChoiceBox;
    @FXML
    private Label selectWebsiteText;
    @FXML
    private Button confirmationButton;
    @FXML
    private Button hardNYT;
    @FXML
    private Button evilWebSudoku;
    private WebsiteSelection websiteSelection = new WebsiteSelection();
    private Parent root;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        websiteChoiceBox.getItems().addAll(websiteSelection.getAvailableSties());
        confirmationButton.setOnAction((ActionEvent e ) -> {
            try {
                if (websiteChoiceBox.getValue() != null) {
                    websiteSelection.chooseWebsite(websiteChoiceBox.getValue());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Difficulty-Selection.fxml"));
                    DifficultySelectionController difficultySelectionController =
                            new DifficultySelectionController(websiteSelection);
                    loader.setController(difficultySelectionController);
                    root = loader.load();
                    confirmationButton.getScene().setRoot(root);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        shortcutWebsiteSelection(hardNYT, "NYT Sudoku", "hard");
        shortcutWebsiteSelection(evilWebSudoku, "WebSudoku", "evil");
    }


    public void shortcutWebsiteSelection(Button button, String website, String difficulty) {
        WebsiteSelection websiteSelect = new WebsiteSelection();
        websiteSelect.chooseWebsite(website);
        websiteSelect.setDifficulty(difficulty);
        button.setOnAction((ActionEvent e) -> {
            try {
                FXMLLoader loader = new FXMLLoader((getClass().getResource("Final.fxml")));
                FinalController finalController = new FinalController(websiteSelect);
                loader.setController(finalController);
                root = loader.load();
                button.getScene().setRoot(root);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}