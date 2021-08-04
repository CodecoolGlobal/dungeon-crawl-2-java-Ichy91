package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.items.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    private GameMap map = MapLoader.loadMap("/map2.txt");
    private Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    private GraphicsContext context = canvas.getGraphicsContext2D();
    private Label healthLabel = new Label();
    private Label attackLabel = new Label();
    private Label defenseLabel = new Label();
    private final SplitMenuButton splitMenuButtonWeapon = new SplitMenuButton();
    private final SplitMenuButton splitMenuButtonDefense = new SplitMenuButton();
    private Button pickUpButton = new Button();

    private Label inventoryLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SimpleAudioPlayer.playMusic();
        splitMenuButtonWeapon.setText("Weapons");
        splitMenuButtonDefense.setText("Defense");
        splitMenuButtonWeapon.setOnAction((e) -> {
            System.out.println("SplitMenuButtonAttack clicked!");
        });
        splitMenuButtonDefense.setOnAction((e) -> {
            System.out.println("SplitMenuButtonDefense clicked!");
        });
        splitMenuButtonWeapon.setFocusTraversable(false);
        splitMenuButtonDefense.setFocusTraversable(false);
        splitMenuButtonDefense.setStyle("-fx-font-size: 15px; -fx-background-color: #0000ff; -fx-min-width: 140");
        splitMenuButtonWeapon.setStyle("-fx-font-size: 15px; -fx-background-color: #0000ff; -fx-min-width: 140");


        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(new Label("Attack: "), 0, 1);
        ui.add(new Label("Defense: "), 0, 2);
        ui.add(healthLabel, 1, 0);
        ui.add(attackLabel, 1, 1);
        ui.add(defenseLabel, 1, 2);
        ui.add(splitMenuButtonWeapon, 0, 6);
        ui.add(splitMenuButtonDefense, 0, 8);
        pickUpButton.setText("pick up");
        ui.add(pickUpButton, 0, 4);
        ui.add(inventoryLabel, 0, 9);
        pickUpButton.setDisable(true);
        pickUpButton.setFocusTraversable(false);
        pickUpButton.setStyle("-fx-font-size: 15px; -fx-background-color: #d9d9d9; -fx-border-width: 1px; -fx-border-color: #0000ff; -fx-min-width: 140");

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();

        pickUpButton.setOnAction(event -> {
            map.getPlayer().getCell().getItem().addToInventory();
            pickUpButton.setDisable(true);
            refresh();
        });
    }


    private void onKeyPressed(KeyEvent keyEvent) {
        Cell playerCell = map.getPlayer().getCell();
        pickUpButton.setDisable(true);
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                map.getCompanion().followPlayer(playerCell);
                map.getPlayer().move(0, 0);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                map.getCompanion().followPlayer(playerCell);
                map.getPlayer().move(0, 0);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                map.getCompanion().followPlayer(playerCell);
                map.getPlayer().move(0, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                map.getCompanion().followPlayer(playerCell);
                map.getPlayer().move(0, 0);
                refresh();
                break;
            case SPACE:
                if (map.getPlayer().isStandingOnItem()){
                    map.getPlayer().getCell().getItem().addToInventory();
                    refresh();
                }
                break;
            case X:
                if(map.getPlayer().getCell().getType()== CellType.STAIRUP){
                map = MapLoader.loadMap("/map3.txt");
                refresh();
                } //TODO dynamic map change
            case W:
                for (Item item : map.getPlayer().getInventory()){
                    if (item instanceof Sword) {
                        map.getPlayer().equipItem(item);
                        refresh();
                        break;
                    }
                }
                break;
            case E:
                for (Item item : map.getPlayer().getInventory()){
                    if (item instanceof Spear) {
                        map.getPlayer().equipItem(item);
                        refresh();
                        break;
                    }
                }
                break;
            case R:
                for (Item item : map.getPlayer().getInventory()){
                    if (item instanceof Armor) {
                        map.getPlayer().equipItem(item);
                        refresh();
                        break;
                    }
                }
                break;

            case T:
                for (Item item : map.getPlayer().getInventory()){
                    if (item instanceof Helmet) {
                        map.getPlayer().equipItem(item);
                        refresh();
                        break;
                    }
                }
                break;

        }
        if (map.getPlayer().isStandingOnItem()){
            pickUpButton.setDisable(false);
        }

    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Cell cell = map.getCell(map.getPlayer().getX()-5 + x, map.getPlayer().getY()-5 +y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                }

                else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                }

                else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        attackLabel.setText("" + map.getPlayer().getAttack());
        defenseLabel.setText("" + map.getPlayer().getDefense());
        addItemsIntoInventoryList();
        inventoryLabel.setText(map.getPlayer().getKeys());
    }

    private void addItemsIntoInventoryList() {
        ArrayList<Item> inventory = map.getPlayer().getInventory();
        addAttackItems(inventory);
        addDefenseItems(inventory);
    }

    private void addAttackItems(ArrayList<Item> inventory) {
        splitMenuButtonWeapon.getItems().clear();
        MenuItem menuItem;
        for (Item item : inventory) {
            if (item.getAttack() > 0) {
                menuItem = new MenuItem(item.getTileName());
                menuItem.setOnAction((e)-> {
                    map.getPlayer().equipItem(item);
                    refresh();
                    System.out.println(item.getTileName() + " selected");
                });
                splitMenuButtonWeapon.getItems().addAll(menuItem);
            }
        }
    }


    private void addDefenseItems(ArrayList<Item> inventory) {
        splitMenuButtonDefense.getItems().clear();
        MenuItem menuItem;
        for (Item item : inventory) {
            if (item.getDefense() > 0) {
                menuItem = new MenuItem(item.getTileName());
                menuItem.setOnAction((e)-> {
                    map.getPlayer().equipItem(item);
                    refresh();
                    System.out.println(item.getTileName() + " selected");
                });
                splitMenuButtonDefense.getItems().addAll(menuItem);
            }
        }
    }
}
