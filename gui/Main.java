package gui;

import javax.swing.JFrame;
import entities.Player;
import game.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import world.Location;

public class Main extends Application
{

	public static void main(String[] args)
	{
		Main.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Viewer view = new Viewer();
		Player player = new Player("Player", new Location(0, 0));
		Controller control = new Controller(player, view);
		primaryStage.setTitle("Character Select");
		primaryStage.setScene(view.getScene());
		
		primaryStage.show();
	}
}
