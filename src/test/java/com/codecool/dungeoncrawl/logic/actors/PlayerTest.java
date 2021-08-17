package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.logic.items.Keys.BlueKey;
import com.codecool.dungeoncrawl.logic.items.Keys.GreenKey;
import com.codecool.dungeoncrawl.logic.items.Keys.RedKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    GameMap gameMap = new GameMap(10, 10, CellType.FLOOR);
    Player player;

    @BeforeEach
    void setup() {
        player = new Player(gameMap.getCell(5, 5));
    }

    @Test
    void pickupItem () {
        Armor armor = new Armor(gameMap.getCell(6, 5));
        player.move(1, 0);

        assertTrue(player.isStandingOnItem());
        assertEquals(6, armor.getCell().getX());
        assertEquals(5, armor.getCell().getY());

        armor.addToInventory();

        assertEquals("armor", player.getInventory().get(0).getTileName());
    }

    @Test
    void pickupExistItemInTheInventory() {
        Helmet helmet = new Helmet(gameMap.getCell(6, 6));
        player.handlePickedUpItem(helmet);
        player.handlePickedUpItem(helmet);

        assertEquals(1, player.getInventory().size());
    }

    @Test
    void pickupHealingPotion() {
        HealingPotion healingPotion = new HealingPotion(gameMap.getCell(6, 6));
        player.handlePickedUpItem(healingPotion);

        assertEquals(30, player.getHealth());
    }

    @Test
    void equipItemWhenIsNotEquipped() {
        Spear spear = new Spear(gameMap.getCell(6, 6));
        player.handlePickedUpItem(spear);
        player.equipItem(spear);
        player.fillUpEquippedItems();

        assertTrue(spear.isEquiped());
        assertEquals("playerWithSpear", player.getTileName());
    }

    @Test
    void removeEquippedItem() {
        Sword sword = new Sword(gameMap.getCell(6, 6));
        player.equipItem(sword);
        player.equipItem(sword);

        assertFalse(sword.isEquiped());
    }

    @Test
    void fullEquippedPlayer() {
        Sword sword = new Sword(gameMap.getCell(2, 2));
        Spear spear = new Spear(gameMap.getCell(6, 6));
        Armor armor = new Armor(gameMap.getCell(3, 3));
        Helmet helmet = new Helmet(gameMap.getCell(4, 4));

        player.handlePickedUpItem(sword);
        player.handlePickedUpItem(spear);
        player.handlePickedUpItem(armor);
        player.handlePickedUpItem(helmet);

        player.equipItem(sword);
        player.equipItem(armor);
        player.equipItem(helmet);

        assertEquals("playerWithSwordAndHelmetAndArmor", player.getTileName());
        assertEquals(15, player.getAttack());

        player.equipItem(spear);

        assertEquals("playerWithSpearAndHelmetAndArmor", player.getTileName());
        assertEquals(15, player.getDefense());
    }

    @Test
    void playerPickupAllKeys() {
        BlueKey blueKey = new BlueKey(gameMap.getCell(2, 2));
        GreenKey greenKey = new GreenKey(gameMap.getCell(3, 3));
        RedKey redKey = new RedKey(gameMap.getCell(4, 4));

        player.handlePickedUpItem(blueKey);
        player.handlePickedUpItem(greenKey);
        player.handlePickedUpItem(redKey);

        assertEquals("[BLUE][GREEN][RED]", player.getKeys());
    }

    @Test
    void playerDontHaveKey() {
        assertFalse(player.hasKey("Blue"));
        assertFalse(player.hasKey("Green"));
        assertFalse(player.hasKey("Red"));
    }

    @Test
    void checkIfPlayerHaveKeys() {
        BlueKey blueKey = new BlueKey(gameMap.getCell(2, 2));
        GreenKey greenKey = new GreenKey(gameMap.getCell(3, 3));
        RedKey redKey = new RedKey(gameMap.getCell(4, 4));

        player.handlePickedUpItem(blueKey);
        player.handlePickedUpItem(greenKey);
        player.handlePickedUpItem(redKey);

        assertTrue(player.hasKey("Blue"));
        assertTrue(player.hasKey("Green"));
        assertTrue(player.hasKey("Red"));
    }

    @Test
    void playerOpenDoors() {
        gameMap.getCell(6, 5).setType(CellType.BLUE_CLOSED_DOOR);
        gameMap.getCell(7, 5).setType(CellType.GREEN_CLOSED_DOOR);
        gameMap.getCell(8, 5).setType(CellType.RED_CLOSED_DOOR);

        BlueKey blueKey = new BlueKey(gameMap.getCell(2, 2));
        GreenKey greenKey = new GreenKey(gameMap.getCell(3, 3));
        RedKey redKey = new RedKey(gameMap.getCell(4, 4));

        player.handlePickedUpItem(blueKey);
        player.handlePickedUpItem(greenKey);
        player.handlePickedUpItem(redKey);

        player.move(1,0);
        assertEquals("OPENED_DOOR", player.getCell().getType().toString());

        player.move(1,0);
        assertEquals("OPENED_DOOR", player.getCell().getType().toString());

        player.move(1,0);
        assertEquals("OPENED_DOOR", player.getCell().getType().toString());
    }
}