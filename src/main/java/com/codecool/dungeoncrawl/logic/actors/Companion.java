package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Companion extends Actor{

    public Companion(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "companion";
    }
}
