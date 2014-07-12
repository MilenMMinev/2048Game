package com.hackbulgaria.game;

import java.io.File;

public class SaveAndLoad {
	Cell[][] grid = new Cell[4][4];

	public void saveState(Grid grid) {
		this.grid = grid.getGridState();
		File file = new File("");
	}
}
