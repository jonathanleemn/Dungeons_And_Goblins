package gui;

import java.util.ArrayList;

import entities.Player;
import game.Commands;
import items.Inventory;
import items.Weapon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import utilities.Spells;

public class Viewer
{
	//creates gui layout
	BorderPane border;
	GridPane grid;
	FlowPane buttons, startButtons, gameButtons, moveButtons;
	Button submit, clear, startGame, inventory, consume, equip, unequip, move, status, map, north, south, west, east, attack, examine;
	TextField nameField;
	Label name;
	TextArea output, mapField;
	Scene scene;
	ComboBox<String> classComboBox, weaponComboBox, armorComboBox, spellComboBox;
	Image fighter, mage, rogue, placeholder;
	ImageView imageHolder;
	ObservableList<String> classList, equippableWeapons, equippableArmor;
	ObservableList<String> spellList;
	ArrayList <Image> imageArrayList; 
	Image displayImage;
	Inventory backpack;

	public Viewer()
	{
		//creating all the components of the GUI
		backpack = new Inventory();
		border = new BorderPane();
		grid = new GridPane();
		buttons = new FlowPane();
		startButtons = new FlowPane();
		gameButtons = new FlowPane();
		moveButtons = new FlowPane();
		classList = FXCollections.observableArrayList("Fighter", "Mage", "Rogue");
		imageArrayList = new ArrayList<Image>();
		
		submit = new Button("Submit");
		clear = new Button("Clear");
		startGame = new Button("Start Game");
		inventory = new Button("Inventory");
		consume = new Button("Consume");
		equip = new Button("Equip");
		unequip = new Button("Unequip");
		move = new Button("Move");
		status = new Button("Status");
		map = new Button("Map");
		north = new Button("North");
		south = new Button("South");
		west = new Button("West");
		east = new Button("East");
		attack = new Button("Attack");
		examine = new Button("Examine");
		
		clear.setStyle("-fx-base: #fefefe");
		submit.setStyle("-fx-base: #fefefe");
		startGame.setStyle("-fx-base: #fefefe");
		inventory.setStyle("-fx-base: #fefefe");
		consume.setStyle("-fx-base: #fefefe");
		equip.setStyle("-fx-base: #fefefe");
		unequip.setStyle("-fx-base: #fefefe");
		move.setStyle("-fx-base: #fefefe");
		status.setStyle("-fx-base: #fefefe");
		map.setStyle("-fx-base: #fefefe");
		north.setStyle("-fx-base: #fefefe");
		south.setStyle("-fx-base: #fefefe");
		west.setStyle("-fx-base: #fefefe");
		east.setStyle("-fx-base: #fefefe");
		attack.setStyle("-fx-base: #fefefe");
		examine.setStyle("-fx-base: #fefefe");
		
		border.setTop(grid);
		border.setBottom(buttons);
		grid.setPadding(new Insets(8, 8, 8, 8));
		grid.setVgap(4);
		grid.setHgap(10);

		scene = new Scene(border, 500, 600);

		name = new Label("Name: ");


		nameField = new TextField();
		nameField.setText("Enter Character Name");
		grid.add(nameField, 1, 0);

		//combobox for selecting a class
		//whenever you select a class the character portrait changes
		classComboBox = new ComboBox<>();
		classComboBox.setItems(classList);
		classComboBox.setPromptText("Select Class");
		classComboBox.setOnAction(e -> imageHolder.setImage(imageArrayList.get(classComboBox.getSelectionModel().getSelectedIndex())));
		
		weaponComboBox = new ComboBox<>();
		weaponComboBox.setPromptText("Equip Weapon");
		
		armorComboBox = new ComboBox<>();
		armorComboBox.setPromptText("Equip Armor");
		
		spellComboBox = new ComboBox<>();
		spellComboBox.setPromptText("Select Spell");
		
		grid.add(classComboBox, 2, 0);
		
		imageHolder = new ImageView();
		
		fighter = new Image("file:src/gui/fighter.gif");
		imageArrayList.add(fighter);
		
		mage = new Image("file:src/gui/mage.gif");
		imageArrayList.add(mage);
		
		rogue = new Image("file:src/gui/rogue.gif");
		imageArrayList.add(rogue);
		
		imageArrayList.add(fighter);
		imageArrayList.add(mage);
		imageArrayList.add(rogue);
		
		//adds a default image so that it doesn't appear blank at first
		placeholder = new Image("file:src/gui/placeholder.gif");
		imageHolder.setImage(placeholder);
		grid.add(imageHolder, 2, 1);
		
		mapField = new TextArea();
		grid.add(mapField, 1, 1);
		
		output = new TextArea();
		border.setCenter(output);
		//adding everything to the GUI
		startButtons.getChildren().addAll(submit, clear, startGame);
		gameButtons.getChildren().addAll(inventory, consume, move, status);
		moveButtons.getChildren().addAll(north, south, west, east, attack, examine);
		buttons.getChildren().addAll(startButtons, gameButtons, moveButtons, weaponComboBox, armorComboBox, spellComboBox);
	}

	
	public Button getAttack()
	{
		return attack;
	}


	public void setAttack(Button attack)
	{
		this.attack = attack;
	}


	public Button getExamine()
	{
		return examine;
	}


	public void setExamine(Button examine)
	{
		this.examine = examine;
	}


	public Button getStatus()
	{
		return status;
	}


	public void setStatus(Button status)
	{
		this.status = status;
	}


	public ComboBox<String> getWeaponComboBox()
	{
		return weaponComboBox;
	}


	public void setWeaponComboBox(ComboBox<String> weaponComboBox)
	{
		this.weaponComboBox = weaponComboBox;
	}


	public ComboBox<String> getArmorComboBox()
	{
		return armorComboBox;
	}


	public void setArmorComboBox(ComboBox<String> spellComboBox)
	{
		this.spellComboBox = spellComboBox;
	}
	
	
	public ComboBox<String> getSpellComboBox()
	{
		return spellComboBox;
	}


	public void setSpellComboBox(ComboBox<String> spellComboBox)
	{
		this.spellComboBox = spellComboBox;
	}


	public TextArea getMapField()
	{
		return mapField;
	}


	public void setMapField(TextArea mapField)
	{
		this.mapField = mapField;
	}


	public Button getNorth()
	{
		return north;
	}


	public void setNorth(Button north)
	{
		this.north = north;
	}


	public Button getSouth()
	{
		return south;
	}


	public void setSouth(Button south)
	{
		this.south = south;
	}


	public Button getWest()
	{
		return west;
	}


	public void setWest(Button west)
	{
		this.west = west;
	}


	public Button getEast()
	{
		return east;
	}


	public void setEast(Button east)
	{
		this.east = east;
	}


	public Image getDisplayImage() {
		return displayImage;
	}

	public void setDisplayImage(Image displayImage) {
		this.displayImage = displayImage;
	}

	public BorderPane getBorder()
	{
		return border;
	}

	public void setBorder(BorderPane border)
	{
		this.border = border;
	}

	public GridPane getGrid()
	{
		return grid;
	}

	public void setGrid(GridPane grid)
	{
		this.grid = grid;
	}

	public FlowPane getButtons()
	{
		return buttons;
	}

	public void setButtons(FlowPane buttons)
	{
		this.buttons = buttons;
	}

	public Button getSubmit()
	{
		return submit;
	}

	public void setSubmit(Button submit)
	{
		this.submit = submit;
	}

	public Button getClear()
	{
		return clear;
	}

	public void setClear(Button clear)
	{
		this.clear = clear;
	}

	public TextField getNameField()
	{
		return nameField;
	}

	public void setNameField(TextField nameField)
	{
		this.nameField = nameField;
	}

	public Label getName()
	{
		return name;
	}

	public void setName(Label name)
	{
		this.name = name;
	}

	public TextArea getOutput()
	{
		return output;
	}

	public void setOutput(TextArea output)
	{
		this.output = output;
	}

	public Scene getScene()
	{
		return scene;
	}

	public void setScene(Scene scene)
	{
		this.scene = scene;
	}

	public Button getStartGame()
	{
		return startGame;
	}

	public void setStartGame(Button startGame)
	{
		this.startGame = startGame;
	}

	public Button getInventory()
	{
		return inventory;
	}

	public void setInventory(Button inventory)
	{
		this.inventory = inventory;
	}

	public Button getConsume()
	{
		return consume;
	}

	public void setConsume(Button consume)
	{
		this.consume = consume;
	}

	public Button getEquip()
	{
		return equip;
	}

	public void setEquip(Button equip)
	{
		this.equip = equip;
	}

	public Button getUnequip()
	{
		return unequip;
	}

	public void setUnequip(Button unequip)
	{
		this.unequip = unequip;
	}

	public Button getMove()
	{
		return move;
	}

	public void setMove(Button move)
	{
		this.move = move;
	}

	public Button getMap()
	{
		return map;
	}

	public void setMap(Button map)
	{
		this.map = map;
	}

	

}
