package org.example;

public class Cell {
    char value;
    boolean given;

    public Cell(char inValue) {
        value = inValue;
        if(value == '0') {
            given = false;
        }
        else {
            given = true;
        }
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
