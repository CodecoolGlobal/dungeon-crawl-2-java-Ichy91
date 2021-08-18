package com.codecool.dungeoncrawl.model;

import java.util.ArrayList;
import java.sql.Date;

public class GameState extends BaseModel {
    private Date savedAt;
    private PlayerModel player;
    private ArrayList<InventoryModel> inventory = new ArrayList<>();
    private String currentMap;
    private String name;
//    private List<String> discoveredMaps = new ArrayList<>();
    private Integer playerId;

    public GameState(String name, String currentMap, PlayerModel player) {
        this.name = name;
        this.currentMap = currentMap;
        this.player = player;
    }

    public GameState(String name, String currentMap, Integer playerId) {
        this.name = name;
        this.currentMap = currentMap;
        this.playerId = playerId;
    }

    public GameState(PlayerModel player, ArrayList<InventoryModel> inventory, String map) {
        this.savedAt = new Date(System.currentTimeMillis());
        this.player = player;
        this.inventory = inventory;
        this.currentMap = map;
    }


    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }

//    public List<String> getDiscoveredMaps() {
//        return discoveredMaps;
//    }
//
//    public void addDiscoveredMap(String map) {
//        this.discoveredMaps.add(map);
//    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
}
