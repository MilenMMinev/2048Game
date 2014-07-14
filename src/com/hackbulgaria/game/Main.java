package com.hackbulgaria.game;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		Grid grid = new Grid();
		GameRunner gameRunner = new GameRunner();
		gameRunner.run(grid);
		}
	}
