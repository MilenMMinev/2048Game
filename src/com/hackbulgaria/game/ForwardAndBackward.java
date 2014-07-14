package com.hackbulgaria.game;

import java.util.Stack;

public class ForwardAndBackward {
	private static final Stack<Grid> redo = new Stack<>();
	private static final Stack<Grid> undo = new Stack<>();

	public static Grid undo() {
		redo.add(undo.peek());
		return undo.pop();
	}

	// used when you use arrows and when you use forward
	public static void saveMove(Grid grid) {
		undo.push(grid);
	}

	public static Grid redo() {
		return redo.pop();
	}

}
