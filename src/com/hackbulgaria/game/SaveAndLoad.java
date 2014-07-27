package com.hackbulgaria.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveAndLoad {
	private final static File file = new File("grid.ser");

	public static void save(Grid grid) throws FileNotFoundException,
			IOException {
		file.createNewFile();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				file));
		out.writeObject(grid);
		out.flush();
		out.close();
	}

	public static Grid load() throws IOException, ClassNotFoundException {
		Grid deserializedGrid = null;
		@SuppressWarnings("resource")
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		Object obj = in.readObject();
		if (obj instanceof Grid) {
			deserializedGrid = (Grid) obj;
		} else {
			return null;
		}
		// deserializedGrid = (Grid) in.readObject();
		return deserializedGrid;
	}
}
