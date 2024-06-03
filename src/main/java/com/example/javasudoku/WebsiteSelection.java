package com.example.javasudoku;

public class WebsiteSelection {
    private SudokuWebsite sudokuWebsite;
    private final String[] availableSties = {"NYT Sudoku", "WebSudoku", "Sudoku.com.au"};
    private String difficulty;
    private String author;
    public void setDifficulty(String level){difficulty = level;}
    public String getImportString() {return sudokuWebsite.getImportString(difficulty);}

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

    public SudokuWebsite getSudokuWebsite() {
        return sudokuWebsite;

    }
    public String[] getDifficultyArray(){
        return sudokuWebsite.diffArray;
    }

    public String getSourceURL(){return sudokuWebsite.sourceURL;}
    public String getDate(){
        return sudokuWebsite.date != null ? sudokuWebsite.date : "";
    }
    public String getTitle(){
        String diff = difficulty.substring(0,1).toUpperCase() + difficulty.substring(1, difficulty.length());
        String title = sudokuWebsite.date;
        if(sudokuWebsite.getClass() != WebSudoku.class) {
            title += " " + diff;
        }
        title = "\"" + title + "\"";
        return title;
    }

    public String getAuthor(){
        return "\"" + author + "\"";
    }
}
