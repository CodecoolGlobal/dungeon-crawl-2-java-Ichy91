package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Companion;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.monsters.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.HealingPotion;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

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
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            map.setCompanion(new Companion(cell));
                            break;
                        case 'S':
                            cell.setType(CellType.FLOOR);
                            map.setSword(new Sword(cell));
                            break;
                        case 'A':
                            cell.setType(CellType.FLOOR);
                            map.setArmor(new Armor(cell));
                            break;
                        case 'H':
                            cell.setType(CellType.FLOOR);
                            map.setHealingPotion(new HealingPotion(cell));
                            break;
                        case 'K':
                            cell.setType(CellType.FLOOR);
                            map.setKey(new Key(cell));
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
