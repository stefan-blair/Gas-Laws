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

import Numbers.Calculator;
import Particles.Container;

@SuppressWarnings("serial")
public class Sandbox extends JPanel implements ActionListener{
	
	Container container;
	Calculator calculator;
	
	JButton PlusP, MinusP, PlusV, MinusV, PlusT, MinusT;
	JLabel Volume, Pressure, Tempurature;
	float maxVolume, minVolume,  deltaV, deltaP, deltaT, currentV, currentP, currentT, prevV, prevP, prevT;
	
	public Sandbox(Container container, Calculator calculator){
		
		//Layout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();	
		gbc.insets = new Insets(25,5,25,5);
		//Container/Calculator
		this.container = container;
		this.calculator = calculator;
		//Variables
		setVariables();
		//Graphics
		PlusV = new JButton(" + ");
		MinusV = new JButton(" - ");
		PlusT = new JButton(" + ");
		MinusT = new JButton(" - ");
		Volume = new JLabel("  "+String.valueOf(currentV)+" L"+"   ");
		Pressure = new JLabel("   "+String.valueOf(currentP)+" atm"+"   ");
		Tempurature = new JLabel("   "+String.valueOf(currentT)+" K"+"   ");
		//Style
		PlusV.setFont(new Font("Arial", Font.ITALIC, 30));
		PlusT.setFont(new Font("Arial", Font.ITALIC, 30));
		MinusV.setFont(new Font("Arial", Font.ITALIC, 30));
		MinusT.setFont(new Font("Arial", Font.ITALIC, 30));
		Pressure.setFont(new Font("Arial", Font.ITALIC, 40));
		Volume.setFont(new Font("Arial", Font.PLAIN, 30));
		Tempurature.setFont(new Font("Arial", Font.PLAIN, 30));
		//add
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(Pressure, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(MinusV, gbc);
		gbc.gridx = 1;
		add(Volume,gbc);
		gbc.gridx = 2;
		add(PlusV, gbc);
		gbc.gridy = 3;
		gbc.gridx = 0;
		add(MinusT, gbc);
		gbc.gridx = 1;
		add(Tempurature, gbc);
		gbc.gridx = 2;
		add(PlusT, gbc);
		//Action
		PlusV.addActionListener(this);
		PlusV.setActionCommand("1");
		MinusV.addActionListener(this);
		MinusV.setActionCommand("2");
		
		PlusT.addActionListener(this);
		PlusT.setActionCommand("3");
		MinusT.addActionListener(this);
		MinusT.setActionCommand("4");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		prevP = currentP;
		prevV = currentV;
		prevT = currentT;
		
		if(e.getActionCommand().equals("1") && currentT > 64){
			if(currentP > .4 && calculator.getDouble(Volume.getText())<=500){
				currentP = prevP-deltaP;
				currentV = getV(prevP, prevV, prevT, currentP, currentT);
			}
		}
		
		if(e.getActionCommand().equals("2")){
			if(currentP > .2 && currentV > 80 && currentT > 64){
				currentP = prevP+deltaP;
				currentV = getV(prevP, prevV, prevT, currentP, currentT);
				}
			}
		
		if(e.getActionCommand().equals("3")){
			if(1 == 1){
				currentP = prevP+deltaP;
				currentT = getT(prevP, prevV, prevT, currentP, currentV);
			}
		}
		
		if(e.getActionCommand().equals("4")){
			if(currentP > .2){
				currentP = prevP-deltaP;
				currentT = getT(prevP, prevV, prevT, currentP, currentV);
		
			}
		}
		
		//currentP = getP(prevP, prevV, prevT, currentV, currentT);
		
		Volume.setText("  "+String.valueOf(Math.round(currentV * 100.0)/100.0)+" L"+"  ");
		Pressure.setText("  "+String.valueOf(Math.round(currentP * 100.0)/100.0)+" atm"+"  ");
		Tempurature.setText("   "+String.valueOf((int)currentT)+" K"+"   ");
		container.setWidth((int) currentV);
		container.setHeight((int) currentV);
		container.setX(200-(container.getWidth()/2));
		container.setY(300-(container.getHeight()/2));
		container.newParticles();
		container.setHeat((int)currentT/2);
		container.setVelocity(currentP);
		
	}
	
	public float getT(float p1,float v1,float t1,float p2,float v2){
		
		return (float)((p2*v2*t1)/(p1*v1));
	}
	
	public float getV(float p1, float v1, float t1, float p2, float t2){
		
		return ((t2/t1)*p1*v1)/p2;
		
	}
	
	public float getP(float p1, float v1, float t1, float v2, float t2){
		
		return ((t2/t1)*p1*v1)/v2;
		
	}
	
	public void setVariables(){
		maxVolume = 400;
		minVolume = 80;
		deltaV = 40;
		deltaP = (float) 0.2;
		deltaT = 10;
		currentV = 300;
		currentP = 1;
		currentT = 160;
		prevV = 300;
		prevP = 1;
		prevT = 100;
	}
	
}	
//PlusT.addActionListener(new ActionListener(){
//
//	public void actionPerformed(ActionEvent arg0) {
//		if(currentV<maxVolume && currentP>=.2){
//			//System.out.println(currentP+" + "+deltaP);
//			prevP = currentP;
//			prevV = currentV;
//			prevT = currentT;
//			currentP += deltaP;
//			currentV = prevV+deltaV;
//			currentT = prevT+deltaT;
//			if(currentP<1)deltaV = 40;
//			System.out.println(container.getVelocity()+", "+currentV+","+currentP);
