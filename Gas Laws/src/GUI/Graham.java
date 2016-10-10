package GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Numbers.Calculator;
import Numbers.periodicTable;
import Particles.Container;

public class Graham extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Container container, container2;
	Calculator calculator;
	periodicTable table;
	
	JTextField rate1, mass1, rate2, mass2;
	JButton button, reset;
	JLabel answer1, answer2, notice;
	
	double dy1, dy2, size1, size2;
	
	String sub;
	String[] names;
	double totalMass;
	int amount;
	
	Random random;
	
	public Graham(Container container, Container container2, Calculator calculator, double dy1, double dy2){
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(25,5,25,5);
		
		this.container = container;
		this.container2 = container2;
		this.calculator = calculator;
		
		table = new periodicTable();
		
		sub = "";
		totalMass = 0;
		amount = 0;
		
		this.dy1 = dy1;
		this.dy2 = dy2;
		size1 = 15;
		size2 = 15;
		
		random = new Random();
		
		rate1 = new JTextField("          Rate 1          ");
		mass1 = new JTextField("          Mass 1          ");
		
		rate2 = new JTextField("          Rate 2          ");
		mass2 = new JTextField("          Mass 2          ");
		
		button = new JButton("Solve");
		reset = new JButton("Reset Simulation");
		answer1 = new JLabel("Answer 1");
		answer2 = new JLabel("Answer 2");
		
		answer1.setFont(new Font("Arial", Font.ITALIC, 20));
		answer2.setFont(new Font("Arial", Font.ITALIC, 20));
		
		notice = new JLabel("<html>Enter variables.<br>NOTE units are not necessary.</html>");
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		add(notice, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(rate1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(mass1, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		add(rate2, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		add(mass2, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(button, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(answer1, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		add(answer2, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(reset, gbc);
		
		button();
	}
	
	public void button(){
		
		reset.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent arg0) {setParticles();}});
	
		button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				if(rate1.getText().replaceAll(" ", "").length()==0)rate1.setText("          Rate 1          ");
				if(mass1.getText().replaceAll(" ", "").length()==0)mass1.setText("          Mass 1          ");
				if(rate2.getText().replaceAll(" ", "").length()==0)rate2.setText("          Rate 2          ");
				if(mass2.getText().replaceAll(" ", "").length()==0)mass1.setText("          Mass 2          ");
				
				if(rate1.getText().contains("Rate") && rate2.getText().contains("Rate")){
					if(calculator.getDouble(mass1.getText())>=calculator.getDouble(mass2.getText())){
						rate1.setText("     1     ");
						System.out.println(1);
					}
					if(calculator.getDouble(mass1.getText())<calculator.getDouble(mass2.getText())){
						rate2.setText("     1     ");
						System.out.println(2);
					}
				}
				
				answer1.setText("Answer 1");
				answer2.setText("Answer 2");
				
				if(!mass1.getText().contains("Mass 1") && mass2.getText().contains("Mass 2")){
				
					answer2.setText(String.valueOf(getMass(getMolarMass(mass1.getText()), calculator.getDouble(rate1.getText()), calculator.getDouble(rate2.getText()))));
					
					dy1 = calculator.getDouble(rate1.getText());
					dy2 = calculator.getDouble(rate2.getText());
					
				}
				else if(!mass2.getText().contains("Mass 2") && mass1.getText().contains("Mass 1")){
					
					answer1.setText(String.valueOf(getMass(getMolarMass(mass2.getText()), calculator.getDouble(rate2.getText()), calculator.getDouble(rate1.getText()))));	

					dy1 = calculator.getDouble(rate1.getText());
					dy2 = calculator.getDouble(rate2.getText());
					
				}
				
				else if(!rate1.getText().contains("Rate 1") && rate2.getText().contains("Rate 2")){
					
					answer2.setText(String.valueOf(getRate(getMolarMass(mass1.getText()), getMolarMass(mass2.getText()), Double.valueOf(rate1.getText()))));
					
					dy1 = Double.valueOf(rate1.getText());
					dy2 = Double.valueOf(answer2.getText());
			
				}
				
				else if(!rate2.getText().contains("Rate 2") && rate1.getText().contains("Rate 1")){
					
					answer1.setText(String.valueOf(getRate(getMolarMass(mass2.getText()), getMolarMass(mass1.getText()), Double.valueOf(rate2.getText()))));
					
					dy2 = Double.valueOf(rate2.getText());
					dy1 = Double.valueOf(answer1.getText());
			
				}
				

				while(dy1<1){
					dy1*=2;
					dy2*=2;
					//System.out.println(dy1+", "+dy2);
				}
				while(dy2<1){
					dy1*=2;
					dy2*=2;
					//System.out.println(dy1+", "+dy2);
				}
				while(dy1>25){
					dy1/=2;
					dy2/=2;
				}
				while(dy2>25){
					dy1/=2;
					dy2/=2;
				}
				
				
				setParticles();
				
				rate1.setText("          "+rate1.getText().replaceAll(" ", "")+"          ");
				mass1.setText("          "+mass1.getText().replaceAll(" ", "")+"          ");
				
				rate2.setText("          "+rate2.getText().replaceAll(" ", "")+"          ");
				mass2.setText("          "+mass2.getText().replaceAll(" ", "")+"          ");
				
				totalMass = 0;
				amount = 0;
				
			}
			
		});
		
	}
	
	public void setParticles(){

		container.setSf(9);
		container2.setSf(9);
		if(dy2 > dy1)container2.setSf((int)(12+(dy2/dy1)));
		if(dy1 > dy2)container.setSf((int)(12+(dy1/dy2)));
		//System.out.println((int)(12+(dy1/dy2)));
		for(int i = 0; i < 50; i++){
			container.pX(i, random.nextInt(100));
			container.pY(i, random.nextInt(100) + 480);
			container.pDY(i, -dy1);			
			
			container2.pX(i, random.nextInt(100) + 150);
			container2.pY(i, random.nextInt(100) + 480);
			container2.pDY(i, -dy2);	
		}
	}
	
	public double getMolarMass(String substance){
		
		if(calculator.getUnit(substance).equals("volume") || calculator.getUnit(substance).equals("temp")|| calculator.getUnit(substance).equals("pressure"))return calculator.getDouble(substance);
		
		else if(table.getElement(substance) == 0){
			sub = substance.replaceAll("(?!^)([A-Z])", ",$1");
			//System.out.println(sub);
			names = sub.split(",");
			
			for(int i = 0; i<names.length; i++){
				amount = 0;
				if(names[i].matches(".*\\d.*")){
					amount = (int)calculator.getDouble(names[i]);
					if(amount > 0)totalMass += (table.getElement(names[i].replaceAll("\\d", ""))) * amount;
				}
				else totalMass += table.getElement(names[i]);
			}
			return totalMass;
		}else return table.getElement(substance);
		
	}
	
	public double getRate(double mass1, double mass2, double rate){
		
		//System.out.println(rate +"*"+mass1+"/"+mass2);
		
		return rate * Math.sqrt(mass1/mass2);

//		2.76e-4
//		He
//		
//		O2
//		
//		9.76e-5
		
	}
	
	public double getMass(double mass, double rate1, double rate2){
		
		return mass / (Math.pow(rate2, 2)/Math.pow(rate1, 2));
		
	}
}
