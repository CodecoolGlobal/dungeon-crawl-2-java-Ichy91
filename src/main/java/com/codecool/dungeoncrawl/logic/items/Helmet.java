package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Helmet extends Item {

    public Helmet(Cell cell) {
        super(cell);
        this.attack = 0;
        this.defense = 5;
        this.health = 0;
    }

    public Helmet() {
        this.attack = 0;
        this.defense = 5;
        this.health = 0;
    }

    @Override
    public String getTileName() {
        return "helmet";
    }
    public char getChar() {return 'E';}

}
