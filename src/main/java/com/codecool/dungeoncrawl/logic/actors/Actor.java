package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.actors.monsters.Monster;

public abstract class Actor implements Drawable {
    protected boolean isAlive = true;
    protected Cell cell;
    protected int health, defense, attack;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {

        if (cell.getNeighbor(dx, dy).getType() == CellType.GREEN_CLOSED_DOOR) {
            if (this.hasKey("Green")) {
                cell.getNeighbor(dx, dy).setType(CellType.OPENED_DOOR);
            }
        }
        if (cell.getNeighbor(dx, dy).getType() == CellType.RED_CLOSED_DOOR) {
            if (this.hasKey("Red")) {
                cell.getNeighbor(dx, dy).setType(CellType.OPENED_DOOR);
            }
        }
        if (cell.getNeighbor(dx, dy).getType() == CellType.BLUE_CLOSED_DOOR) {
            if (this.hasKey("Blue")) {
                cell.getNeighbor(dx, dy).setType(CellType.OPENED_DOOR);
            }
        }

        if (cell.getNeighbor(dx, dy).getType() != CellType.WALL
                && cell.getNeighbor(dx, dy).getType() != CellType.RED_CLOSED_DOOR
                && cell.getNeighbor(dx, dy).getType() != CellType.GREEN_CLOSED_DOOR
                && cell.getNeighbor(dx, dy).getType() != CellType.BLUE_CLOSED_DOOR) {

                if (cell.getNeighbor(dx, dy).getActor() instanceof Monster)
                    attackToMonster((Monster) cell.getNeighbor(dx, dy).getActor(), dx, dy);

                else acceptMove(dx, dy);

            /*Cell nextCell = cell.getNeighbor(dx, dy);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell; */
        } else {
            System.out.println("Cannot move there!!!");

        }
    }

    public void setHealth(int health) {
        this.health = health;
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

    private void acceptMove(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    private void attackToMonster(Monster monster, int dx, int dy) {
        if (monster.isAlive) {
            monster.health = monster.health - (this.attack - monster.defense);

            if (monster.health <= 0) monster.isAlive = false;
        }

        else acceptMove(dx, dy);
    }

    public abstract void addToPlayerInventory(Item item);

    public boolean hasKey(String color){
        return false;
    }
}
