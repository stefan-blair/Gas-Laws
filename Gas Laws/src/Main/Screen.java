package Main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Particles.Container;

public class Screen extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 400;
	private static final int HEIGHT = 600;

	private Thread thread;
	private boolean running = false;
	
	private int FPS = 5;
	private int targetTime = FPS/1000;
	
	Container container;
	Container container2;
	
	public Screen(Container container, Container container2){
		this.container = container;
		this.container2 = container2;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		Start();
	}
	
	public void Start(){
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run(){
		long start, elapsed, wait;
				
		while(running){
			start = System.nanoTime();
			tick();
			repaint();
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			
			if(wait <= 0){
				wait = 5;
			}
			
			try{
				Thread.sleep(wait);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void tick(){
		
		container.tick();
		container2.tick();

		//System.out.println(Math.abs(container.getVelocity()));
//		System.out.println(container2.getVelocity());

	}
	
	public void paint(Graphics g){
		super.paint(g);
		container.paint(g, 1);
		container2.paint(g, 2);
	}
	
}
