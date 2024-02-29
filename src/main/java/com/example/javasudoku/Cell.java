package com.example.javasudoku;

public class Cell {
    char value;
    boolean given;

    public Cell(char inValue) {
        value = inValue;
        char notGiven = '0';
        given = value != notGiven;
    }

    @Override
    public String toString() {
        return !given ? "{}" : "{" +
                "\"value\":" + value +
                ",\"given\":" + true +
                '}';
    }
}
