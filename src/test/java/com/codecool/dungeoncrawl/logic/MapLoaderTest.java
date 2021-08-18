package com.codecool.dungeoncrawl.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapLoaderTest {

    @Test
    void loadMapTest() {
        assertEquals("roofRight", MapLoader.loadMap("/test2.txt").getCell(3, 3).getTileName());
    }

    @Test
    void loadMapWithoutMapFileThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> MapLoader.loadMap(""));
    }

    @Test
    void loadMapWithWrongCharacterInTxtFileTest() {
        assertThrows(RuntimeException.class, () -> MapLoader.loadMap("/test.txt"));
    }

}