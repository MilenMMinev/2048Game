package com.hackbulgaria.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {
	private final int ROWS = 4;
	private final int COLUMNS = 4;

	private Number[][] number = new Number[ROWS][COLUMNS];
	
	public boolean run() throws IOException{
		Grid game = new Grid();
		KeyReader k = new KeyReader();
		game.initialise();
		while (true){
			for(int i = 0; i < 50; i++)
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

	private void addRandom() {
		Random rnd = new Random();
		List<Coordinates> free_squaers = new ArrayList<>();
		for (int i = 0; i < ROWS; i++)
			for(int j = 0; j < COLUMNS; j++)
			if(number[i][j].getValue() == 0)
				free_squaers.add(new Coordinates(i, j));
				
		
		if (free_squaers.size() == 0)
			System.out.println("Game Over"); // TO DO:
			//game over
		else{
			int rnd_index = rnd.nextInt(free_squaers.size());  // Select random free square
			Coordinates rnd_square = free_squaers.get(rnd_index); // Get the coordinates of this square
			number[rnd_square.getX()][rnd_square.getY()] = getRandom(); // set its value to either 2 or 4
			
		}
	}

	private Number getRandom() {
		Number random_number = new Number();
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
				number[i][j] = new Number();
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
