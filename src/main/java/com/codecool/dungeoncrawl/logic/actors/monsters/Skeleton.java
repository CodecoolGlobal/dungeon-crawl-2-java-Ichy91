package com.codecool.dungeoncrawl.logic.actors.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Skeleton extends Monster {
    public Skeleton(Cell cell) {
        super(cell);
        this.health = 15;
        this.defense = 0;
        this.attack = 2;
    }

    @Override
    public void addToPlayerInventory(Item item) {
        
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}