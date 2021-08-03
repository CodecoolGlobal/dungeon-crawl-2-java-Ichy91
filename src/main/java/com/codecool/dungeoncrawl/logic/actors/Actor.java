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

        if (cell.getNeighbor(dx, dy).getType() == CellType.GREEN_CLOSED_DOOR){
            if (this.hasKey("Green")){
                cell.getNeighbor(dx, dy).setType(CellType.OPENED_DOOR);
            }
        }
        if (cell.getNeighbor(dx, dy).getType() == CellType.RED_CLOSED_DOOR){
            if (this.hasKey("Red")){
                cell.getNeighbor(dx, dy).setType(CellType.OPENED_DOOR);
            }
        }
        if (cell.getNeighbor(dx, dy).getType() == CellType.BLUE_CLOSED_DOOR){
            if (this.hasKey("Blue")){
                cell.getNeighbor(dx, dy).setType(CellType.OPENED_DOOR);
            }
        }

        if (cell.getNeighbor(dx, dy).getType() != CellType.WALL
                && cell.getNeighbor(dx, dy).getType() != CellType.RED_CLOSED_DOOR
                && cell.getNeighbor(dx, dy).getType() != CellType.GREEN_CLOSED_DOOR
                && cell.getNeighbor(dx, dy).getType() != CellType.BLUE_CLOSED_DOOR) {


            Cell nextCell = cell.getNeighbor(dx, dy);
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else {
            System.out.println("Cannot move there!!!");
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

    public boolean hasKey(String color){
        return false;
    }
}
