package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Armor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    GameMap map = new GameMap(3, 3, CellType.FLOOR);

    @Test
    void getNeighbor() {
        Cell cell = map.getCell(1, 1);
        Cell neighbor = cell.getNeighbor(-1, 0);
        assertEquals(0, neighbor.getX());
        assertEquals(1, neighbor.getY());
    }

    @Test
    void cellOnEdgeHasNoNeighbor() {
        Cell cell = map.getCell(1, 0);
        assertNull(cell.getNeighbor(0, -1));

        cell = map.getCell(0, 2);
        assertNull(cell.getNeighbor(-1, 1));
    }

    @Test
    void cellGetCellTypeTest() {
        Cell cell = new Cell(new GameMap(100,100, CellType.EMPTY), 10, 11, CellType.WALL);
        assertEquals(CellType.WALL, cell.getType());
    }

    @Test
    void cellGetTileNameTest() {
        Cell cell = new Cell(new GameMap(100,100, CellType.EMPTY), 10, 11, CellType.WALL);
        assertEquals("wall", cell.getTileName());
    }

    @Test
    void cellGetCellNotActorTest() {
        Cell cell = new Cell(new GameMap(100,100, CellType.EMPTY), 10, 11, CellType.WALL);
        assertNull(cell.getActor());
    }

    @Test
    void cellGetCellNotItemTest() {
        Cell cell = new Cell(new GameMap(100,100, CellType.EMPTY), 10, 11, CellType.WALL);
        assertNull(cell.getItem());
    }

    @Test
    void cellSetCellToArmorTest() {
        Cell cell = new Cell(new GameMap(50,50, CellType.FLOOR), 5, 6, CellType.WALL);
        cell.setItem(new Armor(cell));
        assertEquals(cell.getItem().getTileName(), "armor");
    }

    @Test
    void cellSetTypeToEmptyTest() {
        Cell cell = new Cell(new GameMap(50,50, CellType.FLOOR), 5, 6, CellType.WALL);
        cell.setType(CellType.EMPTY);
        assertEquals(CellType.EMPTY, cell.getType());
    }

    @Test
    void cellSetCellToActorTest() {
        Cell cell = new Cell(new GameMap(50,50, CellType.FLOOR), 5, 6, CellType.WALL);
        cell.setActor(new Player(cell));
        assertEquals(cell.getActor().getTileName(), "player");
    }
}