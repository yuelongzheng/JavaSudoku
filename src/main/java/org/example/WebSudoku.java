package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebSudoku implements SudokuWebsite {
    public String getImportString(int difficulty) {
        String importString = "";
        String userAgentString = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:122.0) Gecko/20100101 Firefox/122.0";
        String url = getWebsiteURL(difficulty);
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent(userAgentString)
                    .header("Accept-Language", "*")
                    .get();
            Elements allElements = doc.getAllElements();
            String answer = doc.getElementById("cheat").attr("value");
            String editMask = doc.getElementById("editmask").attr("value");
            String pid = doc.getElementById("pid").attr("value");
            System.out.println(url + "&set_id=" + pid);
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
        if(difficulty >= 4) {
            return "https://nine.websudoku.com/?level=" + '4';
        }
        else if (difficulty <= 1) {
            return "https://nine.websudoku.com/?level=" + '1';
        }
        return "https://nine.websudoku.com/?level=" + difficulty;
    }



}
