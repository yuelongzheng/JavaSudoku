package com.example.javasudoku;

import blazing.chain.LZSEncoding;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinalController implements Initializable {
    @FXML
    private TextArea finalURLTextArea;
    @FXML
    private TextArea sourceURLTextArea;
    @FXML
    private Button copyFinalURL;
    @FXML
    private Button copySourceURL;
    @FXML
    private Label identifierLabel;
    @FXML
    private Button newWebsiteButton;
    private WebsiteSelection websiteSelection;
    private String finalURL;
    private Parent root;
    private Stage stage;
    private Scene scene;
    public FinalController(WebsiteSelection websiteSelect) {
        websiteSelection = websiteSelect;
        String importString = websiteSelection.getImportString();
        JSONSudoku JSON = new JSONSudoku();
        JSON.populateGrid(importString);
        String base64Compress = LZSEncoding.compressToBase64(JSON.toString());
        String websiteString = "https://sudokupad.app/fpuz";
        finalURL = websiteString + base64Compress;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        finalURLTextArea.setText(finalURL);
        sourceURLTextArea.setText(websiteSelection.getSourceURL());
        identifierLabel.setText(websiteSelection.getDate());
        copyFinalURL.setOnAction((ActionEvent e) -> copyTextToClipboard(e, finalURLTextArea));
        copySourceURL.setOnAction((ActionEvent e) -> copyTextToClipboard(e, sourceURLTextArea));
        newWebsiteButton.setOnAction((ActionEvent e) -> {
            try {
                chooseAnotherWebsite(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void copyTextToClipboard(ActionEvent event, TextArea textArea) {
        StringSelection stringSelection = new StringSelection(textArea.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public void chooseAnotherWebsite(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sudoku-Website-Selection.fxml"));
        root = loader.load();
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        String cssLocation = this.getClass().getResource("SceneStyle.css").toExternalForm();
        scene = new Scene(root);
        scene.getStylesheets().add(cssLocation);
        stage.setScene(scene);
        stage.show();
    }
}
