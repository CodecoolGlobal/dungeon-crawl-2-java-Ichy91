package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Companion;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map3.txt");
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
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
