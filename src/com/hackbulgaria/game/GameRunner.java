package com.hackbulgaria.game;

import java.io.IOException;

import jline.ConsoleReader;

public class GameRunner {
	private static final int UNDO = 122;
	private static final int REDO = 144;
	private static final int MOVE_UP = 16;
	private static final int MOVE_RIGHT = 6;
	private static final int MOVE_DOWN = 14;
	private static final int LEFT_ARROW = 2;

	public void run(Grid grid) throws IOException {
		ConsoleReader c = null;
		KeyReader k = new KeyReader();
		grid.initialise();
		while (grid.getGameRun()) {
			// for (int i = 0; i < 50; i++)
			System.out.println();
			// c.clearScreen();
			grid.print();
			// char playerMove = k.getPlayerMove();
			// System.out.println(playerM4ove);
			// switch (playerMove) {
			// case 'd': // d
			// ForwardAndBackward.saveMove(grid.clone());
			// grid.moveRight();
			// grid.addRandom();
			// break;
			// case 'u':
			// grid = ForwardAndBackward.undo();
			// break;
			// default:
			// break;
			// }
			switch (k.getPlayerMove()) {
			case LEFT_ARROW:
				ForwardAndBackward.saveMove(grid.clone());
				grid.moveLeft();
				grid.addRandom();
				break;
			case MOVE_DOWN:
				ForwardAndBackward.saveMove(grid.clone());
				grid.moveDown();
				grid.addRandom();
				break;
			case MOVE_RIGHT:
				ForwardAndBackward.saveMove(grid.clone());
				grid.moveRight();
				grid.addRandom();
				break;
			case MOVE_UP:
				ForwardAndBackward.saveMove(grid.clone());
				grid.moveUp();
				grid.addRandom();
				break;
			case REDO:// r
				grid = ForwardAndBackward.redo();
				break;
			case UNDO:// z
				grid = ForwardAndBackward.undo();
			default:
				break;
			}
			// ForwardAndBackward.back().print();
		}
		// r 144 z 122
		System.out.println("Game Over!!!");
	}

}
