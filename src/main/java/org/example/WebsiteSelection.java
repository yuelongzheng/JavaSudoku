package org.example;

public class WebsiteSelection {
    private SudokuWebsite sudokuWebsite;
    private int difficulty;
    private String[] availableSties = {"NYT Sudoku", "WebSudoku", "Sudoku.com.au"};

    public void setDifficulty(int diff) {
        difficulty = boundChoices(diff, sudokuWebsite.diffArray.length);
    }

    public String getImportString() {
        return sudokuWebsite.getImportString(difficulty);
    }

    public void printIdentifier() {
        System.out.println(sudokuWebsite.getIdentifier());
    }

    private void printArray(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + 1 + " for " + arr[i]);
        }
    }

    public void printDifficulties() {
        printArray(sudokuWebsite.diffArray);
    }

    public void printAvailableSites() {
        printArray(availableSties);
    }

    // Ensure that user input does not cause an array exception
    // Returns 1 to arrSize
    private int boundChoices(int choice, int arrSize) {
        return Math.min(Math.max(choice, 1), arrSize);
    }

    public void chooseWebsite(int websiteChoice) {
        websiteChoice = boundChoices(websiteChoice, availableSties.length);
        switch (websiteChoice) {
            case 1:
                sudokuWebsite = new NYTSudoku();
                break;
            case 2:
                sudokuWebsite = new WebSudoku();
                break;
            case 3:
                sudokuWebsite = new SudokuComAu();
                break;
        }
    }

}
