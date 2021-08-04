package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.MapLoader;
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

        /*if (cell.getNeighbor(dx, dy).getType() == CellType.STAIRUP) {
            Main.setMap( MapLoader.loadMap("/map3.txt"));
        }*/

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
                && cell.getNeighbor(dx, dy).getType() != CellType.BLUE_CLOSED_DOOR
                && cell.getNeighbor(dx, dy).getType() != CellType.PLANT
                && cell.getNeighbor(dx, dy).getType() != CellType.CHAINH
                && cell.getNeighbor(dx, dy).getType() != CellType.CHAINV
                && cell.getNeighbor(dx, dy).getType() != CellType.TABLEBOTTOM
                && cell.getNeighbor(dx, dy).getType() != CellType.TABLEMIDDLE
                && cell.getNeighbor(dx, dy).getType() != CellType.TABLETOP
                && cell.getNeighbor(dx, dy).getType() != CellType.SINGLETABLE) {

            if (cell.getNeighbor(dx, dy).getActor() instanceof Monster) {
                attackToMonster((Monster) cell.getNeighbor(dx, dy).getActor(), dx, dy);

                if (cell.getNeighbor(dx, dy).getActor() != null)
                    attackToPlayer((Monster) cell.getNeighbor(dx, dy).getActor(), this);
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
    public int getAttack() { return attack;}

    public int getDefense() {
        return defense;
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

    public void healPlayer(int health) {
        this.health += health;
    }

    public abstract void handlePickedUpItem(Item item);

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

    private void attackToPlayer(Monster monster, Actor player) {
        if (player.isAlive) {

            if (monster.attack - player.defense < 1) player.health = player.health;

            else player.health = player.health - (monster.attack - player.defense);

            if (player.health <= 0) player.isAlive = false;
        }
        else System.out.println("Die!");
    }

    public boolean hasKey(String color){
        return false;
    }
}
