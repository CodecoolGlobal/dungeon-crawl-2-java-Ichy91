package com.codecool.dungeoncrawl.logic.items.Keys;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Key;

public class GreenKey extends Key {

    public GreenKey(Cell cell) {
        super(cell);
    }
    public GreenKey() {}

    @Override
    public String getTileName() {
        return "green-key";
    }

    public char getChar() {return 'g';}

}
