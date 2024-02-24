package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebSudoku extends SudokuWebsite {

    public WebSudoku() {
        diffArray = new String[] {"easy", "medium", "hard", "evil"};
    }

    public String getImportString(int difficulty) {
        String importString = "";
        String url = getWebsiteURL(difficulty);
        try {
            Document doc = getDocument(url);
            String answer = doc.getElementById("cheat").attr("value");
            String editMask = doc.getElementById("editmask").attr("value");
            String pid = doc.getElementById("pid").attr("value");
            identifier = url + "&set_id=" + pid;
            StringBuilder unsolvedBoard = new StringBuilder();
            for (int i = 0; i < editMask.length(); i++) {
                if (editMask.charAt(i) == '0') {
                    unsolvedBoard.append(answer.charAt(i));
                } else {
                    unsolvedBoard.append(0);
                }
            }
            importString = unsolvedBoard.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return importString;
    }
    public String getWebsiteURL(int difficulty) {
        return "https://nine.websudoku.com/?level=" + difficulty;
    }



}
