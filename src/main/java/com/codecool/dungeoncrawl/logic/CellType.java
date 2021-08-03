package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    GREEN_CLOSED_DOOR("green_closed_door"),
    WHITE_CLOSED_DOOR("blue_closed_door"),
    RED_CLOSED_DOOR("red_closed_door"),
    OPENED_DOOR("red_opened_door");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
