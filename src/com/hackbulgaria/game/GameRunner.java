package com.hackbulgaria.game;

import java.io.IOException;

public class GameRunner {
	public boolean run() throws IOException {
		Grid game = new Grid();
		KeyReader k = new KeyReader();
		game.initialise();
		while (true) {
			for (int i = 0; i < 50; i++)
				System.out.println();
			game.print();
			switch (k.getPlayerMove()) {
			case 2:
				game.moveLeft();
				break;
			case 14:
				game.moveDown();
				break;
			case 6:
				game.moveRight();
				break;
			case 16:
				game.moveUp();
				break;
			case 113:
				return false;
			default:
				break;
			}
			game.addRandom();
		}
	}

}
