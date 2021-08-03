package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Actor {
    private final ArrayList<Item> inventory= new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
        this.inventory.add(new Sword(cell));
    }

    public String getTileName() {
        return "player";
    }

//    public void addDefensiveItemIntoInventory(Item item) {
//        defenseItems.put(item);
//    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
}
