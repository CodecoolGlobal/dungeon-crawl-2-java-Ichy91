package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Companion;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {

    @Test
    void gameMapGettersTest() {
        GameMap gameMap = new GameMap(50, 50, CellType.FLOOR);
        assertNull(gameMap.getPlayer());
        assertNull(gameMap.getCompanion());
        assertEquals(50, gameMap.getWidth());
        assertEquals(50, gameMap.getHeight());
        assertEquals(new ArrayList<>(), gameMap.getMonsters());
    }

    @Test
    void gameMapSettersTest() {
        GameMap gameMap = new GameMap(100, 100, CellType.EMPTY);
        gameMap.setKey(new Key(gameMap.getCell(50, 50)));
        assertEquals("key" ,gameMap.getCell(50, 50).getItem().getTileName());
        gameMap.setArmor(new Armor(gameMap.getCell(50, 50)));
        assertEquals("armor" ,gameMap.getCell(50, 50).getItem().getTileName());
        gameMap.setCompanion(new Companion(gameMap.getCell(50, 50)));
        assertEquals("companion" ,gameMap.getCell(50, 50).getActor().getTileName());
        gameMap.setPlayer(new Player(gameMap.getCell(50, 50)));
        assertEquals("player" ,gameMap.getCell(50, 50).getActor().getTileName());
        gameMap.setHelmet(new Helmet(gameMap.getCell(50, 50)));
        assertEquals("helmet" ,gameMap.getCell(50, 50).getItem().getTileName());
        gameMap.setHealingPotion(new HealingPotion(gameMap.getCell(50, 50)));
        assertEquals("healingPotion" ,gameMap.getCell(50, 50).getItem().getTileName());
        gameMap.setSpear(new Spear(gameMap.getCell(50, 50)));
        assertEquals("spear" ,gameMap.getCell(50, 50).getItem().getTileName());
        gameMap.setSword(new Sword(gameMap.getCell(50, 50)));
        assertEquals("sword" ,gameMap.getCell(50, 50).getItem().getTileName());
        gameMap.setArmor(new Armor(gameMap.getCell(50, 50)));
        assertEquals("armor" ,gameMap.getCell(50, 50).getItem().getTileName());
        gameMap.setPlayerName("CodeCool");
        assertEquals("CodeCool", gameMap.getPlayer().getPlayerName());
    }

    @Test
    void setPlayerNameToEmptyStringThrowsException() {
        GameMap gameMap = new GameMap(1, 1, CellType.EMPTY);
        assertThrows(IllegalArgumentException.class, () ->  gameMap.setPlayerName(""));
    }

}