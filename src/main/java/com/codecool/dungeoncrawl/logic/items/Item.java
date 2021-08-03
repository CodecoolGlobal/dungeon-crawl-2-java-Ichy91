package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;
    protected int defense, attack, health;

    public Item (Cell cell){
        this.cell = cell;
        this.cell.setItem(this);
    }

    public Cell getCell() {
        return cell;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public void addToInventory(){
        this.cell.getActor().addToPlayerInventory(this.cell.getItem());

        this.cell.setType(CellType.FLOOR);
        this.cell.setItem(null);
    }

}
