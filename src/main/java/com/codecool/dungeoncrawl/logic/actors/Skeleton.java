package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public void handlePickedUpItem(Item item) {

    }

    public char getChar() {return 's';}


    @Override
    public String getTileName() {
        return "skeleton";
    }
}
