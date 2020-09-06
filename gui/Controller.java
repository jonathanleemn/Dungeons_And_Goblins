package gui;

import javax.swing.plaf.synth.SynthSeparatorUI;

import entities.Monster;
import events.Combat;
import events.Events;
import game.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import world.Map;

public class Controller implements EventHandler<ActionEvent>
{
	entities.Player player;
	Viewer view;
	game.Commands commands;
	Game game = new Game();
	Events newEvent = new Events();
	Map map = new Map(game.player);
	Monster monster = new Monster();
	Combat combat = new Combat();
	int index;

	public Controller(entities.Player p, Viewer v)
	{
		player = p;
		view = v;

		// register components to the listener
		view.getSubmit().setOnAction(this);
		view.getClear().setOnAction(this);
		view.getStartGame().setOnAction(this);
		view.getStartGame().setVisible(false);

		view.getInventory().setOnAction(this);
		view.getInventory().setVisible(false);
		view.getConsume().setOnAction(this);
		view.getConsume().setVisible(false);
		view.getEquip().setOnAction(this);
		view.getEquip().setVisible(false);
		view.getUnequip().setOnAction(this);
		view.getUnequip().setVisible(false);
		view.getMove().setOnAction(this);
		view.getMove().setVisible(false);
		view.getMap().setOnAction(this);
		view.getMap().setVisible(false);
		view.getStatus().setOnAction(this);
		view.getStatus().setVisible(false);

		view.getNorth().setOnAction(this);
		view.getNorth().setVisible(false);
		view.getSouth().setOnAction(this);
		view.getSouth().setVisible(false);
		view.getWest().setOnAction(this);
		view.getWest().setVisible(false);
		view.getEast().setOnAction(this);
		view.getEast().setVisible(false);
		view.getMapField().setDisable(true);
		view.getAttack().setOnAction(this);
		view.getAttack().setVisible(false);
		view.getExamine().setOnAction(this);
		view.getExamine().setVisible(false);

		view.getWeaponComboBox().setVisible(false);
		view.getWeaponComboBox().setOnAction(this);
		view.getArmorComboBox().setVisible(false);
		view.getArmorComboBox().setOnAction(this);
		view.getSpellComboBox().setVisible(false);
		view.getSpellComboBox().setOnAction(this);
	}

	@Override
	public void handle(ActionEvent event)
	{
		try
		{
			// submit button outputs your character status to the GUI text field
			if (event.getSource().equals(view.getSubmit()))
			{
				view.submit.setVisible(false);
				String Class = view.classComboBox.getValue();
				game.selectAClass(Class);
				game.player.setName(view.getNameField().getText());
				view.getOutput().appendText(game.player.toString());
				view.startGame.setVisible(true);
			}

			// clear button clears it and resets character name
			if (event.getSource().equals(view.getClear()))
			{
				view.getOutput().clear();
				view.submit.setVisible(true);
				view.startGame.setVisible(false);
			}

			// starts the game and makes all the buttons appear in the GUI
			if (event.getSource().equals(view.getStartGame()))
			{
				view.submit.setVisible(false);
				view.clear.setVisible(false);
				view.classComboBox.setDisable(true);
				view.nameField.setDisable(true);
				view.startGame.setVisible(false);
				startGamePressed(event);
				view.inventory.setVisible(true);
				view.consume.setVisible(true);
				view.equip.setVisible(true);
				view.unequip.setVisible(true);
				view.move.setVisible(true);
				view.map.setVisible(true);
				view.mapField.setDisable(false);
				createMap();
				view.weaponComboBox.setVisible(true);
				view.armorComboBox.setVisible(true);
				view.getStatus().setVisible(true);

				// if player was a mage, then it'd add a spellbook to that class
				if (view.classComboBox.getValue().equals("Mage"))
				{
					player.fillSpellBook();
					System.out.println(player.spells.toString() + " Spellbook filled");
					view.spellList = FXCollections.observableArrayList(player.spells);
					view.spellComboBox.setItems(view.spellList);
					view.spellComboBox.setVisible(true);
					player.displaySpells();
				}
			}

			// outputs inventory to the text area
			if (event.getSource().equals(view.getInventory()))
			{
				System.out.println("Inventory viewed");
				view.getOutput().appendText("\n" + game.player.backpack.toString() + "\n");
			}

			// player consumes a health potion and removes it from the inventory
			if (event.getSource().equals(view.getConsume()))
			{
				System.out.println("consumable consumed");
				view.getOutput().appendText("\n" + game.player.consumePotion() + "\n");
			}

			if (event.getSource().equals(view.getMove()))
			{
				System.out.println("Player selecting a move");
				view.inventory.setDisable(true);
				view.consume.setDisable(true);
				view.equip.setDisable(true);
				view.unequip.setDisable(true);
				view.move.setDisable(true);
				view.map.setDisable(true);
				view.north.setVisible(true);
				view.south.setVisible(true);
				view.west.setVisible(true);
				view.east.setVisible(true);
				view.status.setDisable(true);
			}

			// logic for moving the character north
			if (event.getSource().equals(view.getNorth()))
			{
				// makes sure that you can't go out of bounds
				if (game.player.getLocX() - 1 < 0 || game.player.getLocX() - 1 > map.map.length - 1)
				{
					view.getOutput().appendText("\n\nYou walked into a wall. Good job.");
					bumpedIntoWall();
				}

				else
				{
					map.map[game.player.loc.getRow()][game.player.loc.getCol()] = 0;
					game.player.setLocX(game.player.getLocX() - 1);
					view.getOutput().appendText("\n" + game.player.loc.toString());
					System.out.println("Player moved North");
					updateMap();
					buttonReset();
					try
					{
						checkIfMonsterRoom();
					} catch (Exception e)
					{

					}
				}
			}

			// logic for moving player south
			if (event.getSource().equals(view.getSouth()))
			{
				// makes sure that you can't go out of bounds
				if (game.player.getLocX() + 1 < 0 || game.player.getLocX() + 1 > map.map.length - 1)
				{
					view.getOutput().appendText("\n\nYou walked into a wall. Good job.");
					bumpedIntoWall();

				} else
				{
					map.map[game.player.loc.getRow()][game.player.loc.getCol()] = 0;
					game.player.setLocX(game.player.getLocX() + 1);
					view.getOutput().appendText("\n" + game.player.loc.toString());
					System.out.println("Player moved South");
					updateMap();
					buttonReset();
					try
					{
						checkIfMonsterRoom();
					} catch (Exception e)
					{
					}
				}
			}

			// logic for moving the player west
			if (event.getSource().equals(view.getWest()))
			{
				// makes sure that you can't go out of bounds
				if (game.player.getLocY() - 1 < 0 || game.player.getLocY() - 1 > map.map.length - 1)
				{
					view.getOutput().appendText("\n\nYou walked into a wall. Good job.");
					bumpedIntoWall();

				} else
				{
					map.map[game.player.loc.getRow()][game.player.loc.getCol()] = 0;
					game.player.setLocY(game.player.getLocY() - 1);
					view.getOutput().appendText("\n" + game.player.loc.toString());
					System.out.println("Player moved West");
					updateMap();

					buttonReset();
					try
					{
						checkIfMonsterRoom();
					} catch (Exception e)
					{

					}

				}
			}
			// logic for moving player east
			if (event.getSource().equals(view.getEast()))
			{
				// makes sure that you can't go out of bounds
				if (game.player.getLocY() + 1 < 0 || game.player.getLocY() + 1 > map.map.length - 1)
				{
					view.getOutput().appendText("\n\nYou walked into a wall. Good job.");
					bumpedIntoWall();
				} else
				{
					map.map[game.player.loc.getRow()][game.player.loc.getCol()] = 0;
					game.player.setLocY(game.player.getLocY() + 1);
					view.getOutput().appendText("\n" + game.player.loc.toString());
					System.out.println("Player moved East");
					updateMap();
					buttonReset();
					try
					{
						checkIfMonsterRoom();
					} catch (Exception e)
					{

					}
				}
			}

			// outputs player.toString onto text area
			if (event.getSource().equals(view.getStatus()))
			{
				view.getOutput().appendText("\n" + game.player.toString() + "\n");

			}

			// button that only appears when you've encountered a monster
			// allows you to engage in combat with foe
			if (event.getSource().equals(view.getAttack()))
			{

				try
				{
					view.spellComboBox.setOnAction(e -> game.player.setBaseDamage(
							player.spellsList.get((view.spellComboBox.getSelectionModel().getSelectedIndex()))
									.getSpellPower() + game.player.getBaseDamage()));
					System.out
							.println(player.spellsList.get((view.spellComboBox.getSelectionModel().getSelectedIndex()))
									.getSpellPower() + " " + game.player.getBaseDamage());
				} catch (ArrayIndexOutOfBoundsException e)
				{
					game.player.setBaseDamage(game.player.getBaseDamage());
				} // add this to your code

				view.getOutput().appendText(combat.attack(game.player, monster));
				view.getOutput().appendText(combat.attack(monster, game.player));

				if (monster.getHealth() <= 0)
				{

					System.out.println("Monster was killed");
					view.getOutput().appendText("Enemy slain\n");
					view.attack.setVisible(false);
					view.examine.setVisible(false);
					view.inventory.setDisable(false);
					view.unequip.setDisable(false);
					view.move.setDisable(false);
					newEvent.monsterTrue = false;
				}
				playerDeath();

			}

			if (event.getSource().equals(view.getExamine()))
			{
				view.getOutput().appendText(monster.getDescription() + "\n");
				System.out.println("Player examined monster");
			}

		}
		// will prompt this message if player doesn't select a class
		// and tries to start game
		catch (NullPointerException e)
		{
			view.getOutput().appendText("Press clear and select a class\n");
			System.out.println("Player tried to submit without selecting class");
		}
	}

	//method for updating the weapon/armor comboboxes
	//whenever you encounter a new equippable item, it takes the current combobox and clears it
	//it then slaps on a new combobox on top of it with the updated array
	public void refreshComboBoxes() throws Exception
	{

		if (newEvent.weaponTrue == true || newEvent.armorTrue == true)

		{

			view.weaponComboBox.getSelectionModel().clearSelection();
			view.weaponComboBox.getItems().removeAll(view.weaponComboBox.getItems());
			view.equippableWeapons = FXCollections.observableArrayList(game.player.backpack.equippableWeapons);
			view.weaponComboBox.setItems(view.equippableWeapons);
			view.weaponComboBox.setOnAction(e ->
			{
				try
				{
					game.player.backpack.equipItem(view.weaponComboBox.getSelectionModel().getSelectedIndex());
				} catch (Exception e2)
				{
				}
			});
			view.armorComboBox.getSelectionModel().clearSelection();
			view.armorComboBox.getItems().removeAll(view.armorComboBox.getItems());
			view.equippableArmor = FXCollections.observableArrayList(game.player.backpack.equippableArmor);
			view.armorComboBox.setItems(view.equippableArmor);
			view.armorComboBox.setOnAction(
					e ->
					{
						try
						{
							game.player.backpack.equipItem(view.armorComboBox.getSelectionModel().getSelectedIndex());
						} catch (Exception e1)
						{
						}
					});
			newEvent.weaponTrue = false;
			newEvent.armorTrue = false;
		}
	}

	// resets buttons whenever you run into a wall
	private void bumpedIntoWall()
	{
		System.out.println("Player tried to move off of 2d array");
		view.inventory.setDisable(false);
		view.consume.setDisable(false);
		view.unequip.setDisable(false);
		view.move.setDisable(false);
		view.status.setDisable(false);
		view.north.setVisible(false);
		view.south.setVisible(false);
		view.west.setVisible(false);
		view.east.setVisible(false);
	}

	// resets buttons after player moves
	private void buttonReset()
	{
		view.north.setVisible(false);
		view.south.setVisible(false);
		view.west.setVisible(false);
		view.east.setVisible(false);
		view.inventory.setDisable(false);
		view.consume.setDisable(false);
		view.equip.setDisable(false);
		view.unequip.setDisable(false);
		view.move.setDisable(false);
		view.map.setDisable(false);
		view.status.setDisable(false);
	}

	// ouputs message when player dies from monster
	public void playerDeath()
	{
		if (game.player.getHealth() <= 0)
		{
			System.out.println("Player died");
			view.getOutput().appendText(game.player.getName() + " was slain.\n"
					+ "------------------------------GAME OVER------------------------------");
			view.inventory.setDisable(true);
			view.consume.setDisable(true);
			view.unequip.setDisable(true);
			view.move.setDisable(true);
			view.weaponComboBox.setDisable(true);
			view.armorComboBox.setDisable(true);
			view.mapField.setDisable(true);
			view.status.setDisable(true);
			view.attack.setDisable(true);
			view.examine.setDisable(true);
			view.spellComboBox.setDisable(true);
		}
	}

	// updates player loc on map text field
	public void updateMap()
	{
		view.getMapField().appendText("\n");
		for (int i = 0; i < map.map.length; i++)
		{
			for (int j = 0; j < map.map.length; j++)
			{
				map.map[game.player.loc.getRow()][game.player.loc.getCol()] = 1;
				view.getMapField().appendText(String.valueOf(map.map[i][j]) + "    ");
			}
			view.getMapField().appendText("\n");
		}
	}

	// checks to see if there's a monster
	// if so, start combat
	public void checkIfMonsterRoom() throws Exception
	{

		view.getOutput().appendText("\n\n" + newEvent.getEvent(game.player));
		if (newEvent.monsterTrue == true)
		{
			monster = newEvent.generateMonster();
			view.attack.setVisible(true);
			view.examine.setVisible(true);

			view.inventory.setDisable(true);
			view.unequip.setDisable(true);
			view.move.setDisable(true);
		}
		
		//refreshes if you don't encounter a monster
		refreshComboBoxes();
	}

	// creates the map
	public void createMap()
	{
		for (int i = 0; i < map.map.length; i++)
		{
			for (int j = 0; j < map.map.length; j++)
			{
				map.map[game.player.loc.getRow()][game.player.loc.getCol()] = 1;
				view.getMapField().appendText(String.valueOf(map.map[i][j]) + "    ");
			}
			view.getMapField().appendText("\n");
		}
	}

	public void startGamePressed(ActionEvent event)
	{
		System.out.println("Game Started");
		view.getOutput().appendText("\n\n" + newEvent.getEvent(game.player));
		view.getOutput().appendText("\n\n You location on the map is marked with a 1");
		game.addStartItems();
		addEquippablesToInventory();
	}

	// adds beginning items at start of game to the weapon + armor comboboxes
	public void addEquippablesToInventory()
	{
		view.equippableWeapons = FXCollections.observableArrayList(game.player.backpack.equippableWeapons);
		view.weaponComboBox.setItems(view.equippableWeapons);
		view.weaponComboBox.setOnAction(
				e -> game.player.backpack.equipItem(view.weaponComboBox.getSelectionModel().getSelectedIndex()));
		System.out.println(game.player.backpack.getEquippableItems());

		view.equippableArmor = FXCollections.observableArrayList(game.player.backpack.equippableArmor);
		view.armorComboBox.setItems(view.equippableArmor);
		view.armorComboBox.setOnAction(
				e -> game.player.backpack.equipItem(view.armorComboBox.getSelectionModel().getSelectedIndex()));

	}
}
