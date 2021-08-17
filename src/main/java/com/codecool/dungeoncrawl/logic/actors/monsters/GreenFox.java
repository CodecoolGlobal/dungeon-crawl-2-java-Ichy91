package com.codecool.dungeoncrawl.logic.actors.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class GreenFox extends Monster{
    public GreenFox(Cell cell) {
        super(cell);
        this.health = 20;
        this.defense = 10;
        this.attack = 15;
    }

    @Override
    public String getTileName() {
        return "greenFox";
    }

    @Override
    public void handlePickedUpItem(Item item) {}

    public char getChar() {return 'f';}

}
