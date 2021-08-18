package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryModel;

import java.util.List;

public interface InventoryDao {
    void add(InventoryModel inventory);
    List<InventoryModel> get(int id);
    void removeItemsWithGivenPlayerId(Integer playerId);
    List<InventoryModel> getAll();
}
