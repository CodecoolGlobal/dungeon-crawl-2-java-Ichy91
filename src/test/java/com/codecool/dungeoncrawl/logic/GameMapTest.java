package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Companion;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {

    @Test
    void gameMapGetGameMapNoPlayerTest() {
        GameMap gameMap = new GameMap(50, 50, CellType.FLOOR);
        assertNull(gameMap.getPlayer());
    }

    @Test
    void gameMapGetGameMapNoCompanionTest() {
        GameMap gameMap = new GameMap(50, 50, CellType.FLOOR);
        assertNull(gameMap.getCompanion());
    }

    @Test
    void gameMapGetGameMapWidthAndHeightTest() {
        GameMap gameMap = new GameMap(50, 50, CellType.FLOOR);
        assertEquals(50, gameMap.getWidth());
        assertEquals(50, gameMap.getHeight());
    }

    @Test
    void gameMapGetGameMapNoMonstersTest() {
        GameMap gameMap = new GameMap(50, 50, CellType.FLOOR);
        assertEquals(new ArrayList<>(), gameMap.getMonsters());
    }

    @Test
    void gameMapSetCellToKeyTest() {
        GameMap gameMap = new GameMap(100, 100, CellType.EMPTY);
        gameMap.setKey(new Key(gameMap.getCell(50, 50)));
        assertEquals("key" ,gameMap.getCell(50, 50).getItem().getTileName());

    }

    @Test
    void gameMapSetPlayerNameTest() {
        GameMap gameMap = new GameMap(100, 100, CellType.EMPTY);
        gameMap.setPlayer(new Player(gameMap.getCell(50, 50)));
        gameMap.setPlayerName("CodeCool");
        assertEquals("CodeCool", gameMap.getPlayer().getPlayerName());
    }

    @Test
    void gameMapSetCellToSwordTest() {
        GameMap gameMap = new GameMap(100, 100, CellType.EMPTY);
        gameMap.setSword(new Sword(gameMap.getCell(50, 50)));
        assertEquals("sword" ,gameMap.getCell(50, 50).getItem().getTileName());
    }

    @Test
    void gameMapSetCellToSpearTest() {
        GameMap gameMap = new GameMap(100, 100, CellType.EMPTY);
        gameMap.setSpear(new Spear(gameMap.getCell(50, 50)));
        assertEquals("spear" ,gameMap.getCell(50, 50).getItem().getTileName());
    }

    @Test
    void gameMapSetCellToHealingPotionTest() {
        GameMap gameMap = new GameMap(100, 100, CellType.EMPTY);
        gameMap.setHealingPotion(new HealingPotion(gameMap.getCell(50, 50)));
        assertEquals("healingPotion" ,gameMap.getCell(50, 50).getItem().getTileName());
    }

    @Test
    void gameMapSetCellToHelmetTest() {
        GameMap gameMap = new GameMap(100, 100, CellType.EMPTY);
        gameMap.setHelmet(new Helmet(gameMap.getCell(50, 50)));
        assertEquals("helmet" ,gameMap.getCell(50, 50).getItem().getTileName());
    }

    @Test
    void gameMapSetCellToPlayerTest() {
        GameMap gameMap = new GameMap(100, 100, CellType.EMPTY);
        gameMap.setPlayer(new Player(gameMap.getCell(50, 50)));
        assertEquals("player" ,gameMap.getCell(50, 50).getActor().getTileName());
    }

    @Test
    void gameMapSetCellToCompanionTest() {
        GameMap gameMap = new GameMap(100, 100, CellType.EMPTY);
        gameMap.setCompanion(new Companion(gameMap.getCell(50, 50)));
        assertEquals("companion" ,gameMap.getCell(50, 50).getActor().getTileName());
    }

    @Test
    void gameMapSetCellToArmorTest() {
        GameMap gameMap = new GameMap(100, 100, CellType.EMPTY);
        gameMap.setArmor(new Armor(gameMap.getCell(50, 50)));
        assertEquals("armor" ,gameMap.getCell(50, 50).getItem().getTileName());
    }

    @Test
    void setPlayerNameToEmptyStringThrowsException() {
        GameMap gameMap = new GameMap(1, 1, CellType.EMPTY);
        assertThrows(IllegalArgumentException.class, () ->  gameMap.setPlayerName(""));
    }

}