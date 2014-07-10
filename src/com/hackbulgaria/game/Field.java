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
				System.out.print(number[i][j].getValue() + " ");
			}
			System.out.println();
		}
	}

	private void orderRow(int index) {
		for (int a = 0; a < 4; a++) {
			for (int i = 0; i < ROWS - 1; i++) {
				Number current = number[index][i];
				Number next = number[index][i + 1];
				if (current.getValue() != 0) {
					if (next.getValue() == 0) {
						next.setValue(current.getValue());
						current.setValue(0);
					}
				}

			}
		}
	}

	public void moveRight() {
		for (int i = 0; i < ROWS; i++) {
			orderRow(i);
			for (int j = COLUMNS - 1; j > 0; j--) {
				if (number[i][j].getValue() == number[i][j - 1].getValue()) {
					number[i][j].merge();
					number[i][j - 1].setValue(0);
				}
			}
			// orderRow(i);
		}
	}

	private Number[][] rotate(Number[][] original) {
		Number[][] rotated = new Number[ROWS][COLUMNS];
		final int M = ROWS;
		final int N = COLUMNS;
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				rotated[c][ROWS - 1 - r] = original[r][c];
			}
		}
		return rotated;
	}

	public void moveLeft() {
		number = rotate(number);
		number = rotate(number);
		moveRight();
		number = rotate(number);
		number = rotate(number);

	}

	public void moveDown() {
		number = rotate(number);
		number = rotate(number);
		number = rotate(number);
		moveRight();
		number = rotate(number);
	}

	public void moveUp() {
		number = rotate(number);
		moveRight();
		number = rotate(number);
		number = rotate(number);
		number = rotate(number);

	}

}
