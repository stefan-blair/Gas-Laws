package Particles;

import java.awt.Color;
import java.awt.Graphics;

public class Particle {

	private float x, y, dx, dy;
	private int radius;
	private boolean visible;
	private String color;
	
	int r, gr, b;
	
	public Particle(int x, int y, String color, boolean visible){
		this.x = x;
		this.y = y;
		dx = (float) 1;
		dy = (float) 1;
		radius = 20;
		this.visible = visible;
	
		this.color = color;
		
		r = 0;
		gr = 0;
		b = 0;
	}
	
	public void tick(){
		x += dx;
		y += dy;
	}
	
	public void paint(Graphics g){
		if(color.equals("green"))g.setColor(Color.green);
		else if(color.equals("orange"))g.setColor(Color.orange);
		
		else if(color.equals("random"))g.setColor(new Color(r,gr,b));
		
		if(visible)g.fillOval((int)x, (int)y, radius, radius);
	}
	
	//Getters and Setters
	public double getDx() {return dx;}
	public void setDx(float dx) {this.dx = dx;}
	public double getDy() {return dy;}
	public void setDy(float dy) {this.dy = dy;}
	public float getX() {return x;}
	public void setX(float x) {this.x = x;}
	public float getY() {return y;}
	public void setY(float y) {this.y = y;}
	public void setRadius(int radius){this.radius = radius;}
	public void setVisible(boolean visible){this.visible = visible;}
	public float getRadius(){return this.radius;}
	public void setColor(String color){this.color = color;}
	public void intColor(int r, int g, int b){
		this.color = "random";
		this.r = r;
		this.gr = g;
		this.b = b;
	}
}
