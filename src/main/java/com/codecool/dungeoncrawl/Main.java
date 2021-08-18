package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.monsters.GreenFox;
import com.codecool.dungeoncrawl.logic.actors.monsters.Monster;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class Main extends Application {
    private GameMap map = MapLoader.loadMap("/map2.txt");
    private Player player = map.getPlayer();
    private Canvas canvas = new Canvas(
            25 * Tiles.TILE_WIDTH,
            25 * Tiles.TILE_WIDTH);
    private GraphicsContext context = canvas.getGraphicsContext2D();
    private Label healthLabel = new Label();
    private Label attackLabel = new Label();
    private Label defenseLabel = new Label();
    private final SplitMenuButton splitMenuButtonWeapon = new SplitMenuButton();
    private final SplitMenuButton splitMenuButtonDefense = new SplitMenuButton();
    private Button pickUpButton = new Button();
    private Button exportGameButton = new Button();
    private Label inventoryLabel = new Label();
    private String name = "";
    private ArrayList<String> developersName = new ArrayList<>(Arrays.asList("isti", "saz", "mate", "martin"));
    private ArrayList<Item> inventory = new ArrayList<>();
    private GameDatabaseManager dbManager;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setupDbManager();
        getNameFromUserWithWelcomeScene(primaryStage);
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        KeyCombination saveCombinationMac = new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN);
        KeyCombination saveCombinationWin = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        if (exitCombinationMac.match(keyEvent) ||
                exitCombinationWin.match(keyEvent) ||
                keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        } else if (saveCombinationMac.match(keyEvent) ||
                saveCombinationWin.match(keyEvent)) {
                saveAction();
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        Cell playerCell = map.getPlayer().getCell();
        pickUpButton.setDisable(true);
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                map.getCompanion().followPlayer(playerCell);
                map.getPlayer().move(0, 0);
                monsterMover();
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                map.getCompanion().followPlayer(playerCell);
                map.getPlayer().move(0, 0);
                monsterMover();
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                map.getCompanion().followPlayer(playerCell);
                map.getPlayer().move(0, 0);
                monsterMover();
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                map.getCompanion().followPlayer(playerCell);
                map.getPlayer().move(0, 0);
                monsterMover();
                refresh();
                break;
            case SPACE:
                if (map.getPlayer().isStandingOnItem()) {
                    map.getPlayer().getCell().getItem().addToInventory();
                    addItemsIntoInventoryList();
                    refresh();
                }
                break;
            case X:
                if (map.getPlayer().getCell().getType() == CellType.STAIRUP) {
                    int health = player.getHealth();
                    inventory = player.getInventory();
                    map = MapLoader.loadMap("/map3.txt");
                    if (developersName.contains(name)) map.getPlayer().setHealth(99);
                    player = map.getPlayer();
                    player.setPlayerName(name);
                    player.setHealth(health);
                    player.setInventory(inventory);
                    player.fillUpEquippedItems();
                }
                refresh();
                break;
            case W:
                for (Item item : map.getPlayer().getInventory()) {
                    if (item instanceof Sword) {
                        map.getPlayer().equipItem(item);
                        refresh();
                        break;
                    }
                }
                break;
            case E:
                for (Item item : map.getPlayer().getInventory()) {
                    if (item instanceof Spear) {
                        map.getPlayer().equipItem(item);
                        refresh();
                        break;
                    }
                }
                break;
            case R:
                for (Item item : map.getPlayer().getInventory()) {
                    if (item instanceof Armor) {
                        map.getPlayer().equipItem(item);
                        refresh();
                        break;
                    }
                }
                break;

            case T:
                for (Item item : map.getPlayer().getInventory()) {
                    if (item instanceof Helmet) {
                        map.getPlayer().equipItem(item);
                        refresh();
                        break;
                    }
                }
                break;
        }
        if (map.getPlayer().isStandingOnItem()) {
            pickUpButton.setDisable(false);
        }

    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < 25; x++) {
            for (int y = 0; y < 25; y++) {
                Cell cell = map.getCell(map.getPlayer().getX() - 13 + x, map.getPlayer().getY() - 13 + y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        attackLabel.setText("" + map.getPlayer().getAttack());
        defenseLabel.setText("" + map.getPlayer().getDefense());
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
                menuItem.setOnAction((e) -> {
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
                menuItem.setOnAction((e) -> {
                    map.getPlayer().equipItem(item);
                    refresh();
                    System.out.println(item.getTileName() + " selected");
                });
                splitMenuButtonDefense.getItems().addAll(menuItem);
            }
        }
    }

    private void monsterMover() {
        for (Monster monster : map.getMonsters()) {
            if (monster.getHealth() <= 0) {
                map.getMonsters().remove(monster);
                break;
            } else {
                monster.monsterMove(monster);
                if (monster instanceof GreenFox) monster.monsterMove(monster);

            }
        }
    }

    private void getNameFromUserWithWelcomeScene(Stage primaryStage) {
        Pane pane = new Pane();
        String IDLE_BUTTON_STYLE = "-fx-font-size: 15px; -fx-background-color:#B53737, -fx-shadow-highlight-color, -fx-outer-border, -fx-body-color;";
        String HOVERED_BUTTON_STYLE = "-fx-font-size: 15px; -fx-background-color:#B53737, -fx-shadow-highlight-color, -fx-outer-border, -fx-body-color;";

        String filePath = "src/main/resources/photo.png";
        Image image = null;
        try {
            image = new Image(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BackgroundImage backgroundImage =
                new BackgroundImage(
                        image,
                        BackgroundRepeat.NO_REPEAT,  // repeat X
                        BackgroundRepeat.NO_REPEAT,  // repeat Y
                        BackgroundPosition.CENTER,   // position
                        new BackgroundSize(
                                100,   // width  = 100%
                                100,   // height = 100%
                                true,  // width is percentage
                                true,  // height is percentage
                                true,  // contain image within bounds
                                true   // cover all of Region content area
                        )
                );

        pane.setBackground(new Background(backgroundImage));

        TextField nameInputField = new TextField();
        nameInputField.relocate(660, 100);
        nameInputField.setText("Default Fellow Codecooler");
        nameInputField.setStyle("-fx-min-width: 100;\n" +
                "-fx-background-color:#B53737 -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;");
        pane.getChildren().add(nameInputField);

        Button startButton = new Button();
        startButton.relocate(820, 98);
        startButton.setFocusTraversable(false);
        startButton.setText("Lets Start!");
        startButton.setStyle(IDLE_BUTTON_STYLE);
        startButton.setOnMouseEntered(e -> startButton.setStyle(HOVERED_BUTTON_STYLE));
        startButton.setOnMouseExited(e -> startButton.setStyle(IDLE_BUTTON_STYLE));
        startButton.setOnAction(event -> {
            name = String.valueOf(nameInputField.getText());
            map.setPlayerName(name);
            player.setPlayerName(name);
            ArrayList<String> developersName = new ArrayList<>(Arrays.asList("isti", "saz", "mate", "martin"));
            if (developersName.contains(name.toLowerCase(Locale.ROOT))) map.getPlayer().setHealth(99);
            gamePlay(primaryStage);
        });
        pane.getChildren().add(startButton);


        Scene scene = new Scene(pane);
        primaryStage.setWidth(1500);
        primaryStage.setHeight(1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome Fellow Codecooler!");
        primaryStage.show();
    }

    private void gamePlay(Stage primaryStage) {

        SimpleAudioPlayer.playMusic();
        splitMenuButtonWeapon.setText("Weapons");
        splitMenuButtonDefense.setText("Defense");
        exportGameButton.setText("Export Game");

        exportGameButton.setOnAction((e) -> {
            System.out.println("ExportGameButton clicked!");
            exportGame();
        });

        splitMenuButtonWeapon.setOnAction((e) -> {
            System.out.println("SplitMenuButtonAttack clicked!");
        });
        splitMenuButtonDefense.setOnAction((e) -> {
            System.out.println("SplitMenuButtonDefense clicked!");
        });

        exportGameButton.setFocusTraversable(false);
        splitMenuButtonWeapon.setFocusTraversable(false);
        splitMenuButtonDefense.setFocusTraversable(false);
        exportGameButton.setStyle("-fx-font-size: 15px; -fx-min-width: 140");
        splitMenuButtonDefense.setStyle("-fx-font-size: 15px; -fx-background-color: #0000ff; -fx-min-width: 140");
        splitMenuButtonWeapon.setStyle("-fx-font-size: 15px; -fx-background-color: #0000ff; -fx-min-width: 140");


        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label(name), 0, 0);
        ui.add(new Label("Health: "), 0, 1);
        ui.add(new Label("Attack: "), 0, 2);
        ui.add(new Label("Defense: "), 0, 3);
        ui.add(healthLabel, 1, 1);
        ui.add(attackLabel, 1, 2);
        ui.add(defenseLabel, 1, 3);
        ui.add(splitMenuButtonWeapon, 0, 7);
        ui.add(splitMenuButtonDefense, 0, 9);
        pickUpButton.setText("pick up");
        ui.add(pickUpButton, 0, 5);
        ui.add(inventoryLabel, 0, 10);
        ui.add(exportGameButton, 0, 40);
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
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("THE CODECOOL JOURNEY");
        primaryStage.show();

        pickUpButton.setOnAction(event -> {
            map.getPlayer().getCell().getItem().addToInventory();
            pickUpButton.setDisable(true);
            refresh();
        });
    }

    private String createPopUpWindow() {
        //Creating a dialog
        TextInputDialog dialog = new TextInputDialog();
        //Setting buttons
        ((Button) dialog.getDialogPane().lookupButton(ButtonType.OK)).setText("Save");
        //Setting the title
        dialog.setTitle("Save menu");
        dialog.setHeaderText("Save your game!");
        //Setting the content of the dialog
        dialog.setContentText("Name");
        //Adding buttons to the dialog pane
        dialog.showAndWait();
        return dialog.getResult();
    }

    private void createOverwriteWindow(String nameOfSave) {
        //Creating a dialog
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation Tab");
        dialog.setContentText("Would you like to overwrite the already existing state?");
        dialog.getDialogPane().getButtonTypes().add(noButton);
        dialog.getDialogPane().getButtonTypes().add(yesButton);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == yesButton) {
            updateAction(nameOfSave);
        } else if(result.isPresent() && result.get() == noButton) {
            saveAction();
        }
    }

    private void updateAction(String nameOfSave) {
        PlayerModel playerModel = dbManager.updatePlayer(player, nameOfSave);
        dbManager.updateGame(nameOfSave, map.generateFuckingTextFromTheMapState(), playerModel);
        dbManager.updateInventory(nameOfSave, playerModel, player.getInventory(), player.getEquippedItems());
    }

    private void saveAction() {
        String nameOfSave = createPopUpWindow();
        if (nameOfSave != null && !nameOfSave.equals("")) {
            if (dbManager.isNameAlreadyInDB(nameOfSave)) {
                createOverwriteWindow(nameOfSave);
            } else {
                PlayerModel playerModel = dbManager.savePlayer(player);
                dbManager.saveGame(nameOfSave, map.generateFuckingTextFromTheMapState(), playerModel);
                dbManager.saveInventory(playerModel, player.getInventory(), player.getEquippedItems());
            }
        }
    }

    private void exportGame() {
        ArrayList<InventoryModel> inventoryModelList = createInventoryModelList();
        GameState gameState = new GameState(new PlayerModel(player), inventoryModelList, map.generateFuckingTextFromTheMapState());

        String json = new Gson().toJson(gameState);

        try {
            FileOutputStream fileInputStream = new FileOutputStream("gameState.json");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileInputStream);
            objectOutputStream.writeObject(json);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<InventoryModel> createInventoryModelList() {
        ArrayList<InventoryModel> inventoryModelList = new ArrayList<>();

        for (Item item: player.getInventory()) {
            inventoryModelList.add(new InventoryModel(item.getTileName(), item.isEquiped()));
        }

        return inventoryModelList;
    }

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }
}
