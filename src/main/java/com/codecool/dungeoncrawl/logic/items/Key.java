package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Item{

    public Key(Cell cell) {
        super(cell);
        this.attack = 0;
        this.defense = 0;
        this.health = 0;
    }

    @Override
    public String getTileName() {

        return "key";
    }
}
