package com.hackbulgaria.game;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		Grid grid = new Grid();
		// GameRunner gameRunner = new GameRunner();
		// gameRunner.run(grid);
		// grid.print();
		SaveAndLoad saveLoad = new SaveAndLoad();
		saveLoad.save(grid);
		// Grid grid2 = new Grid();
		// grid2 = saveLoad.load();
		// grid2.print();
		}
	}
