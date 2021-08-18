package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;
    private InventoryDao inventoryDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
        inventoryDao = new InventoryDaoJdbc(dataSource);
    }

    public void saveGame(String nameOfSave, String map, PlayerModel player) {
        GameState model = new GameState(nameOfSave, map, player);
        gameStateDao.add(model);
    }

    public PlayerModel savePlayer(Player player) {
        PlayerModel model = new PlayerModel(player);
        playerDao.add(model);
        return model;
    }

    public void saveInventory(PlayerModel player, ArrayList<Item> inventory, ArrayList<String> equippedItems) {
        for (Item item : inventory) {
            String itemName = item.getTileName();
            InventoryModel model = new InventoryModel(player, itemName, equippedItems.contains(itemName));
            inventoryDao.add(model);
        }
    }

    public void updateGame(String nameOfSave, String map, PlayerModel player) {
        GameState model = new GameState(nameOfSave, map, player);
        gameStateDao.update(model);
    }

    public PlayerModel updatePlayer(Player player, String nameOfSaving) {
        Integer playerId = gameStateDao.get(nameOfSaving).getPlayerId();
        PlayerModel model = new PlayerModel(player);
        model.setId(playerId);

        playerDao.update(model);
        return model;
    }

    public void updateInventory(PlayerModel player, ArrayList<Item> inventory, ArrayList<String> equippedItems) {
        for (Item item : inventory) {
            String itemName = item.getTileName();
            InventoryModel model = new InventoryModel(player, itemName, equippedItems.contains(itemName));
            inventoryDao.update(model);
        }
    }

    public boolean isNameAlreadyInDB(String nameOfSaving) {
        return gameStateDao.get(nameOfSaving) != null;
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String user = System.getenv("PSQL_USER_NAME");
        String password = System.getenv("PSQL_PASSWORD");
        String dbName = System.getenv("PSQL_DB_NAME");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
