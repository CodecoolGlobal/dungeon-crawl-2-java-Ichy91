package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Armor extends Item{

    public Armor(Cell cell) {
        super(cell);
        this.attack = 0;
        this.defense = 10;
        this.health = 0;
    }

    @Override
    public String getTileName() {
        return "armor";
    }

    public char getChar() {return 'A';}

}
