package com.codecool.dungeoncrawl.logic.items.Keys;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Key;

public class RedKey extends Key {
    public RedKey(Cell cell) {
        super(cell);
    }

    public RedKey() {}


    @Override
    public String getTileName() {
        return "red-key";
    }

    public char getChar() {return 'r';}

}
