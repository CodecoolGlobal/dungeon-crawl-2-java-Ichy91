package com.codecool.dungeoncrawl.logic.items.Keys;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Key;

public class BlueKey extends Key {

    public BlueKey(Cell cell) {
        super(cell);
    }
    public BlueKey() {}

    @Override
    public String getTileName() {
        return "blue-key";
    }

    public char getChar() {return 'b';}

}
