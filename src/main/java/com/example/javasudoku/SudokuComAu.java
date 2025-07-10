package com.example.javasudoku;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SudokuComAu extends SudokuWebsite{

    public SudokuComAu() {
        diffArray = new String[]{"Easy", "Medium", "Hard", "Expert", "Evil"};
    }

    public String[] getPuzzleAndSolution(String difficulty) {
        String[] puzzleAndSolution = new String[2];
        sourceURL = getWebsiteURL(difficulty);
        try {
            Document doc = getDocument(sourceURL);
            Elements scripts = doc.getElementsByTag("script");
            String containsUnSolved = "";

            for(Element script : scripts) {
                if(script.html().contains("iGridUnsolved")) {
                    containsUnSolved = script.html();
                }
            }

            String unSolvedGrid = obtainVar(containsUnSolved, "iGridUnsolved");
            String currPageID = obtainVar(containsUnSolved, "sSourceName");
            String solvedGrid = obtainVar(containsUnSolved, "iGridSolved");
            String[] currPage = currPageID.split("\\r");
            int index = 0;
            for(int i = 0 ; i < currPage.length ; i++){
                if(currPage[i].contains("sTodaysDate")){
                    index = i;
                }
            }
            String todaysDate = currPage[index];
            currPageID = currPage[index].substring(todaysDate.indexOf('\'') +  1, todaysDate.lastIndexOf('\''));
            date = currPageID;
            title = difficulty + " " + date;
            puzzleAndSolution[0] = getDataString(unSolvedGrid);
            puzzleAndSolution[1] = getDataString(solvedGrid);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return puzzleAndSolution;
    }

    public String getDataString(String raw) {
        int start = raw.indexOf('(') + 1;
        int end = raw.indexOf(')');
        String result = raw.substring(start, end);
        result = result.replace(",", "");
        return result;
    }
    public String getWebsiteURL(String difficulty) {
        if(difficulty.equals("easy")){
            return "https://sudoku.com.au/";
        }
        return "https://sudoku.com.au/" + difficulty +".aspx";
    }

    private String obtainVar(String script, String var){
        String result = "";
        String[] lines = script.split("\\r?\\n");
        for(String line : lines) {
            if (line.contains(var)) {
                result = line;
            }
        }
        return result;
    }
}
