package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Companion;
import com.codecool.dungeoncrawl.logic.actors.Player;

import com.codecool.dungeoncrawl.logic.actors.monsters.Monster;
import com.codecool.dungeoncrawl.logic.items.*;

import java.util.ArrayList;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;
    private ArrayList<Monster> monsters = new ArrayList<>();

    private Player player;
    private Companion companion;
    private Sword sword;
    private Spear spear;
//    private Armor armor;
//    private Key key;
    private Armor armor;
    private Helmet helmet;
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCompanion(Companion companion) {
        this.companion = companion;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }

    public void setSpear(Spear spear) {
        this.spear = spear;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
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

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
