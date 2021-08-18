package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int hp;

    public PlayerModel(String playerName) {
        this.playerName = playerName;
    }

    public PlayerModel(Player player) {
        this.playerName = player.getPlayerName();
        this.hp = player.getHealth();

    }

    public PlayerModel(String playerName, int hp) {
        this.playerName = playerName;
        this.hp = hp;

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Player getAFuckingPlayer(Cell cell) {
        return new Player(cell, this.hp);
    }
}
