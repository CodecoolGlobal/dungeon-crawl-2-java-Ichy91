package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {
    private final DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (name, current_map, saved_at, player_id) VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getName());
            statement.setString(2, state.getCurrentMap());
            statement.setInt(3, state.getPlayer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameState state) {
        try (Connection conn = dataSource.getConnection()){
            String sql = "UPDATE game_state SET current_map = ?, saved_at = CURRENT_TIMESTAMP  WHERE player_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setInt(2, state.getPlayer().getId());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameState get(String name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM game_state WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) return null; // first row was not found == no data was returned by the query

            GameState gameState = new GameState(rs.getString(2), rs.getString(3), rs.getInt(5));
            gameState.setPlayerId(rs.getInt(5));
            return gameState;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GameState> getAll() {
        return null;
    }
}
