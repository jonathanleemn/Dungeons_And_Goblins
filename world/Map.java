package world;

import java.util.Random;
import java.util.Scanner;
import entities.Player;

public class Map
{
	public int[][] map = new int[10][10];
	Location loc;
	Random rand = new Random();
	Scanner input = new Scanner(System.in);
	char direction;
	Player player;

	public Map(Player player) //sets players x and y value on arraymap to 1
	{
		this.player = player;
		map[player.loc.getRow()][player.loc.getCol()] = 1;
	}
	
	public void resetMap(){//refills the map with 0
		for (int row = 0; row < map.length; row++)
		{
			for (int col = 0; col < map[row].length; col++)
			{
				map[row][col] = 0;
			}
		}
	}

	public void fillMap(Player player)//fills map with 0
	{
		for (int row = 0; row < map.length; row++)
		{
			for (int col = 0; col < map[row].length; col++)
			{
				map[row][col] = 0;
			}
		}
		map[player.loc.getRow()][player.loc.getCol()] = 1;
	}

	public boolean isValidMove(int nextLoc)//checks move for validity
	{
		return nextLoc<map.length && nextLoc > 0;
	}
	
	public void drawMap()//prints map
	{
		System.out.println("\nYour location is marked with a 1\n");
		for(int row = 0; row < map.length; row++)
		{
			for (int col = 0; col < map[row].length; col++)
			{
				System.out.printf("%d ", map[row][col]);
			}
			System.out.println();
		}
	}	

}