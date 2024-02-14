package org.example;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;
import blazing.chain.LZSEncoding;
public class Main {
    public static void main(String[] args) throws Exception {
        String[] availableSites = {"NYT Sudoku", "WebSudoku"};
        Scanner userInput = new Scanner(System.in);
        printOptions(availableSites);
        int websiteChoice = userInput.nextInt();

        if(websiteChoice <= 1) {
            websiteChoice = 1;
        }
        else {
            websiteChoice = 2;
        }

        String[] NYTDiff = {"easy", "medium", "hard"};
        String[] WebSudokuDiff = {"easy", "medium", "hard", "evil"};
        String[][] allDiff = {NYTDiff, WebSudokuDiff};
        printOptions(allDiff[websiteChoice-1]);

        int difficulty = userInput.nextInt();
        WebsiteSelection websiteSelection = new WebsiteSelection();

        if(websiteChoice == 1) {
            websiteSelection.setVariables(new NYTSudoku(), difficulty);
        }
        else {
            websiteSelection.setVariables(new WebSudoku(), difficulty);
        }
        long start = System.currentTimeMillis();
        String importString = websiteSelection.getImportString();
        int sudokuSize = 9;
        JSONSudoku JSON = new JSONSudoku(sudokuSize);
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

    private static void printOptions(String[] options) {
        for(int i = 0 ; i < options.length; i++) {
            System.out.println(i + 1 + " for " + options[i]);
        }
    }

}