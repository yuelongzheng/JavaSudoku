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

    public String getSolutionString() {
        return createString(solution);
    }

    public String getPuzzleDataString() {
        return createString(puzzle);
    }

    public String createString(int[] arr) {
        StringBuilder result = new StringBuilder();
        for(int c : arr) {
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
