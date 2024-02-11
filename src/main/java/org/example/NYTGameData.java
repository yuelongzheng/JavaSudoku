package org.example;

public class NYTGameData {
    private String displayDate;
    private Difficulty easy;
    private Difficulty hard;
    private Difficulty medium;

//    public NYTGameData(String displayDate, Difficulty easy, Difficulty hard, Difficulty medium) {
//        this.displayDate = displayDate;
//        this.easy = easy;
//        this.hard = hard;
//        this.medium = medium;
//    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }

    public Difficulty getEasy() {
        return easy;
    }

    public void setEasy(Difficulty easy) {
        this.easy = easy;
    }

    public Difficulty getHard() {
        return hard;
    }

    public void setHard(Difficulty hard) {
        this.hard = hard;
    }

    public Difficulty getMedium() {
        return medium;
    }

    public void setMedium(Difficulty medium) {
        this.medium = medium;
    }

    public Difficulty getDifficulty(int level) {
        if(level == 1) {
            return easy;
        }
        else if(level == 2) {
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
