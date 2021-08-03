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
        if (cell.getNeighbor(dx, dy).getType() != CellType.WALL) {

            if (cell.getNeighbor(dx, dy).getActor() instanceof Monster) {
                attackToMonster((Monster) cell.getNeighbor(dx, dy).getActor(), dx, dy);

                if (cell.getNeighbor(dx, dy).getActor() != null)
                    attackToPlayer((Monster) cell.getNeighbor(dx, dy).getActor());
            }

            else acceptMove(dx, dy);
        }

        else System.out.println("Cannot move into the wall!!!");
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

    public abstract void addToPlayerInventory(Item item);

    private void acceptMove(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    private void attackToMonster(Monster monster, int dx, int dy) {
        if (monster.isAlive) {
            if (this.attack - monster.defense < 1) monster.health = monster.health;

            else monster.health = monster.health - (this.attack - monster.defense);

            if (monster.health <= 0) monster.isAlive = false;
        }
        else acceptMove(dx, dy);
    }

    private void attackToPlayer(Monster monster) {
        if (this.isAlive) {

            if (monster.attack - this.defense < 1) this.health = this.health;

            else this.health = this.health - (monster.attack - this.defense);

            if (this.health <= 0) this.isAlive = false;
        }
        else System.out.println("Die!");
    }
}
