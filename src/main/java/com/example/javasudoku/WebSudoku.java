package com.example.javasudoku;

import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebSudoku extends SudokuWebsite {

    public WebSudoku() {
        diffArray = new String[] {"easy", "medium", "hard", "evil"};
    }

    public String[] getPuzzleAndSolution(String difficulty) {
        String[] puzzleAndSolution = new String[2];
        String url = getWebsiteURL(difficulty);
        try {
            Document doc = getDocument(url);
            String answer = doc.getElementById("cheat").attr("value");
            String editMask = doc.getElementById("editmask").attr("value");
            String pid = doc.getElementById("pid").attr("value");
            sourceURL = url + "&set_id=" + pid;
            title = "https://websudoku.com/?level=" + difficulty + "&set_id=" + pid;
            date = "";
            StringBuilder unsolvedBoard = new StringBuilder();
            for (int i = 0; i < editMask.length(); i++) {
                if (editMask.charAt(i) == '0') {
                    unsolvedBoard.append(answer.charAt(i));
                } else {
                    unsolvedBoard.append(0);
                }
            }
            puzzleAndSolution[0] = unsolvedBoard.toString();
            puzzleAndSolution[1] = answer;
        } catch (IOException e ) {
            e.printStackTrace();
        }
        return puzzleAndSolution;
    }
    public String getWebsiteURL(String difficulty) {
        String result = "https://nine.websudoku.com/?level=";
        for(int i = 0 ; i < diffArray.length; i++){
            // A number is used to ensure the correct difficulty is retrieved
            if(diffArray[i].equals(difficulty)) {
                result += i + 1;
            }
        }
        return result;
    }
}
