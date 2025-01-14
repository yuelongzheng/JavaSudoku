package com.example.javasudoku;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SudokuComAu extends SudokuWebsite{

    public SudokuComAu() {
        diffArray = new String[]{"easy", "medium", "hard", "tough"};
    }

    public String[] getPuzzleAndSolution(String difficulty) {
        String[] puzzleAndSolution = new String[2];
        sourceURL = getWebsiteURL(difficulty);
        try {
            Document doc = getDocument(sourceURL);
            Elements scripts = doc.getElementsByTag("script");
            String containsUnSolved = "";
            String containsCurrPageID = "";

            for(Element script : scripts) {
                if(script.html().contains("iGridUnsolved")) {
                    containsUnSolved = script.html();
                }
                else if(script.html().contains("CurrPageID")) {
                    containsCurrPageID = script.html();
                }
            }

            String unSolvedGrid = obtainVar(containsUnSolved, "iGridUnsolved");
            String currPageID = obtainVar(containsCurrPageID, "CurrPageID");
            String solvedGrid = obtainVar(containsUnSolved, "iGridSolved");
            int indexOfFirstInt = indexOfFirstInt(currPageID);
            int indexOfLastInt = indexOfLastInt(currPageID);
            currPageID = currPageID.substring(indexOfFirstInt, indexOfLastInt);
            date = currPageID;
            title = date;
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

    private int indexOfFirstInt(String str) {
        int result = 0;
        boolean found = false;
        for(int i = 0 ; !found && i < str.length() ; i++) {
            if(Character.isDigit(str.charAt(i))) {
                result = i;
                found = true;
            }
        }
        return result;
    }

    private int indexOfLastInt(String str) {
        int result = str.length() - 1;
        while(!Character.isDigit(str.charAt(result))) {
            result--;
        }
        // Add one so the last int can be included
        // substring(start, end), end is not included
        return result + 1;
    }

}
