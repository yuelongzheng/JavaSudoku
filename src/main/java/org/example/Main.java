package org.example;



import com.fasterxml.jackson.databind.ObjectMapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.nytimes.com/puzzles/sudoku/hard")
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:122.0) Gecko/20100101 Firefox/122.0")
                        .header("Accept-Language", "*")
                        .get();
        Elements scripts = doc.getElementsByTag("script");
        String gameData = "";
        for(Element script : scripts) {
            if(script.html().contains("window.gameData")) {
                gameData = script.html();
            }
        }
        String JSONGameData = gameData.substring(gameData.indexOf("{"));
        System.out.println(JSONGameData);
        ObjectMapper objectMapper =  new ObjectMapper();
        NYTGameData nytGameData = objectMapper.readValue(JSONGameData, NYTGameData.class);
        String easyPuzzleData = nytGameData.getEasy().getPuzzle_data().toString();
        String mediumPuzzleData = nytGameData.getMedium().getPuzzle_data().toString();
        String hardPuzzleData = nytGameData.getHard().getPuzzle_data().toString() ;
        puzzleData easy = objectMapper.readValue(easyPuzzleData, puzzleData.class);
        puzzleData medium = objectMapper.readValue(mediumPuzzleData, puzzleData.class);
        puzzleData hard = objectMapper.readValue(hardPuzzleData, puzzleData.class);
        System.out.println(easy.createImportString());
        System.out.println(medium);
        System.out.println(hard);

        CreateSudokuPad createSudokuPad = new CreateSudokuPad();
        createSudokuPad.createSudokduPadLink(medium.createImportString());

//        try {
//            Document doc = Jsoup.connect("https://nine.websudoku.com/?level=4")
//                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:122.0) Gecko/20100101 Firefox/122.0")
//                    .header("Accept-Language", "*")
//                    .get();
//            Elements allElements = doc.getAllElements();
//            String answer = doc.getElementById("cheat").attr("value");
//            String editMask = doc.getElementById("editmask").attr("value");
//            String pid = doc.getElementById("pid").attr("value");
//
//
//            StringBuilder unsolvedBoard = new StringBuilder();
//            for (int i = 0; i < editMask.length(); i++) {
//                if (editMask.charAt(i) == '0') {
//                    unsolvedBoard.append(answer.charAt(i));
//                } else {
//                    unsolvedBoard.append(0);
//                }
//            }
//            System.out.println(answer);
//            System.out.println(editMask);
//            System.out.println(unsolvedBoard);
//            System.out.println(pid);
////            for (Element element : allElements) {
////                System.out.println(element);
////                System.out.println("..............................");
////            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}