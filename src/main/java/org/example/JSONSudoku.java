package org.example;

import java.util.Arrays;

public class JSONSudoku {
    int size;
    Cell[][] grid;

    public JSONSudoku(int inSize) {
        size = inSize;
        grid = new Cell[size][size];
    }

    public void populateGrid(String importString) {
        for(int i = 0 ; i < size; i++) {
            for(int j = 0 ; j < size ; j++) {
                int index = i * size + j;
                char ch = importString.charAt(index);
                grid[i][j] = new Cell(ch);
            }
        }
    }

    @Override
    public String toString() {
        String result = "{" +
                "\"size\":" + size +
                ",\"grid\":[";
        for(int i = 0 ; i < size; i++) {
            result += Arrays.toString(grid[i]).replace(" ", "");
            if(i != size - 1) {
                result += ",";
            }
        }
        result += "]}";
        return result;
    }
}
