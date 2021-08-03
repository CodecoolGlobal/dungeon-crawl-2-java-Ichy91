package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.actors.monsters.Monster;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected int health, defense, attack;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        if (cell.getNeighbor(dx, dy).getType() != CellType.WALL) {

            if (cell.getNeighbor(dx, dy).getActor() instanceof Monster) {
                //TODO
            }

            Cell nextCell = cell.getNeighbor(dx, dy);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

        else System.out.println("Cannot move into the wall!!!");
    }

    public void setHealth(int health) {
        this.health = health;
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
}
