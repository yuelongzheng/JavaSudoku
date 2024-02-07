package org.example;

public class Difficulty {
    private String day_of_week;
    private String difficulty;
    private String print_date;
    private String published;
    private String puzzle_id;
    private String version;
    private puzzleData puzzle_data;

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getPrint_date() {
        return print_date;
    }

    public void setPrint_date(String print_date) {
        this.print_date = print_date;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getPuzzle_id() {
        return puzzle_id;
    }

    public void setPuzzle_id(String puzzle_id) {
        this.puzzle_id = puzzle_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public puzzleData getPuzzle_data() {
        return puzzle_data;
    }

    public void setPuzzle_data(puzzleData puzzle_data) {
        this.puzzle_data = puzzle_data;
    }

    @Override
    public String toString() {
        return "{" +
                "\"day_of_week\":" + "\"" + day_of_week + "\"" +
                ", \"difficulty\":" + "\"" +  difficulty + "\"" +
                ", \"print_date\":" + "\"" + print_date + "\"" +
                ", \"published\":" + "\"" + published + "\"" +
                ",\"puzzle_id\":" + "\"" + puzzle_id + "\"" +
                ", \"version\":" + "\"" + version  + "\"" +
                ", \"puzzle_data\":" + puzzle_data +
                '}';
    }
}