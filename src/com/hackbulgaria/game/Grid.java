package com.hackbulgaria.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {
	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				string += (number[i][j].getValue() + " ");
			}
			string += System.lineSeparator();
		}
		return string;
	}

	private final int ROWS = 4;
	private final int COLUMNS = 4;
	private boolean gameRun = true;

	private Cell[][] number = new Cell[ROWS][COLUMNS];
	
	public Grid() {
		this.initialise();
	}

	public boolean getGameRun() {
		return this.gameRun;
	}
	public Cell[][] getGrid() {
		return this.number;
	}

	@Override
	public Grid clone() {
		Grid grid = new Grid();
		for (int i = 0; i < this.ROWS; i++) {
			for (int j = 0; j < this.COLUMNS; j++) {
				Cell cell = new Cell();
				cell.setValue(this.number[i][j].getValue());
				grid.number[i][j] = cell;
			}
		}
		return grid;
	}

	public void addRandom() {
		Random rnd = new Random();
		List<Coordinates> freeSquares = new ArrayList<>();
		for (int i = 0; i < ROWS; i++)
			for(int j = 0; j < COLUMNS; j++)
			if(number[i][j].getValue() == 0)
				freeSquares.add(new Coordinates(i, j));
				
		
		if (freeSquares.size() == 0) {
			gameRun = false;
		}
		// System.out.println("Game Over"); // TO DO:
			//game over
		else{
			int rndIndex = rnd.nextInt(freeSquares.size());  // Select random free square
			Coordinates rnd_square = freeSquares.get(rndIndex); // Get the coordinates of this square
			number[rnd_square.getX()][rnd_square.getY()] = getRandom(); // set its value to either 2 or 4
			
		}
	}

	private Cell getRandom() {
		Cell random_number = new Cell();
		Random rnd = new Random();
		if (rnd.nextInt(10) == 1){ // in 10% of the cases
			random_number.setValue(4);
		}
		else{
			random_number.setValue(2);
		}
		return random_number;
	}

	public void initialise() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				number[i][j] = new Cell();
			}
		}
		addRandom();
		addRandom();
	}

	public void print() { // TO DO: make it usable by Front End
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
				Cell current = number[index][i];
				Cell next = number[index][i + 1];
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
				if (number[i][j].getValue() == number[i][j - 1].getValue()) {
					number[i][j].merge();
					number[i][j - 1].setValue(0);
				}
			}
			// orderRow(i);
		}
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
