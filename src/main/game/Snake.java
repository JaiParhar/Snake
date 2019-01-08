package main.game;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {

	int xvel, yvel;
	int snakeSize = 4;
	ArrayList<Point> cells = new ArrayList<Point>();
	
	void init(int x, int y) {
		cells.add(0, new Point(x, y));
		xvel = 0;
		yvel = 1;
	}
	
	void newCell() {
		snakeSize++;
	}
	
	void input(int xv, int yv) {
		if(Math.abs(xvel) != Math.abs(xv)) { xvel = xv; }
		if(Math.abs(yvel) != Math.abs(yv)) { yvel = yv; }
	}
	
	void update() {
		cells.add(0, new Point(cells.get(0).x+xvel, cells.get(0).y+yvel));
		
		if(cells.size() > snakeSize) {
			cells.remove(cells.size()-1);
		}
	}
	
	void reset(int x, int y) {
		cells.clear();
		init(x, y);
	}
	
	Point getHead() {
		return cells.get(0);
	}
	
	
}
