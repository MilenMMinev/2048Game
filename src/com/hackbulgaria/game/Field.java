package com.hackbulgaria.game;

import java.util.Random;

public class Field {
	private final int ROWS = 4;
	private final int COLUMNS = 4;

	private Number[][] number = new Number[ROWS][COLUMNS];

	private void generateRandom() {
		Random rnd = new Random();
		int x = rnd.nextInt(4);
		int y = rnd.nextInt(4);
		int value;
		if (rnd.nextInt(2) == 0) {
			value = 2;
		} else {
			value = 4;
		}
		if (number[x][y].getValue() == 0) { // TO DO: Problem if no free space
			number[x][y].setValue(value);
		} else {
			generateRandom();
		}
	}

	public void initialise() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				number[i][j] = new Number();
			}
		}
		generateRandom();
		generateRandom();
	}

	public void print() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				System.out.print(number[i][j].getValue());
			}
			System.out.println();
		}
	}

}
