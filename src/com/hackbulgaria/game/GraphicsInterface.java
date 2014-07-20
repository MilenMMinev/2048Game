package com.hackbulgaria.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GraphicsInterface extends JPanel implements ActionListener, KeyListener{
	
	Timer t = new Timer(50, this);
	private Grid grid = new Grid();

	
	public GraphicsInterface(){
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	

	
	@Override
	public void paintComponent(Graphics g){
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	g2.setColor(Color.darkGray);
	g2.setFont(new Font("Name", Font.BOLD, 50));
		Image img = null;
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
				if (Grid.youWon(grid)) {
					g2.drawString("You Won", 250, 80);
				}
				if (Grid.youLose(grid)) {
					g2.drawString("You Lose", 250, 80);
				} else {
					try {
						img = ImageIO.read(new File(
								"/home/rosen/git/2048Game/images/"
										+ grid.getCellValue(j, i) + ".png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g2.drawImage(img, i * 120 + 200, j * 120 + 200, 100, 100,
							null);
				}
				// g2.drawString(Integer.toString(grid.getCellValue(j, i)),
				// i * 120 + 200, j * 120 + 200);
		}
	}
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();
		
		Grid before = grid.clone();
		switch(code){
		case KeyEvent.VK_LEFT:
			ForwardAndBackward.saveMove(grid.clone());
			grid.moveLeft();
			if (grid.areDifferent(before)) {
				grid.addRandom();
			}
			break;
			
		case KeyEvent.VK_DOWN:
			ForwardAndBackward.saveMove(grid.clone());
			grid.moveDown();
			if (grid.areDifferent(before)) {
				grid.addRandom();
			}
			break;
			
		case KeyEvent.VK_RIGHT:
			ForwardAndBackward.saveMove(grid.clone());
			grid.moveRight();
			if (grid.areDifferent(before)) {
				grid.addRandom();
			}
			break;
			
		case KeyEvent.VK_UP:
			ForwardAndBackward.saveMove(grid.clone());
			grid.moveUp();
			if (grid.areDifferent(before)) {
				grid.addRandom();
			}
			break;
			
		case KeyEvent.VK_R:// r
			grid = ForwardAndBackward.redo();
			break;
			
		case KeyEvent.VK_Z:// z
			grid = ForwardAndBackward.undo();
		case KeyEvent.VK_S:// s
			try {
				SaveAndLoad.save(grid);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		case KeyEvent.VK_L:// l
			try {
				grid = SaveAndLoad.load();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		default:
		}
		}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}