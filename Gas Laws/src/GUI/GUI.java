package GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Numbers.Calculator;
import Particles.Container;

public class GUI extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	JButton convert;
	JTextField initial1 = new JTextField("     Initial Variable 1     ");
	JTextField initial2 = new JTextField("     Initial Variable 2     ");
	JTextField final1 = new JTextField("     Final Variable     ");
	JLabel final2 = new JLabel("     Final Variable 2     ");
	JTextField desiredUnit = new JTextField("     Enter your desired unit     ");
	String exactUnit;
	JLabel notice;
	
	Container container;
	Container container2;
	
	Calculator calculator;

	public GUI(Container container, Container container2, Calculator calculator){
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.container = container;
		this.container2 = container2;
		this.calculator = calculator;
		
		notice = new JLabel("<html>Enter pressure, volume or tempurature variables<br>The top two text fields are the initial conditions<br>The bottom text field is the given final condition.<br>The second final condition will be solved for.</html>");
		
		final2.setFont(new Font("Arial", Font.PLAIN, 25));
		
		gbc.insets = new Insets(25,25,25,25);
		button();

		gbc.gridy = 0;
		add(notice, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(initial1,gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(initial2,gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(final1,gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		//add(desiredUnit,gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(convert, gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		add(final2, gbc);
	}
	
	public void button(){
		
		convert = new JButton("Solve");	
		
		convert.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				container.setResize(true);
				container2.setResize(true);
				
				if(calculator.getUnit(initial1.getText()).equals("temp") && initial1.getText().contains("C"))initial1.setText(String.valueOf(calculator.converter((float) calculator.getDouble(initial1.getText()), "C", "K"))+"K");
				
				if(calculator.getUnit(initial2.getText()).equals("temp") && initial2.getText().contains("C"))initial2.setText(String.valueOf(calculator.converter((float) calculator.getDouble(initial2.getText()), "C", "K"))+"K");
				
				if(calculator.getUnit(final1.getText()).equals("temp") && final1.getText().contains("C"))final1.setText(String.valueOf(calculator.converter((float) calculator.getDouble(final1.getText()), "C", "K"))+"K");
				
				
				if(calculator.getLaw(initial1.getText(), initial2.getText()).equals("boyle")){
					final2.setText(calculator.boyles(initial1.getText(), initial2.getText(), final1.getText(),desiredUnit.getText() ,container, container2));
				}
				else if(calculator.getLaw(initial1.getText(), initial2.getText()).equals("charles")){
					final2.setText(calculator.charles(initial1.getText(), initial2.getText(), final1.getText(), desiredUnit.getText(),container, container2));
				}
				else if(calculator.getLaw(initial1.getText(), initial2.getText()).equals("lussac")){
					final2.setText(calculator.lussac(initial1.getText(), initial2.getText(), final1.getText(), desiredUnit.getText(),container, container2));
				}
				
				if(!desiredUnit.getText().contains("Enter your desired unit")){
					exactUnit = final2.getText().replace(".", "");
					System.out.println(calculator.getDouble(final2.getText())+", "+(exactUnit.replaceAll("\\d", "")).replaceAll(" ", "")+", "+desiredUnit.getText());
					final2.setText(String.valueOf(calculator.converter((float) calculator.getDouble(final2.getText()), (exactUnit.replaceAll("\\d", "")).replaceAll(" ", ""), desiredUnit.getText()))+desiredUnit.getText());;
				}
				
				initial1.setText("     "+initial1.getText().replaceAll(" ", "")+"     ");
				initial2.setText("     "+initial2.getText().replaceAll(" ", "")+"     ");
				final1.setText("     "+final1.getText().replaceAll(" ", "")+"     ");
				
				if(container.getSize()>container2.getSize()){
					container.setResize(false);
					container.setSize(container2.getSize());
				}
				if(container.getSize()<container2.getSize()){
					container2.setResize(false);
					container2.setSize(container.getSize());
				}
			}
		});
	}
	//Getters and Setters
}