package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class HealingPotion extends Item{

    public HealingPotion(Cell cell) {
        super(cell);

        this.attack = 0;
        this.defense = 0;
        this.health = 10;
    }

    @Override
    public String getTileName() {
        return "healingPotion";
    }
}
