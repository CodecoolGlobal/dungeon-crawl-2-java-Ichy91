package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Companion;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.HealingPotion;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private Companion companion;
    private Sword sword;
    private Armor armor;
    private Key key;
    private HealingPotion healingPotion;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCompanion(Companion companion) {
        this.companion = companion;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public void setHealingPotion(HealingPotion healingPotion) {
        this.healingPotion = healingPotion;
    }

    public Player getPlayer() {
        return player;
    }

    public Companion getCompanion() {
        return companion;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
