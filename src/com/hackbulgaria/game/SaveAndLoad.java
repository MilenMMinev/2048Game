package com.hackbulgaria.game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveAndLoad {
	public void saveState(Grid grid) throws IOException {
		// File file = new File("");
		// FileWriter wasdas = new FileWriter(file);
		// wasdas.w
		// PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
		// writer.
		// ObjectOutputStream object = new ObjectOutputStream();
		// object.
		FileOutputStream fout = new FileOutputStream("saveGame.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(grid);
		oos.close();
	}

	public Grid load() throws IOException, ClassNotFoundException {
		Grid deserializedGrid;
		FileInputStream inputFileStream = new FileInputStream("ser/emp.ser");
		ObjectInputStream objectInputStream = new ObjectInputStream(
				inputFileStream);
		deserializedGrid = (Grid) objectInputStream.readObject();
		objectInputStream.close();
		inputFileStream.close();
		return deserializedGrid;
	}
}
