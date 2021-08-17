package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Companion extends Actor{

    public Companion(Cell cell) {
        super(cell);
        this.health = 0;
        this.defense = 0;
        this.attack = 0;
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

    public char getChar() {return 'c';}


}
