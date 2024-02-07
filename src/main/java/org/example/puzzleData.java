package org.example;

import java.util.Arrays;

public class puzzleData {
    private int[] hints;
    private int[] puzzle;
    private int[] solution;

//    public puzzleData(int[] hints, int[] puzzle, int[] solution) {
//        this.hints = hints;
//        this.puzzle = puzzle;
//        this.solution = solution;
//    }

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
        for(int i = 0 ; i < puzzle.length ; i++) {
            result.append(puzzle[i]);
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
