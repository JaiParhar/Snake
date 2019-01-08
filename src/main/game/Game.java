package main.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.scene.input.KeyCode;

public class Game extends JPanel{

	public static final int TILE_SIZE = 10;
	long gameSpeed = 150;
	
	boolean exit;
	Snake s;
	JFrame frame;
	
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		
		g.setColor(Color.WHITE);
		for(int i = 0; i < s.cells.size(); i++) {
			g.fillRect(s.cells.get(i).x*TILE_SIZE, s.cells.get(i).y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
		}
		
		
	}
	
	public void update() {
		s.update();
		if(s.getHead().x < 0 || s.getHead().y < 0 ||
		s.getHead().x > getWidth()/TILE_SIZE || s.getHead().y > getHeight()/TILE_SIZE) {
			reset();
		} 
	}
	
	public void input(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP: s.input(0, -1); break;
		case KeyEvent.VK_DOWN: s.input(0, 1); break;
		case KeyEvent.VK_LEFT: s.input(-1, 0); break;
		case KeyEvent.VK_RIGHT: s.input(1, 0); break;
		default: break;
		}
	}
	
	public void init() {
		frame = new JFrame("SnakeAI");
		frame.setSize(600, 400);
		frame.add(this);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		s = new Snake();
		s.init(5, 5);
		s.input(0, 1);
		
		frame.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) {}
			public void keyPressed(KeyEvent e) { input(e); }
		});
		exit = false;
	}
	
	public void loop() {
		while(!exit) {
			update();
			repaint();
			
			try {
				Thread.sleep(gameSpeed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void reset() {
		s.reset(5, 5);
	}
	
	public void run() {
		init();
		loop();
	}
	
    public void paintComponent(Graphics g) {
		render(g);
	}
	
}
