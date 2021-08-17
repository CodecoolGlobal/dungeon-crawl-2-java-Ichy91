package com.codecool.dungeoncrawl.logic.actors.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Actor;

import java.util.Random;

public abstract class Monster extends Actor {
    protected Random random = new Random();
    public Monster(Cell cell) {
        super(cell);
    }

    public void monsterMove(Monster monster) {
        int x = 0;
        int y = 0;
        boolean choice = random.nextBoolean();

        if (choice) {
            while(x == 0) {
                x = random.nextInt(3) - 1;
            }
        }

        else {
            while(y == 0) {
                y = random.nextInt(3) - 1;
            }
        }

        if (cell.getNeighbor(x, y).getType() != CellType.WALL
                && cell.getNeighbor(x, y).getType() != CellType.RED_CLOSED_DOOR
                && cell.getNeighbor(x, y).getType() != CellType.GREEN_CLOSED_DOOR
                && cell.getNeighbor(x, y).getType() != CellType.BLUE_CLOSED_DOOR
                && cell.getNeighbor(x, y).getActor() == null)
            monster.acceptMove(x, y);
    }
    public char getChar() {return 'f';}

}
