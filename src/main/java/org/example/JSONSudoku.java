package org.example;

import java.util.Arrays;

public class JSONSudoku {
    int sudokuSize = 9;
    Cell[][] grid = new Cell[sudokuSize][sudokuSize];

    public void populateGrid(String importString) {
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
        result.append(",\"grid\":[");
        for(int i = 0 ; i < sudokuSize ; i++) {
            result.append(Arrays.toString(grid[i]).replace(" ", ""));
            result.append(",");
        }
        result.deleteCharAt(result.length()-1);
        result.append("]}");
        return result.toString();
    }
}
