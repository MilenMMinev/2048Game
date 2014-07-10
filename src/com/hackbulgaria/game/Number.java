package com.hackbulgaria.game;

public class Number {
	private int value;

	Number() {
		this.value = 0;
	}

	public void merge() {
		this.value *= 2;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
