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
    WINDOW("window");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
