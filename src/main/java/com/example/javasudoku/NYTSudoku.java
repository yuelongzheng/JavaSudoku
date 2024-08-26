package com.example.javasudoku;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NYTSudoku extends SudokuWebsite{
    public NYTSudoku() {
        diffArray = new String[]{"easy", "medium", "hard"};
    }

    public String[] getPuzzleAndSolution(String difficulty) {
        String[] puzzleAndSolution = new String[2];
        sourceURL = getWebsiteURL(difficulty);
        try {
            Document doc = getDocument(sourceURL);
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
            date = nytGameData.getDisplayDate();
            String diff = difficulty.substring(0,1).toUpperCase() + difficulty.substring(1);
            title = date + " " + diff;
            String stringPuzzleData = nytGameData.getDifficulty(difficulty).getPuzzle_data().toString();
            NYTPuzzleData data = objectMapper.readValue(stringPuzzleData, NYTPuzzleData.class);
            puzzleAndSolution[0] = data.getPuzzleDataString();
            puzzleAndSolution[1] = data.getSolutionString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return puzzleAndSolution;
    }


    public String getWebsiteURL(String difficulty) {
        return "https://www.nytimes.com/puzzles/sudoku/" + difficulty;
    }
}
