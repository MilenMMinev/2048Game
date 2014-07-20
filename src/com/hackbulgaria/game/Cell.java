package com.hackbulgaria.game;

import java.io.Serializable;

public class Cell implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Cell [value=" + value + "]";
	}

	private int value;

	Cell() {
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
