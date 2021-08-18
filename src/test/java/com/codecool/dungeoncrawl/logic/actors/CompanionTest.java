package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanionTest {
    GameMap gameMap = new GameMap(10, 10, CellType.FLOOR);

    @Test
    void followThePlayer() {
        Player player = new Player(gameMap.getCell(5, 5));
        Companion companion = new Companion(gameMap.getCell(5, 6));

        Cell playerCell = player.getCell();
        player.move(1, 0);
        companion.followPlayer(playerCell);

        assertEquals(5, companion.getX());
        assertEquals(5, companion.getY());
    }
}