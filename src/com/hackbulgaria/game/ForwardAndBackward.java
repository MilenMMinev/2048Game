package com.hackbulgaria.game;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ForwardAndBackward {
	private final Queue<Grid> forWard = new PriorityQueue<>();
	private final Stack<Grid> backWard = new Stack<>();

	public Grid back() {
		forWard.add(backWard.peek());
		return backWard.pop();
	}

	public void ImportMove(Grid grid) {
		backWard.push(grid);
	}

	public Grid forward() {
		return forWard.poll();
	}

}
