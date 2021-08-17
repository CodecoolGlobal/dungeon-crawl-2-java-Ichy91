package com.codecool.dungeoncrawl.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapLoaderTest {

    @Test
    void loadMapTest() {
        assertEquals("floor", MapLoader.loadMap("/test.txt").getCell(3, 3).getTileName());
    }

}