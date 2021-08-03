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
        tileMap.put("companion", new Tile(31, 7));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("sword", new Tile(1, 31));
        tileMap.put("armor", new Tile(3, 23));
        tileMap.put("healingPotion", new Tile(23, 22));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("red-key", new Tile(18, 23));
        tileMap.put("blue-key", new Tile(17, 23));
        tileMap.put("green-key", new Tile(17, 30));
        tileMap.put("blue_closed_door", new Tile(31, 25));
        tileMap.put("red_closed_door", new Tile(29, 25));
        tileMap.put("green_closed_door", new Tile(30, 25));
        tileMap.put("opened_door", new Tile(27, 25));


    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
