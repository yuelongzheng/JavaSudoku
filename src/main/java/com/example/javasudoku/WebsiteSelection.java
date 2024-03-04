package com.example.javasudoku;

public class WebsiteSelection {
    private SudokuWebsite sudokuWebsite;
    private final String[] availableSties = {"NYT Sudoku", "WebSudoku", "Sudoku.com.au"};
    private String difficulty;
    public void setDifficulty(String level){difficulty = level;}
    public String getImportString() {return sudokuWebsite.getImportString(difficulty);}

    public void chooseWebsite(String websiteName){
        switch(websiteName) {
            case "NYT Sudoku":
                sudokuWebsite = new NYTSudoku();
                break;
            case "WebSudoku":
                sudokuWebsite = new WebSudoku();
                break;
            case "Sudoku.com.au":
                sudokuWebsite = new SudokuComAu();
                break;
        }
    }
    public String[] getAvailableSties() {
        return availableSties;
    }

    public String[] getDifficultyArray(){
        return sudokuWebsite.diffArray;
    }

    public String getSourceURL(){return sudokuWebsite.sourceURL;}
    public String getDate(){
        return sudokuWebsite.date != null ? "Date of Sudoku  :  " + sudokuWebsite.date : "";}
}
