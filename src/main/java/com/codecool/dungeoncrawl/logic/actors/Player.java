package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Keys.BlueKey;
import com.codecool.dungeoncrawl.logic.items.Keys.GreenKey;
import com.codecool.dungeoncrawl.logic.items.Keys.RedKey;

import java.util.ArrayList;

public class Player extends Actor {
    private final ArrayList<Item> inventory= new ArrayList<>();

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

    public void addToPlayerInventory(Item pickedUpItem) {
        for (Item item : inventory) {
            if (item.getTileName().equals(pickedUpItem.getTileName())) return;
        }
        inventory.add(pickedUpItem);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public String getKeys() {
        String s = "";
        for (Item item: this.inventory){
            if (item instanceof RedKey){
                String redkey = "[RED]";
                s = s.concat(redkey);
            }            if (item instanceof BlueKey){
                String redkey = "[BLUE]";
                s = s.concat(redkey);
            }            if (item instanceof GreenKey){
                String redkey = "[GREEN]";
                s = s.concat(redkey);
            }
        }
        return s;
    }

    @Override
    public boolean hasKey(String color) {
        if (color.equals("Red")){
            for (Item item: this.inventory){
                if (item instanceof RedKey){
                    return true;
                }
            }
        }
        if (color.equals("Green")){
            for (Item item: this.inventory){
                if (item instanceof GreenKey){
                    return true;
                }
            }
        }
        if (color.equals("Blue")){
            for (Item item: this.inventory){
                if (item instanceof BlueKey){
                    return true;
                }
            }
        }

        return false;
    }

}
