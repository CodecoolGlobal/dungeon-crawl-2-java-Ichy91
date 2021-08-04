package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    COMPANION("companion"),
    TOILET("toilet"),
    CARH1("carH1"),
    CARH2("carH2"),
    CARV1("carV1"),
    CARV2("carV2"),
    CHAINH("chainH"),
    CHAINV("chainV"),
    CHAIR("chair"),
    TABLETOP("tableTop"),
    TABLEMIDDLE("tableMiddle"),
    TABLEBOTTOM("tableBottom"),
    SINGLETABLE("singleTable"),
    PLANT("plant"),
    COLUMNTOP("colTop"),
    COLUMNMIDDLE("colMid"),
    COLUMNBOTTOM("colBot"),
    WINDOW("window"),
    STAIRUP("stairUp");
    GREEN_CLOSED_DOOR("green_closed_door"),
    BLUE_CLOSED_DOOR("blue_closed_door"),
    RED_CLOSED_DOOR("red_closed_door"),
    OPENED_DOOR("opened_door");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
