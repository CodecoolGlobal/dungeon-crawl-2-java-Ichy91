package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.HealingPotion;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;

import java.util.ArrayList;

public class Player extends Actor {
    private final ArrayList<Item> inventory= new ArrayList<>();
    private final ArrayList<String> equipedItems = new ArrayList<>();
    private String tileName = "player";


    public Player(Cell cell) {
        super(cell);
        this.health = 20;
        this.defense = 0;
        this.attack = 5;
    }

    public String getTileName() {
        return tileName;
    }

    public void handlePickedUpItem(Item pickedUpItem) {
        addToInventory(pickedUpItem);
        if (pickedUpItem instanceof HealingPotion) healPlayer(pickedUpItem.getHealth());
        else if (pickedUpItem instanceof Key) return;
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

            equipedItems.add(pickedUpItem.getTileName());
            visualAppearanceOfPlayer();
            increaseAttack(pickedUpItem);
            increaseDefense(pickedUpItem);
            pickedUpItem.setEquiped(true);

        } else {
            equipedItems.remove(pickedUpItem.getTileName());
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
        if (!(pickedUpItem instanceof HealingPotion) && !(pickedUpItem instanceof Key)) {
            inventory.add(pickedUpItem);
        }
    }

    private void visualAppearanceOfPlayer() {
        if (equipedItems.contains("sword")) tileName = "playerWithSword";
        if (equipedItems.contains("spear")) tileName = "playerWithSpear";
        if (equipedItems.contains("armor")) tileName = "playerWithArmor";
        if (equipedItems.contains("helmet")) tileName = "playerWithHelmet";
        if (equipedItems.contains("helmet") && equipedItems.contains("armor")) tileName = "playerWithHelmetAndArmor";
        if (equipedItems.contains("sword") && equipedItems.contains("armor")) tileName = "playerWithSwordAndArmor";
        if (equipedItems.contains("sword") && equipedItems.contains("helmet")) tileName = "playerWithSwordAndHelmet";
        if (equipedItems.contains("spear") && equipedItems.contains("armor")) tileName = "playerWithSpearAndArmor";
        if (equipedItems.contains("spear") && equipedItems.contains("helmet")) tileName = "playerWithSpearAndHelmet";
        if (equipedItems.contains("sword") && equipedItems.contains("helmet") && equipedItems.contains("armor")) tileName = "playerWithSwordAndHelmetAndArmor";
        if (equipedItems.contains("spear") && equipedItems.contains("helmet") && equipedItems.contains("armor")) tileName = "playerWithSpearAndHelmetAndArmor";
        else if (equipedItems.isEmpty()) tileName = "player";
    }

    private void removeAllWeaponsFromEquippedElements() {
        equipedItems.removeIf(item -> item.equals("sword") || item.equals("spear"));
        for (Item item : inventory) {
            if (item.getTileName().equals("sword") || item.getTileName().equals("spear")) item.setEquiped(false);
        }
    }

}
