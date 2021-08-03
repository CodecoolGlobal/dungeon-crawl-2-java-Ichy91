package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item{

    public Sword(Cell cell) {
        super(cell);
        this.attack = 10;
        this.defense = 0;
        this.health = 0;
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
