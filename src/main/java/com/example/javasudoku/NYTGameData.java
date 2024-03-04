package com.example.javasudoku;
public class NYTGameData {
    private String displayDate;
    private NYTDifficulty easy;
    private NYTDifficulty hard;
    private NYTDifficulty medium;

    // setters and getters are required for jackson.databind
    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }

    public NYTDifficulty getEasy() {
        return easy;
    }

    public void setEasy(NYTDifficulty easy) {
        this.easy = easy;
    }

    public NYTDifficulty getHard() {
        return hard;
    }

    public void setHard(NYTDifficulty hard) {
        this.hard = hard;
    }

    public NYTDifficulty getMedium() {
        return medium;
    }

    public void setMedium(NYTDifficulty medium) {
        this.medium = medium;
    }

    public NYTDifficulty getDifficulty(String level){
        if(level.equals("easy")) {
            return easy;
        }
        else if(level.equals("medium")){
            return medium;
        }
        return hard;
    }

    @Override
    public String toString() {
        return "NYTGameData{" +
                "displayDate='" + displayDate + '\'' +
                ", easy='" + easy + '\'' +
                ", hard='" + hard + '\'' +
                ", medium='" + medium + '\'' +
                '}';
    }
}
