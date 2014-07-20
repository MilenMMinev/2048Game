package com.hackbulgaria.game;

import java.io.IOException;



public class GameRunner {

	public void run(Grid grid) throws IOException {
		grid.initialise();
		Visualiser v = new Visualiser();
		v.show();
		
		

}
};