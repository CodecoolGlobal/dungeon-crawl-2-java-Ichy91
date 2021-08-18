package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDaoJdbc implements InventoryDao {
    private final DataSource dataSource;

    public InventoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(InventoryModel inventory) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO inventory (player_id, type, equipped) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, inventory.getPlayer().getId());
            statement.setString(2, inventory.getType());
            statement.setBoolean(3, inventory.isEquipped());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            inventory.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeItemsWithGivenPlayerId(Integer playerId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM inventory WHERE player_id = ?;";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, playerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<InventoryModel> get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT type, equipped FROM inventory WHERE player_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            List<InventoryModel> inventory = new ArrayList<>();

            while (rs.next()){
                inventory.add(new InventoryModel(rs.getString(1), rs.getBoolean(2)));
            }

            return inventory;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    @Override
    public List<InventoryModel> getAll() {
        return null;
    }
}
