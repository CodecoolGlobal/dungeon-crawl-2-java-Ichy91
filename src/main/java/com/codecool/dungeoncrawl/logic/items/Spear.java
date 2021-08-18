package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Spear extends Item {

    public Spear(Cell cell) {
        super(cell);
        this.attack = 7;
        this.defense = 0;
        this.health = 0;
    }
    public Spear() {
        this.attack = 7;
        this.defense = 0;
        this.health = 0;
    }

    @Override
    public String getTileName() {
        return "spear";
    }

    public char getChar() {return 'P';}

}
