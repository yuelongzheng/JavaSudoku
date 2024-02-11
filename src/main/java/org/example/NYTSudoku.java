package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NYTSudoku implements SudokuWebsite{
    public String getImportString(int difficulty) {
        String importString = "";
        String userAgentString = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:122.0) Gecko/20100101 Firefox/122.0";
        String url = getWebsiteURL(difficulty);
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent(userAgentString)
                    .header("Accept-Language", "*")
                    .get();
            Elements scripts = doc.getElementsByTag("script");
            String gameData = "";
            for (Element script : scripts) {
                if (script.html().contains("window.gameData")) {
                    gameData = script.html();
                }
            }

            String JSONGameData = gameData.substring(gameData.indexOf("{"));
            ObjectMapper objectMapper =  new ObjectMapper();
            NYTGameData nytGameData = objectMapper.readValue(JSONGameData, NYTGameData.class);
            System.out.println(url + "  " + nytGameData.getDisplayDate());

            String stringPuzzleData = nytGameData.getDifficulty(difficulty).getPuzzle_data().toString();
            puzzleData data = objectMapper.readValue(stringPuzzleData, puzzleData.class);
            importString = data.createImportString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return importString;
    }
    public String getWebsiteURL(int difficulty) {
        if(difficulty <= 1) {
            return "https://www.nytimes.com/puzzles/sudoku/easy";
        }
        if(difficulty == 2) {
            return "https://www.nytimes.com/puzzles/sudoku/medium";
        }
        return "https://www.nytimes.com/puzzles/sudoku/hard";
    }




}
