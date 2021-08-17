package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.HealingPotion;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Keys.BlueKey;
import com.codecool.dungeoncrawl.logic.items.Keys.GreenKey;
import com.codecool.dungeoncrawl.logic.items.Keys.RedKey;

import java.util.ArrayList;

public class Player extends Actor {
    private ArrayList<Item> inventory = new ArrayList<>();
    private final ArrayList<String> equippedItems = new ArrayList<>();
    private String tileName = "player";

    private String playerName = "";

    public Player(Cell cell) {
        super(cell);
        this.health = 20;
        this.defense = 0;
        this.attack = 5;
    }

    @Override
    public String getTileName() {
        return tileName;
    }

    public void handlePickedUpItem(Item pickedUpItem) {
        addToInventory(pickedUpItem);
        if (pickedUpItem instanceof HealingPotion) healPlayer(pickedUpItem.getHealth());
    }

    private void increaseAttack(Item item) {
        this.attack += item.getAttack();
    }

    private void decreaseAttack(Item item) {
        this.attack = 5;
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

        if (!pickedUpItem.isEquiped()) {
            if (pickedUpItem.getAttack() > 0) {
                attack = 5;
                removeAllWeaponsFromEquippedElements();
            }

            equippedItems.add(pickedUpItem.getTileName());
            visualAppearanceOfPlayer();
            increaseAttack(pickedUpItem);
            increaseDefense(pickedUpItem);
            pickedUpItem.setEquiped(true);

        } else {
            equippedItems.remove(pickedUpItem.getTileName());
            visualAppearanceOfPlayer();
            decreaseAttack(pickedUpItem);
            decreaseDefense(pickedUpItem);
            pickedUpItem.setEquiped(false);
        }
    }

    private void addToInventory(Item pickedUpItem) {
        for (Item item : inventory) {
            if (item.getTileName().equals(pickedUpItem.getTileName())) return;
        }
        if (!(pickedUpItem instanceof HealingPotion)) {
            inventory.add(pickedUpItem);
        }
    }

    private void visualAppearanceOfPlayer() {
        if (equippedItems.contains("sword")) tileName = "playerWithSword";
        if (equippedItems.contains("spear")) tileName = "playerWithSpear";
        if (equippedItems.contains("armor")) tileName = "playerWithArmor";
        if (equippedItems.contains("helmet")) tileName = "playerWithHelmet";
        if (equippedItems.contains("helmet") && equippedItems.contains("armor")) tileName = "playerWithHelmetAndArmor";
        if (equippedItems.contains("sword") && equippedItems.contains("armor")) tileName = "playerWithSwordAndArmor";
        if (equippedItems.contains("sword") && equippedItems.contains("helmet")) tileName = "playerWithSwordAndHelmet";
        if (equippedItems.contains("spear") && equippedItems.contains("armor")) tileName = "playerWithSpearAndArmor";
        if (equippedItems.contains("spear") && equippedItems.contains("helmet")) tileName = "playerWithSpearAndHelmet";
        if (equippedItems.contains("sword") && equippedItems.contains("helmet") && equippedItems.contains("armor"))
            tileName = "playerWithSwordAndHelmetAndArmor";
        if (equippedItems.contains("spear") && equippedItems.contains("helmet") && equippedItems.contains("armor"))
            tileName = "playerWithSpearAndHelmetAndArmor";
        else if (equippedItems.isEmpty()) tileName = "player";
    }

    private void removeAllWeaponsFromEquippedElements() {
        equippedItems.removeIf(item -> item.equals("sword") || item.equals("spear"));
        for (Item item : inventory) {
            if (item.getTileName().equals("sword") || item.getTileName().equals("spear")) item.setEquiped(false);
        }
    }
    public String getKeys() {
        String s = "";
        for (Item item : this.inventory) {
            if (item instanceof RedKey) {
                String redkey = "[RED]";
                s = s.concat(redkey);
            }
            if (item instanceof BlueKey) {
                String redkey = "[BLUE]";
                s = s.concat(redkey);
            }
            if (item instanceof GreenKey) {
                String redkey = "[GREEN]";
                s = s.concat(redkey);
            }
        }
        return s;
    }

        @Override
        public boolean hasKey (String color){
            if (color.equals("Red")) {
                for (Item item : this.inventory) {
                    if (item instanceof RedKey) {
                        return true;
                    }
                }
            }
            if (color.equals("Green")) {
                for (Item item : this.inventory) {
                    if (item instanceof GreenKey) {
                        return true;
                    }
                }
            }
            if (color.equals("Blue")) {
                for (Item item : this.inventory) {
                    if (item instanceof BlueKey) {
                        return true;
                    }
                }
            }

            return false;
        }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void fillUpEquipedItems() {
        for (Item item : inventory) {
            if (item.isEquiped()) equippedItems.add(item.getTileName());
        }
        visualAppearanceOfPlayer();
    }

    public char getChar() {return '@';}

    public ArrayList<String> getEquippedItems() {
        return equippedItems;
    }
}
