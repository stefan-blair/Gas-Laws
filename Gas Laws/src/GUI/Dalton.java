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

public class Dalton extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Container container;
	Container container2;
	
	int r, g, b;
	
	String[] list, names;
	double[] pressures;
	int[] percents;
	int currentPercent;
	String numbers;
	String sub;
	String percentAnswer, pressureAnswer;
	double answerDouble;
	double doubleX;
	double totalX;
	double lowSpeed;
	double amount, totalMass, totalMolMass;
	boolean x, p, w;
	
	Random random;
	
	JLabel message;
	JTextField iV;
	JTextField fV;
	JButton button;
	JLabel answer;
	
	Calculator calculator;
	periodicTable table;
	
	public Dalton(Container container, Container container2, Calculator calculator){
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(25,25,25,25);
		
		random = new Random();
		
		this.container = container;
		this.container2 = container2;
		
		this.calculator = calculator;
		
		message = new JLabel("<html>Enter X for unknown variable<br>INCLUDE UNITS<br>Percent sign if necessary</html>");
		iV = new JTextField("                    Enter partial pressures                     ");
		fV = new JTextField("                    Enter total pressure if given                    ");
		button = new JButton("Solve for unknown variable");
		answer = new JLabel("Answer");
		
		answer.setFont(new Font("Arial", Font.ITALIC, 40));
		
		button();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(message, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(iV, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(fV, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(button, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(answer, gbc);
	}
	
	public void button(){
		
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(iV.getText().contains("%")){
					p = true;
					answer.setText(String.valueOf(getPercents(strip(iV.getText(), calculator.getDouble(fV.getText())),calculator.getDouble(fV.getText()))));
					setParticles(strip(iV.getText(), calculator.getDouble(fV.getText())), calculator.getDouble(fV.getText()));
					iV.setText("                    "+iV.getText().replaceAll(" ","")+"                    ");
					fV.setText("                    "+fV.getText().replaceAll(" ","")+"                    ");
					p = false;
				}else if(iV.getText().contains("X") || iV.getText().contains("x")){
					x = true;
					answer.setText("X = "+String.valueOf(getAnswer(strip(iV.getText(), calculator.getDouble(fV.getText())),calculator.getDouble(fV.getText())))+" atm");
					setParticles(strip(iV.getText(), calculator.getDouble(fV.getText())), calculator.getDouble(fV.getText()));
					iV.setText("                    "+iV.getText().replaceAll(" ","")+"                    ");
					fV.setText("                    "+fV.getText().replaceAll(" ","")+"                    ");
					x = false;
				}else{
					answer.setText(String.valueOf(getAnswer(strip(iV.getText(), 1),1))+" atm");
					setParticles(strip(iV.getText(), 1), getAnswer(strip(iV.getText(), 1), 1));
					iV.setText("                    "+iV.getText().replaceAll(" ","")+"                    ");
				}
							
			}
			
		});
	}
	
	public double[] strip(String numbers, double answer){
		
		this.totalX = 0;
		
		this.numbers = numbers;
		list = numbers.split(",");
		pressures = new double[list.length];

		if(p){			
			for(int i = 0; i < list.length; i++){
				pressures[i] = (calculator.getDouble(list[i])/100)*answer;
			}
			
		}
		
		if(!p){
		for(int i = 0; i<list.length; i++){
			if(!list[i].contains("x") && !list[i].contains("X")){
				System.out.println(list[i]);
				pressures[i] = calculator.getDouble(list[i]);
				totalX += pressures[i];
			}
		}
		
		if(x){
			for(int i = 0; i<pressures.length; i++){
				if(pressures[i] == 0){
					pressures[i] = answer - totalX;
					doubleX = answer - totalX;
				}
			}
		}
	}
		return pressures;
	}

	@SuppressWarnings("null")
	public double getAnswer(double[] pressures, double answer){
		
		answerDouble = 0;
		
			for(int i = 0; i<pressures.length; i++){
		
			answerDouble += pressures[i];
		}

		if(!x && !p)return answerDouble;
		if(x && !p)return doubleX;
		return (Double) null;
	}
	
	public String getPercents(double[] pressures, double answer){
		percentAnswer = "";
		for(int i = 0; i < pressures.length; i++){
			percentAnswer += String.valueOf(pressures[i]);
			if(!(i == pressures.length-1))percentAnswer += " atm, ";
		}
		return percentAnswer;
	}
	
	public String getPressures(double[] pressures, double answer){
		pressureAnswer = "";
		for(int i = 0; i<pressures.length; i++){
			pressureAnswer += String.valueOf(pressures[i]);
			if(!(i == pressures.length-1))pressureAnswer += ", ";
		}
		
		return pressureAnswer;
	}
	
	public void setParticles(double[] pressures, double answer){
		container.newParticles();
		percents = new int[pressures.length];
		currentPercent = 0;
		
		lowSpeed = pressures[1];
		
		container.setColor(0, 50, 0, 0, 0);
		
		r = 0;
		g = 0;
		b = 0;
		
		for(int i = 0; i<pressures.length; i++){
			percents[i] = (int)(50*(pressures[i]/answer));
			if(pressures[i] < lowSpeed)lowSpeed = pressures[i];
		}
		for(int i = 0; i<percents.length; i++){
			r = random.nextInt(255);
			g = random.nextInt(255);
			b = random.nextInt(255);
			if(lowSpeed < pressures[i])container.setDaltonSpeed(currentPercent, percents[i]+currentPercent, pressures[i]/lowSpeed);
			else if(lowSpeed == pressures[i])container.setDaltonSpeed(currentPercent, percents[i]+currentPercent, 1);
			container.setColor(currentPercent, percents[i]+currentPercent, r, g, b);
			currentPercent+=percents[i];
			System.out.println(pressures[i]/lowSpeed);
		}
		
	}
	
}
