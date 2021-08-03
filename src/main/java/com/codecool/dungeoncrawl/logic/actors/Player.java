package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class Player extends Actor {
    private final ArrayList<Item> inventory= new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    public void addToPlayerInventory(Item pickedUpItem) {
        for (Item item : inventory) {
            if (item.getTileName().equals(pickedUpItem.getTileName())) return;
        }
        inventory.add(pickedUpItem);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
}
