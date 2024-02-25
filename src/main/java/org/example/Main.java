package org.example;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

import blazing.chain.LZSEncoding;

public class Main {
    public static void main(String[] args){
        WebsiteSelection websiteSelection = new WebsiteSelection();
        websiteSelection.printAvailableSites();
        Scanner userInput = new Scanner(System.in);
        int websiteChoice = userInput.nextInt();
        websiteSelection.chooseWebsite(websiteChoice);
        websiteSelection.printDifficulties();
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


}