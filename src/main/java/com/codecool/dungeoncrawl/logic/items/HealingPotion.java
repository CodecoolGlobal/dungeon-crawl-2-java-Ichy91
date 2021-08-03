package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class HealingPotion extends Item{

    public HealingPotion(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return null;
    }
}
