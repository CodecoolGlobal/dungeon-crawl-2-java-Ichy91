package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.HealingPotion;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;

import java.util.ArrayList;

public class Player extends Actor {
    private final ArrayList<Item> inventory= new ArrayList<>();


    public Player(Cell cell) {
        super(cell);
        this.health = 20;
        this.defense = 0;
        this.attack = 5;
    }

    public String getTileName() {
        return "player";
    }

    public void handlePickedUpItem(Item pickedUpItem) {
        addToInventory(pickedUpItem);
        if (pickedUpItem instanceof HealingPotion) healPlayer(pickedUpItem.getHealth());
        else if (pickedUpItem instanceof Key) return;
    }

    public void increaseAttack(Item item) {
        this.attack = 5 + item.getAttack();
    }

    public void increaseDefense(Item item) {
        this.defense = 5 + item.getDefense();
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    private void addToInventory(Item pickedUpItem) {
        for (Item item : inventory) {
            if (item.getTileName().equals(pickedUpItem.getTileName())) return;
        }
        if (!(pickedUpItem instanceof HealingPotion) && !(pickedUpItem instanceof Key)) {
            inventory.add(pickedUpItem);
            increaseAttack(pickedUpItem);
            increaseDefense(pickedUpItem);
        }
    }
}
