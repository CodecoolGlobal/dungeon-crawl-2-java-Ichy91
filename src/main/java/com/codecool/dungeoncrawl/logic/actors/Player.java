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

    private void increaseAttack(Item item) {
        this.attack = 5 + item.getAttack();
    }

    private void decreaseAttack(Item item) {
        this.attack -= item.getAttack();
    }

    private void increaseDefense(Item item) {
        this.defense += item.getDefense();
    }

    private void decreaseDefense(Item item) {
        this.defense -= item.getDefense();
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void equipItem(Item pickedUpItem) {
        System.out.println(pickedUpItem.getAttack());
        if (!pickedUpItem.isEquiped()) {
            attack = 5;
            increaseAttack(pickedUpItem);
            increaseDefense(pickedUpItem);
            pickedUpItem.setEquiped(true);

        } else {
            decreaseAttack(pickedUpItem);
            decreaseDefense(pickedUpItem);
            pickedUpItem.setEquiped(false);
        }
    }


    private void addToInventory(Item pickedUpItem) {
        for (Item item : inventory) {
            if (item.getTileName().equals(pickedUpItem.getTileName())) return;
        }
        if (!(pickedUpItem instanceof HealingPotion) && !(pickedUpItem instanceof Key)) {
            inventory.add(pickedUpItem);
        }
    }

}
