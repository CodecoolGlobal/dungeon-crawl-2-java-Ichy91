package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
        this.health = 20;
        this.defense = 0;
        this.attack = 5;
    }

    @Override
    public String getTileName() {
        return "player";
    }
}
