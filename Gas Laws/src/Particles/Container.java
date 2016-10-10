package Particles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Container {

	@SuppressWarnings("unused")
	private int width, height,x,y, currentWidth, currentHeight, cX, cY;
	private int heat = 0;
	private Particle[] particles;
	private Random random;
	private int amount;
	private int sF = 15;
	
	private String text1, text2;
	
	private boolean visible,moving, show, resize;
	
	private String color;
	
	public Container(int y,int width, int height, String color){
		this.width = width;
		this.height = height;
		this.color = color;
		
		text1 = "Gas tank Initial";
		text2 = "Gas tank Final";
		
		currentHeight = height;
		currentWidth = width;
		visible = true;
		moving = true;
		show = true;
		resize = true;
		random = new Random();
		amount = 20;
		x = 0;
		cX = 0;
		this.y = y;
		cY = y;
		particles = new Particle[50];
		for(int i = 0; i<particles.length; i++){
			if(i<=amount)particles[i] = new Particle(random.nextInt(width-20)+x,random.nextInt(height-20)+y, color,true);
			if(i>amount) particles[i] = new Particle(random.nextInt(width-20)+x,random.nextInt(height-20)+y, color,false);
		}
	}
	
	public void tick(){
		
		for(int i = 0; i<particles.length; i++){
			//particles[i].setVisible(false);
			if(moving)particles[i].tick();
			if(particles[i].getX() >= x+width-particles[i].getRadius()){
				particles[i].setDx((float) (particles[i].getDx()*-1));
				particles[i].setVisible(false);
				if(random.nextInt(2)==1)particles[i].setDy((float) (particles[i].getDy()*-1));
			}
			if(particles[i].getX() <= x){
				particles[i].setDx((float) (particles[i].getDx()*-1));
				particles[i].setVisible(false);
				if(random.nextInt(2)==1)particles[i].setDy((float) (particles[i].getDy()*-1));
			}
			if(particles[i].getY() >= y+height-particles[i].getRadius()){
				particles[i].setDy((float) (particles[i].getDy()*-1));
				particles[i].setVisible(false);
				if(random.nextInt(2)==1)particles[i].setDx((float) (particles[i].getDx()*-1));
			}
			if(particles[i].getY() <= y){
				particles[i].setDy((float) (particles[i].getDy()*-1));
				particles[i].setVisible(false);
				if(random.nextInt(2)==1)particles[i].setDx((float) (particles[i].getDx()*-1));
			}else if(visible && particles[i].getX()>x && particles[i].getX()<x+width && particles[i].getY()>y && particles[i].getY()<y+height &&i<=amount){
				particles[i].setVisible(true);
			}
			
			if(resize)particles[i].setRadius((int)width/sF);
		}
	}
	
	public void paint(Graphics g, int s){
		if(show){
			if(heat>0){
				if(heat>255)heat = 255;
				g.setColor(new Color(heat,0,0));
			}
			if(heat<0){
				if(-1*heat>255)heat = -255;
				g.setColor(new Color(0,0,-1*heat));
			}
			if(heat==0)g.setColor(Color.black);
			g.fillRect(x, y, width, height);
			g.setColor(Color.gray);
			g.drawRect(x, y, width, height);
			g.setFont(new Font("Arial", Font.ITALIC, 38));
			if(s == 1)g.drawString(text1, x + width/2-text1.length()*9, y + height/2);
			else if(s == 2)g.drawString(text2, x + width/2-text2.length()*9, y + height/2);
			for(int i = 0; i<particles.length;i++){
				particles[i].paint(g);
			}}
	}
	
	public void newParticles(){
		for(int i = 0; i<particles.length; i++){
			if(i<=amount)particles[i] = new Particle(random.nextInt(width-20)+x,random.nextInt(height-20)+y, color,true);
			else particles[i] = new Particle(random.nextInt(width-20)+x,random.nextInt(height-20)+y, color,false);
		}
	}
	
	//Getters and Setters
	public int getWidth() {return width;}
	public void setWidth(int width) {this.width = width;}
	public int getHeight() {return height;}
	public void setHeight(int height) {this.height = height;}
	public void setX(int x){this.x = x;}
	public void setY(int y){this.y = y;}
	public void setHeat(int heat){this.heat = heat;}
	public void setVelocity(double velocity){
		for(int i = 0; i<particles.length; i++){
			particles[i].setDx((float)velocity);
			particles[i].setDy((float)velocity);
		}
	}
	public void setVisible(boolean visible){

		this.visible = visible;
		for(int i = 0; i<particles.length; i++){
			particles[i].setVisible(visible);
		}
	}
	public void setShow(boolean show){this.show = show;}
	public boolean getShow(){return show;}
	public float getVelocity(){return (float) particles[1].getDx();}
	public void setAmount(int amount){this.amount = amount;}
	public void setColor(int x, int y, int r, int g, int b){
		
		for(int i = x; i<y; i++){
			particles[i].intColor(r, g, b);
		}
	}
	public void setDaltonSpeed(int x, int y, double z){
		
		for(int i = x; i<y; i++){
			particles[i].setDx((float)z);
			particles[i].setDy((float)z);
		}
	}
	public void reSetColor(){
		for(int i = 0; i < particles.length; i++){
			particles[i].setColor(color);
		}
	}
	public void setSf(int sF){this.sF = sF;}	
	public void pX(int x, int y){particles[x].setX(y);}
	public void pY(int x, int y){particles[x].setY(y);}
	public void pDX(int x, double y){particles[x].setDx((float) y);}
	public void pDY(int x, double d){particles[x].setDy((float) d);}
	public void setText1(String text1){this.text1 = text1;}
	public void setText2(String text2){this.text2 = text2;}
	public double getSize(){return width/sF;}
	public void setSize(double newRadius){
		for(int i = 0; i < particles.length; i++){
			particles[i].setRadius((int) newRadius);
		}
	}
	public void setResize(boolean resize){this.resize = resize;}
	
}


