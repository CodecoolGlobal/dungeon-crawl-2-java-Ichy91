package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty", ' '),
    FLOOR("floor", '.'),
    WALL("wall", '#'),
    COMPANION("companion", 'c'),
    TOILET("toilet", 'Ű'),
    CARH1("carH1", 'y'),
    CARH2("carH2", 'Y'),
    CARV1("carV1", 'x'),
    CARV2("carV2", 'X'),
    CHAINH("chainH", 'L'),
    CHAINV("chainV", 'l'),
    CHAIR("chair", 'M'),
    TABLETOP("tableTop", 'C'),
    TABLEMIDDLE("tableMiddle", 'V'),
    TABLEBOTTOM("tableBottom", 'B'),
    SINGLETABLE("singleTable", 'N'),
    PLANT("plant", 'Á'),
    COLUMNTOP("colTop", 'J'),
    COLUMNMIDDLE("colMid", 'K'),
    COLUMNBOTTOM("colBot", 'H'),
    WINDOW("window", 'É'),
    STAIRUP("stairUp", 'Ő'),
    GREEN_CLOSED_DOOR("green_closed_door", 'G'),
    BLUE_CLOSED_DOOR("blue_closed_door", 'Z'),
    RED_CLOSED_DOOR("red_closed_door", 'R'),
    OPENED_DOOR("opened_door", 'O'),
    ROOFRIGHT("roofRight", '9'),
    ROOFLEFT("roofLeft", '7'),
    ROOF("roof", '6'),
    ROOFTOP("roofTop", '8');

    private final String tileName;
    private final char C;

    CellType(String tileName, char C) {
        this.tileName = tileName;
        this.C = C;
    }

    public String getTileName() {
        return tileName;
    }

    public char getChar() {
        return C;
    }
}
