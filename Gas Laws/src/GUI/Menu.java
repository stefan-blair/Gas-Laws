package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Particles.Container;

public class Menu extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	JFrame frame;
	GUI gui;
	Dalton dalton;
	Graham graham;
	Sandbox sandbox;
	
	Container container, container2;
	
	Random random;
	
	private JRadioButton b1,b2,b3, b4;
	ButtonGroup group;
	
	double[] air;
	
	public Menu(JFrame frame, GUI gui, Dalton dalton, Graham graham, Container container, Container container2, Sandbox sandbox){
		
		random = new Random();
		
		this.frame = frame;
		this.gui = gui;
		this.dalton = dalton;
		this.graham = graham;
		this.sandbox = sandbox;
		
		this.container = container;
		this.container2 = container2;
		
		group = new ButtonGroup();
		
		b1 = new JRadioButton("Boyles/Charles/Lussac's Laws");
		b1.setActionCommand("1");
		b1.addActionListener(this);
		
		b2 = new JRadioButton("Dalton's Law");
		b2.setActionCommand("2");
		b2.addActionListener(this);
		
		b3 = new JRadioButton("Graham's Law");
		b3.setActionCommand("3");
		b3.addActionListener(this);
		
		b4 = new JRadioButton("Sandbox");
		b4.setActionCommand("4");
		b4.addActionListener(this);
		
		group.add(b1);
		group.add(b2);
		group.add(b3);
		group.add(b4);
		
		add(b1);
		add(b2);
		add(b3);
		add(b4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("1")){
			frame.remove(dalton);
			frame.remove(graham);
			frame.remove(sandbox);
			frame.add(gui, BorderLayout.CENTER);
			
			container.setResize(true);
			container2.setResize(true);
			
			container.setHeat(0);
			container2.setHeat(0);
			
			container.setText1("Gas tank Initial");
			container2.setText2("Gas tank Final");
			
			container.setShow(true);
			container2.setShow(true);
			
			container.setSf(15);
			container2.setSf(15);
			
			container.setAmount(20);
			container2.setAmount(20);

			container.reSetColor();
			container2.reSetColor();
			
			container.setWidth(300);
			container.setHeight(300);
			container2.setWidth(300);
			container2.setHeight(300);
			
			container.setX(0);
			container.setY(0);
			container2.setX(0);
			container2.setY(300);
			
			container.newParticles();
			container2.newParticles();

			
		}
		if(e.getActionCommand().equals("2")){
			frame.remove(gui);
			frame.remove(graham);
			frame.remove(sandbox);
			frame.add(dalton, BorderLayout.CENTER);
			
			container.setResize(true);
			container2.setResize(true);
			
			container.setShow(true);
			container2.setShow(false);
			
			container.setHeat(0);
			container2.setHeat(0);
			
			container.setText1("     Partial Pressures");
			container2.setText2("Uniform Gas");
			
			container.setSf(20);
			
			container.setAmount(49);
			
			container.newParticles();
			
			container.setWidth(400);
			container.setHeight(600);
			container2.setWidth(0);
			container2.setHeight(0);
			
			container.setX(0);
			container.setY(0);
			container2.setX(0);
			container2.setY(300);
			
			air = new double[3];
			air[0] = 2;
			air[1] = 4;
			air[2] = 6;

			dalton.setParticles(air, 12);
			
			System.out.println("2");
		}
		if(e.getActionCommand().equals("3")){

			frame.remove(gui);
			frame.remove(dalton);
			frame.remove(sandbox);
			frame.add(graham, BorderLayout.CENTER);
			
			container.setResize(true);
			container2.setResize(true);
			
			container.setShow(true);
			container2.setShow(true);

			container.setHeat(0);
			container2.setHeat(0);
			
			container.setText1("Gas 1");
			container2.setText2("Gas 2");
			
			container.setSf(17);
			container2.setSf(9);
		
			container.setHeight(600);
			container.setWidth(150);
			
			container2.setHeight(600);
			container2.setWidth(150);
			
			container.setY(0);
			container.setX(0);
			
			container2.setY(0);
			container2.setX(150);
			
			container.setAmount(50);
			container.setVelocity(1);
			container.newParticles();
			
			container2.setAmount(50);
			container2.setVelocity(1);
			container2.newParticles();

			container.setColor(0,50, 255,150,100);
			container2.setColor(0,50, 150,255,100);
			for(int i = 0; i < 50; i++){
				container.pX(i, random.nextInt(100));
				container.pY(i, random.nextInt(100) + 450);
				container.pDY(i, -1);			
				
				container2.pX(i, random.nextInt(100) + 150);
				container2.pY(i, random.nextInt(100) + 450);
				container2.pDY(i, -.2);	
			}
			
		}
		if(e.getActionCommand().equals("4")){
			frame.remove(gui);
			frame.remove(dalton);
			frame.remove(graham);
			frame.add(sandbox);
			
			container.setResize(true);
			container2.setResize(true);
			
			sandbox.setVariables();
			
			container.setText1("Sandbox");
			container2.setShow(false);
			
			container.setHeat(190/2);
			container2.setHeat(0);
			
			container.setSf(17);
		
			container.setHeight(300);
			container.setWidth(300);

			container.setY(150);
			container.setX(50);
			
			container.setAmount(50);
			container.setVelocity(1);
			container.newParticles();
		}
		frame.validate();
		frame.repaint();
	}
	

}
