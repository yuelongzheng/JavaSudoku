package com.example.javasudoku;

import java.util.Arrays;

public class JSONSudoku {
    private final int sudokuSize = 9;
    private final Cell[][] grid = new Cell[sudokuSize][sudokuSize];
    WebsiteSelection websiteSelection;
    String[] puzzleAndSolution;

    public JSONSudoku(WebsiteSelection wb) {
        websiteSelection = wb;
        puzzleAndSolution = wb.getPuzzleAndSolution();
    }

    public void populateGrid() {
        String importString = puzzleAndSolution[0];
        for(int i = 0 ; i < sudokuSize * sudokuSize ; i++) {
            int row = i / sudokuSize;
            int col = i % sudokuSize;
            char ch = importString.charAt(i);
            grid[row][col] = new Cell(ch);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{\"size\":");
        result.append(sudokuSize);
        result.append(",\"title\":");
        result.append("\"" + websiteSelection.getTitle() + "\"");
        result.append(",\"author\":");
        result.append("\"" + websiteSelection.getAuthor() + "\"");
        result.append(",\"ruleset\":");
        result.append("\"Normal Sudoku Rules Apply\"");
        result.append(",\"grid\":[");
        for(int i = 0 ; i < sudokuSize ; i++) {
            result.append(Arrays.toString(grid[i]).replace(" ", ""));
            result.append(",");
        }
        result.deleteCharAt(result.length()-1);
        result.append("]");
        result.append(",\"solution\":[");
        String solution = puzzleAndSolution[1];
        for(int i = 0 ; i < solution.length() ; i++) {
            result.append("\"");
            result.append(solution.charAt(i));
            result.append("\"");
            result.append(",");
        }
        result.deleteCharAt(result.length()-1);
        result.append("]}");
        return result.toString();
    }
}
