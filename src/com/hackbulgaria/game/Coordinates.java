package com.hackbulgaria.game;

import java.awt.Point;

public class Coordinates {
	private int x;
	private int y;
	
	public Coordinates(){
		this.x = -1;
		this.y = -1;
	}
	
	public Coordinates (int _x, int _y){
		
		x=  _x;
		y = _y;
	}
	
	public boolean compareTo(Coordinates other)
	{
		return this.x == other.x && this.y == other.y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}

}
