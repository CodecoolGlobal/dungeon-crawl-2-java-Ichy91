package com.codecool.dungeoncrawl.logic.actors.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Skeleton extends Monster {
    public Skeleton(Cell cell) {
        super(cell);
        this.health = 10;
        this.defense = 0;
        this.attack = 5;
    }

    @Override
    public void handlePickedUpItem(Item item) {

    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
