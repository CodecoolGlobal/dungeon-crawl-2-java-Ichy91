package com.codecool.dungeoncrawl.model;

public class InventoryModel extends BaseModel{
    private PlayerModel player;
    private String type;
    private boolean equipped;

    public InventoryModel(PlayerModel player, String type, boolean equipped) {
        this.player = player;
        this.type = type;
        this.equipped = equipped;
    }

    public InventoryModel(String type, boolean equipped) {
        this.type = type;
        this.equipped = equipped;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }
}
