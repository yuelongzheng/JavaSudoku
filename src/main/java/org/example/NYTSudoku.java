package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NYTSudoku extends SudokuWebsite{
    public NYTSudoku() {
        diffArray = new String[]{"easy", "medium", "hard"};
    }

    public String getImportString(int difficulty) {
        String importString = "";
        String url = getWebsiteURL(difficulty);
        try {
            Document doc = getDocument(url);
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
            identifier = url + " " + nytGameData.getDisplayDate();

            String stringPuzzleData = nytGameData.getDifficulty(difficulty).getPuzzle_data().toString();
            NYTPuzzleData data = objectMapper.readValue(stringPuzzleData, NYTPuzzleData.class);
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
        if(difficulty >= 3) {
            return "https://www.nytimes.com/puzzles/sudoku/hard";
        }
        return "https://www.nytimes.com/puzzles/sudoku/medium";
    }


}
