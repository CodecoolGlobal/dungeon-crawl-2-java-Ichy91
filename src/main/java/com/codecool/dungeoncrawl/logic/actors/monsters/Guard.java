package com.codecool.dungeoncrawl.logic.actors.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Guard extends Monster{
    public Guard(Cell cell) {
        super(cell);
        this.health = 40;
        this.defense = 15;
        this.attack = 20;
    }

    @Override
    public void monsterMove(Monster monster) {
        monster.acceptMove(0, 0);
    }

    @Override
    public String getTileName() {
        return "guard";
    }

    @Override
    public void handlePickedUpItem(Item item) {}
}
