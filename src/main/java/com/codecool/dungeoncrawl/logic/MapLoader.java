package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Companion;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.monsters.GreenFox;
import com.codecool.dungeoncrawl.logic.actors.monsters.Guard;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.logic.actors.monsters.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.HealingPotion;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Keys.BlueKey;
import com.codecool.dungeoncrawl.logic.items.Keys.GreenKey;
import com.codecool.dungeoncrawl.logic.items.Keys.RedKey;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {

    public static GameMap loadMap(String level) throws IllegalArgumentException {

        if (level == "") {
            throw new IllegalArgumentException("No map found!");
        }

        InputStream is = MapLoader.class.getResourceAsStream(level);

        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int actualIndexOfMonster = 0;

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.getMonsters().add(new Skeleton(cell));
                            break;
                        case 'f':
                            cell.setType(CellType.FLOOR);
                            map.getMonsters().add(new GreenFox(cell));
                            break;
                        case 'ö':
                            cell.setType(CellType.FLOOR);
                            map.getMonsters().add(new Guard(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            map.setCompanion(new Companion(cell));
                            break;
                        case 'L':
                            cell.setType(CellType.CHAINH);
                            break;
                        case 'l':
                            cell.setType(CellType.CHAINV);
                            break;
                        case 'y':
                            cell.setType(CellType.CARH1);
                            break;
                        case 'Y':
                            cell.setType(CellType.CARH2);
                            break;
                        case 'x':
                            cell.setType(CellType.CARV1);
                            break;
                        case 'X':
                            cell.setType(CellType.CARV2);
                            break;
                        case 'C':
                            cell.setType(CellType.TABLETOP);
                            break;
                        case 'V':
                            cell.setType(CellType.TABLEMIDDLE);
                            break;
                        case 'B':
                            cell.setType(CellType.TABLEBOTTOM);
                            break;
                        case 'N':
                            cell.setType(CellType.SINGLETABLE);
                            break;
                        case 'M':
                            cell.setType(CellType.CHAIR);
                            break;
                        case 'Á':
                            cell.setType(CellType.PLANT);
                            break;
                        case 'J':
                            cell.setType(CellType.COLUMNTOP);
                            break;
                        case 'K':
                            cell.setType(CellType.COLUMNMIDDLE);
                            break;
                        case 'H':
                            cell.setType(CellType.COLUMNBOTTOM);
                            break;
                        case 'É':
                            cell.setType(CellType.WINDOW);
                            break;
                        case 'Ű':
                            cell.setType(CellType.TOILET);
                            break;
                        case 'Ő':
                            cell.setType(CellType.STAIRUP);
                            break;
                        case 'S':
                            cell.setType(CellType.FLOOR);
                            map.setSword(new Sword(cell));
                            break;
                        case 'P':
                            cell.setType(CellType.FLOOR);
                            map.setSpear(new Spear(cell));
                            break;
                        case 'A':
                            cell.setType(CellType.FLOOR);
                            map.setArmor(new Armor(cell));
                            break;
                        case 'E':
                            cell.setType(CellType.FLOOR);
                            map.setHelmet(new Helmet(cell));
                            break;
                        case 'h':
                            cell.setType(CellType.FLOOR);
                            map.setHealingPotion(new HealingPotion(cell));
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            map.setKey(new BlueKey(cell));
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            map.setKey(new RedKey(cell));
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            map.setKey(new GreenKey(cell));
                            break;
                        case 'G':
                            cell.setType(CellType.GREEN_CLOSED_DOOR);
                            break;
                        case 'Z':
                            cell.setType(CellType.BLUE_CLOSED_DOOR);
                            break;
                        case 'R':
                            cell.setType(CellType.RED_CLOSED_DOOR);
                            break;
                        case 'O':
                            cell.setType(CellType.OPENED_DOOR);
                            break;
                        case '7':
                            cell.setType(CellType.ROOFLEFT);
                            break;
                        case '8':
                            cell.setType(CellType.ROOFTOP);
                            break;
                        case '9':
                            cell.setType(CellType.ROOFRIGHT);
                            break;
                        case '6':
                            cell.setType(CellType.ROOF);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
