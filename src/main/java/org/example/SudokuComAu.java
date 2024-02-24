package org.example;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SudokuComAu extends SudokuWebsite{

    public SudokuComAu() {
        diffArray = new String[]{"easy", "medium", "hard", "tough"};
    }

    public String getImportString(int difficulty) {
        String importString = "";
        String url = getWebsiteURL(difficulty);
        try {
            Document doc = getDocument(url);
            Elements scripts = doc.getElementsByTag("script");
            String containsUnSolved = "";
            String containsCurrPageID = "";

            for(Element script : scripts) {
                if(script.html().contains("iGridUnsolved")) {
                    containsUnSolved = script.html();
                }
                if(script.html().contains("CurrPageID")) {
                    containsCurrPageID = script.html();
                }
            }

            String unSolvedGrid = obtainVar(containsUnSolved, "iGridUnsolved");
            String currPageID = obtainVar(containsCurrPageID, "CurrPageID");

            int indexOfFirstInt = indexOfFirstInt(currPageID);
            int indexOfLastInt = indexOfLastInt(currPageID);
            currPageID = currPageID.substring(indexOfFirstInt, indexOfLastInt);

            identifier = url + " " + currPageID;
            // substring is inclusive at the start, and the ( char needs to be skipped
            int start = unSolvedGrid.indexOf('(') + 1;
            int end = unSolvedGrid.indexOf(')');
            unSolvedGrid = unSolvedGrid.substring(start,end);
            unSolvedGrid = unSolvedGrid.replace(",", "");
            importString = unSolvedGrid;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return importString;
    }

    public String getWebsiteURL(int difficulty) {
        if(difficulty <= 1) {
            return "https://sudoku.com.au/";
        }
        else if(difficulty == 2) {
            return "https://sudoku.com.au/medium.aspx";
        }
        else if(difficulty >= 4) {
            return "https://sudoku.com.au/tough.aspx";
        }
        return "https://sudoku.com.au/hard.aspx";
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
