package org.example;

public class WebsiteSelection {
    private SudokuWebsite sudokuWebsite;
    private int difficulty;

    public WebsiteSelection() { }

    public void setVariables(SudokuWebsite website, int diff) {
        sudokuWebsite = website;
        difficulty = diff;
    }

    public WebsiteSelection(SudokuWebsite website, int diff) {
        sudokuWebsite = website;
        difficulty = diff;
    }
    public String getImportString() { return sudokuWebsite.getImportString(difficulty);}

}
