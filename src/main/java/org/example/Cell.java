package org.example;

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
        if(!given) {
            return "{}";
        }
        return  "{" +
                "\"value\":" + value +
                ",\"given\":" + true +
                '}';

    }
}
