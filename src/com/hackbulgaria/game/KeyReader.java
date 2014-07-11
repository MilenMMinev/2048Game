package com.hackbulgaria.game;

import java.io.IOException;

import jline.Terminal;

public class KeyReader {
	public int getPlayerMove() throws IOException{
		Terminal terminal = Terminal.setupTerminal();
		int c = terminal.readVirtualKey(System.in);
		return c;
		
		
	}
}
