package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Actor {
//    private final HashMap<String, Item> attackItems= new HashMap<>();
//    private final HashMap<String, Item> defenseItems= new HashMap<>();

    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

//    public void addDefensiveItemIntoInventory(Item item) {
//        defenseItems.put(item);
//    }
//
//    public void addAttackItemIntoInventory(Item item) {
//        attackItems.put(item);
//    }

//    public ArrayList<Item> refreshInventory() {
//        ArrayList<Item> inventory = new ArrayList<Item>();
//        for (Item attackItem : attackItems) {
//            inventory.add(attackItem);
//        }
//
//        for (Item defenseItem : defenseItems) {
//            inventory.add(defenseItem);
//        }
//
//        return inventory;
//    }
}
