package com.example.javasudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;
import blazing.chain.LZSEncoding;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        WebsiteSelection websiteSelection = new WebsiteSelection();
        websiteSelection.printAvailableSites();
        Scanner userInput = new Scanner(System.in);
        consumeNonIntInput(userInput);
        int websiteChoice = userInput.nextInt();
        websiteSelection.chooseWebsite(websiteChoice);
        websiteSelection.printDifficulties();
        consumeNonIntInput(userInput);
        int difficulty = userInput.nextInt();
        websiteSelection.setDifficulty(difficulty);

        long start = System.currentTimeMillis();
        String importString = websiteSelection.getImportString();
        websiteSelection.printIdentifier();
        JSONSudoku JSON = new JSONSudoku();
        JSON.populateGrid(importString);
        String base64Compress = LZSEncoding.compressToBase64(JSON.toString());
        String url = "https://sudokupad.app/fpuz" + base64Compress;
        System.out.println(url);
        StringSelection stringSelection = new StringSelection(url);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        long finish = System.currentTimeMillis();
        System.out.println("Sudoku was created in " + (finish - start) / 1000.0 + " seconds");
    }

    public static void main(String[] args) {
        launch();
    }

    private static void consumeNonIntInput(Scanner input) {
        while(!input.hasNextInt()){
            input.next();
        }
    }
}