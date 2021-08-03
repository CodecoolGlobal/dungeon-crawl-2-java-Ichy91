package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("companion", new Tile(31, 7));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("chainH", new Tile(12, 12));
        tileMap.put("chainV", new Tile(0, 4));
        tileMap.put("carH1", new Tile(11, 22));
        tileMap.put("carH2", new Tile(12, 23));
        tileMap.put("carV1", new Tile(14, 22));
        tileMap.put("carV2", new Tile(14, 23));
        tileMap.put("tableTop", new Tile(16, 8));
        tileMap.put("tableMiddle", new Tile(16, 9));
        tileMap.put("tableBottom", new Tile(16, 10));
        tileMap.put("singleTable", new Tile(2, 8));
        tileMap.put("chair", new Tile(1, 8));
        tileMap.put("plant", new Tile(19, 5));
        tileMap.put("colTop", new Tile(3, 11));
        tileMap.put("colMid", new Tile(3, 12));
        tileMap.put("colBot", new Tile(3, 13));
        tileMap.put("window", new Tile(1, 9));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
