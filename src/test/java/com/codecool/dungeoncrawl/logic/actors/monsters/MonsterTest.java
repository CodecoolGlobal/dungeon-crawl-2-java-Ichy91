package com.codecool.dungeoncrawl.logic.actors.monsters;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {
    GameMap gameMap = new GameMap(10, 10, CellType.FLOOR);

    @Test
    void checkGreenFoxMove() {
        GreenFox greenFox = new GreenFox(gameMap.getCell(7, 7));
        greenFox.monsterMove(greenFox);

        if (greenFox.getX() == 7)
            assertTrue(7 != greenFox.getY());
        else
            assertTrue(7 != greenFox.getX());
    }

    @Test
    void checkSkeletonMove() {
        Skeleton skeleton = new Skeleton(gameMap.getCell(5, 5));
        skeleton.monsterMove(skeleton);

        if (skeleton.getX() == 5)
            assertTrue(5 != skeleton.getY());
        else
            assertTrue(5 != skeleton.getX());
    }
    @Test
    void checkGuardMove() {
        Guard guard = new Guard(gameMap.getCell(3, 3));
        guard.monsterMove(guard);

        assertEquals(3, guard.getX());
        assertEquals(3, guard.getY());
    }

    @Test
    void monsterDontHaveKey() {
        Skeleton skeleton = new Skeleton(gameMap.getCell(5, 5));
        assertFalse(skeleton.hasKey("Red"));
    }

    @Test
    void checkMonsterTileName() {
        Skeleton skeleton = new Skeleton(gameMap.getCell(5, 5));
        GreenFox greenFox = new GreenFox(gameMap.getCell(7, 7));
        Guard guard = new Guard(gameMap.getCell(3, 3));

        assertEquals("skeleton", skeleton.getTileName());
        assertEquals("greenFox", greenFox.getTileName());
        assertEquals("guard", guard.getTileName());
    }
}