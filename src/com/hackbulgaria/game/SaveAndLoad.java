package com.hackbulgaria.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveAndLoad {
	private final File file = new File("grid.ser");
	public void save(Grid grid) throws FileNotFoundException, IOException {
		file.createNewFile();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				file));
		out.writeObject(grid);
		out.flush();
		out.close();
	}

	public Grid load() throws IOException, ClassNotFoundException {
		Grid deserializedGrid = null;
		@SuppressWarnings("resource")
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		Object obj = in.readObject();
		if (obj instanceof Grid) {
			deserializedGrid = (Grid) obj;
			System.out.println("Done!!!");
		} else {
			System.out.println("Not Done");
		}
		// deserializedGrid = (Grid) in.readObject();
		return deserializedGrid;
	}
}
