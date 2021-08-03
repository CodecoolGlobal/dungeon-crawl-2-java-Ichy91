package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.items.Item;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        if (cell.getNeighbor(dx, dy).getType() != CellType.WALL) {


            Cell nextCell = cell.getNeighbor(dx, dy);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else {
            System.out.println("Cannot move into the wall!!!");
        }
    }

    public boolean isStandingOnItem(){
        return this.cell.getItem() != null;
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public abstract void addToPlayerInventory(Item item);
}
