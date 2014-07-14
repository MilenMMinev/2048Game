package com.hackbulgaria.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jline.Terminal;

public class KeyReader {
	private final BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in));

	public int getPlayerMove() throws IOException {
		Terminal terminal = Terminal.setupTerminal();
		int c = terminal.readVirtualKey(System.in);
		return c;
		// return reader.readLine().trim().charAt(0);char
	}
}
