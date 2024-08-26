package com.example.javasudoku;

public class WebsiteSelection {
    private SudokuWebsite sudokuWebsite;
    private final String[] availableSties = {"NYT Sudoku", "WebSudoku", "Sudoku.com.au"};
    private String difficulty;
    private String author;

    public void setDifficulty(String level){difficulty = level;}
    public String[] getPuzzleAndSolution() {return sudokuWebsite.getPuzzleAndSolution(difficulty);}
    public void chooseWebsite(String websiteName){
        switch(websiteName) {
            case "NYT Sudoku":
                sudokuWebsite = new NYTSudoku();
                author = "New York Times";
                break;
            case "WebSudoku":
                sudokuWebsite = new WebSudoku();
                author = "WebSudoku";
                break;
            case "Sudoku.com.au":
                sudokuWebsite = new SudokuComAu();
                author = "Sudoku.com.au";
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

    public String displayDate(){
        return sudokuWebsite.date.isEmpty() ? "" : "Date of Sudoku: " + sudokuWebsite.date;
    }

    public String getTitle(){
        return sudokuWebsite.title;
    }

    public String getAuthor(){
        return author;
    }
}
