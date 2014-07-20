package com.hackbulgaria.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				string += (cell[i][j].getValue() + " ");
			}
			string += System.lineSeparator();
		}
		return string;
	}

	private transient final int ROWS = 4;
	private transient final int COLUMNS = 4;

	private Cell[][] cell = new Cell[ROWS][COLUMNS];

	public boolean areDifferent(Grid other) {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (this.cell[i][j].getValue() != other.cell[i][j].getValue()) {
					return true;
				}
			}
		}
		return false;
	}

	public int getCellValue(int i, int j) {
		return this.getCells()[i][j].getValue();
	}

	public Cell[][] getCells() {
		return this.cell;
	}

	public Grid() {
		this.initialise();
	}

	public static boolean youWon(Grid grid) {
		for (int row = 0; row < grid.ROWS; row++) {
			for (int col = 0; col < grid.COLUMNS; col++) {
				if (grid.cell[row][col].getValue() == 2048) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean youLose(Grid grid) {// TODO:implement youLose
		Grid cloneGridRight = grid.clone();
		cloneGridRight.moveRight();
		Grid cloneGridLeft = grid.clone();
		cloneGridRight.moveLeft();
		Grid cloneGridUp = grid.clone();
		cloneGridRight.moveUp();
		Grid cloneGridDown = grid.clone();
		cloneGridRight.moveDown();
		if (grid.areDifferent(cloneGridDown)) {
			return false;
		}
		if (grid.areDifferent(cloneGridUp)) {
			return false;
		}
		if (grid.areDifferent(cloneGridLeft)) {
			return false;
		}
		if (grid.areDifferent(cloneGridRight)) {
			return false;
		}
		return true;
	}

	public Cell[][] getGrid() {
		return this.cell;
	}

	@Override
	public Grid clone() {
		Grid grid = new Grid();
		for (int i = 0; i < this.ROWS; i++) {
			for (int j = 0; j < this.COLUMNS; j++) {
				Cell cell = new Cell();
				cell.setValue(this.cell[i][j].getValue());
				grid.cell[i][j] = cell;
			}
		}
		return grid;
	}

	public void addRandom() {
		Random rnd = new Random();
		List<Coordinates> freeSquares = new ArrayList<>();
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLUMNS; j++)
				if (cell[i][j].getValue() == 0)
					freeSquares.add(new Coordinates(i, j));

		if (freeSquares.size() != 0) {

			int rndIndex = rnd.nextInt(freeSquares.size());
			// Select random free square
			Coordinates rnd_square = freeSquares.get(rndIndex);
			// Get the coordinates of this square
			cell[rnd_square.getX()][rnd_square.getY()] = getRandom();
			// set its value to either 2 or 4
		}
	}

	private Cell getRandom() {
		Cell random_cell = new Cell();
		Random rnd = new Random();
		if (rnd.nextInt(10) == 1) { // in 10% of the cases
			random_cell.setValue(4);
		} else {
			random_cell.setValue(2);
		}
		return random_cell;
	}

	public void initialise() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				cell[i][j] = new Cell();
			}
		}
		addRandom();
		addRandom();
	}

	private void orderRow(int index) {
		for (int a = 0; a < 4; a++) {
			for (int i = 0; i < ROWS - 1; i++) {
				Cell current = cell[index][i];
				Cell next = cell[index][i + 1];
				if (current.getValue() != 0) {
					if (next.getValue() == 0) {
						next.setValue(current.getValue());
						current.setValue(0);
					}
				}

			}
		}
	}

	private Cell[][] rotate(Cell[][] original) {
		Cell[][] rotated = new Cell[ROWS][COLUMNS];
		final int M = ROWS;
		final int N = COLUMNS;
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				rotated[c][ROWS - 1 - r] = original[r][c];
			}
		}
		return rotated;
	}

	public void moveRight() {
		for (int i = 0; i < ROWS; i++) {
			orderRow(i);
			for (int j = COLUMNS - 1; j > 0; j--) {
				if (cell[i][j].getValue() == cell[i][j - 1].getValue()) {
					cell[i][j].merge();
					cell[i][j - 1].setValue(0);
				}
			}
			orderRow(i);
		}
	}

	public void moveLeft() {
		cell = rotate(cell);
		cell = rotate(cell);
		moveRight();
		cell = rotate(cell);
		cell = rotate(cell);

	}

	public void moveDown() {
		cell = rotate(cell);
		cell = rotate(cell);
		cell = rotate(cell);
		moveRight();
		cell = rotate(cell);
	}

	public void moveUp() {
		cell = rotate(cell);
		moveRight();
		cell = rotate(cell);
		cell = rotate(cell);
		cell = rotate(cell);

	}

}
