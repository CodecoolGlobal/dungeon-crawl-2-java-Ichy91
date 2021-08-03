package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Companion extends Actor{

    public Companion(Cell cell) {
        super(cell);
    }

    @Override
    public void handlePickedUpItem(Item item) {}

    @Override
    public String getTileName() {
        return "companion";
    }

    public void followPlayer(Cell playerCell) {
        cell.setActor(null);
        playerCell.setActor(this);
        cell = playerCell;
    }

}
