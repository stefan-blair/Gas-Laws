package Main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.Dalton;
import GUI.GUI;
import GUI.Graham;
import GUI.Menu;
import GUI.Sandbox;
import Numbers.Calculator;
import Particles.Container;

public class Main {
	
	public static void main(String[] args){
		Container container = new Container(0,300,300, "green");
		Container container2 = new Container(300,300,300, "orange");
		Calculator calculator = new Calculator();
		JFrame frame = new JFrame();
		Screen screen = new Screen(container, container2);
		GUI gui = new GUI(container,container2, calculator);
		Dalton dalton = new Dalton(container, container2, calculator);
		Graham graham = new Graham(container, container2, calculator, 1, .2);
		Sandbox sandbox = new Sandbox(container, calculator);
		Menu menu = new Menu(frame, gui, dalton, graham, container, container2, sandbox);
		
		JPanel title = new JPanel();
		JLabel Stefan = new JLabel("Created by Stefan Blair");
		title.add(Stefan);
		
		frame.setTitle("Gas Laws");
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(screen, BorderLayout.WEST);
		frame.add(gui, BorderLayout.CENTER);
		frame.add(menu, BorderLayout.NORTH);
		frame.add(title, BorderLayout.SOUTH);
		frame.pack();
		frame.setSize(900,700);
		frame.setLocationRelativeTo(null);
		System.out.println(calculator.getUnit("2atm"));
	}
	
	
}
