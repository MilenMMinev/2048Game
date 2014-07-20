package com.hackbulgaria.game;

import javax.swing.JFrame;



public class Visualiser{
	
	public void show(){
			JFrame f = new JFrame();
			GraphicsInterface s = new GraphicsInterface();
			f.add(s);
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setSize(800, 800);
		}
	
	
};