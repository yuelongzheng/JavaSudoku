package com.example.javasudoku;

import java.util.Arrays;

public class NYTPuzzleData {
    private int[] hints;
    private int[] puzzle;
    private int[] solution;

    // setters and getters are required for jackson.databind
    public int[] getHints() {
        return hints;
    }

    public void setHints(int[] hints) {
        this.hints = hints;
    }

    public int[] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(int[] puzzle) {
        this.puzzle = puzzle;
    }

    public int[] getSolution() {
        return solution;
    }

    public void setSolution(int[] solution) {
        this.solution = solution;
    }

    public String createImportString() {
        StringBuilder result = new StringBuilder();
        for(int c : puzzle) {
            result.append(c);
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return "{" +
                "\"hints\":" +  Arrays.toString(hints) +
                ",\"puzzle\":" +  Arrays.toString(puzzle) +
                ",\"solution\":" +   Arrays.toString(solution) +
                '}';
    }
}
