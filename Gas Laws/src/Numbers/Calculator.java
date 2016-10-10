package Numbers;

import Particles.Container;


public class Calculator {

	String variable1 = "";
	String variable2 = "";
	
	String law = "";
	String unit = "";
	String exactUnit = "";
	
	double num1 = 0;
	double num2 = 0;
	double num3 = 0;
	double num4 = 0;
	
	float newNum = 0;
	
	String answer = "";
	
	String[] tempUnits = {"C","K", "Celcius","Kelvin","kelvin","celcius","k"};
	String[] pressureUnits = {"atm","mmhg","atmosphers","mmHg","kpa","kPa","torr","Torr"};
	String[] areaUnits = {"meters","inchs","feet","L", "cm3","liters","mL","ml","ft","grams","g/mol","moles","mole"};
	
	
	Container container;
	Container container2;
	
	public Calculator(){
		
	}
	
	public float converter(float x, String y, String z){
		System.out.println(y+", "+z);
		if(y.equals("C") && z.equals("K"))newNum = x+273;
		else if(y.equals("K") && z.equals("C"))newNum = x-273;
		return (float)newNum;
	}
	
	public String getUnit(String x){
		for(int i = 0; i<tempUnits.length;i++)if(x.contains(tempUnits[i]))unit = "temp";
		for(int i = 0; i<pressureUnits.length; i++)if(x.contains(pressureUnits[i]))unit = "pressure";
		for(int i = 0; i<areaUnits.length;i++)if(x.contains(areaUnits[i])){
			unit = "volume";
		}
		
		return unit;
	}
	
	public double getDouble(String x){
		double newDouble = Double.parseDouble(x.replaceAll("[^\\d.]", ""));
		return newDouble;
	}
	
	public String getLaw(String x, String y){
		if(getUnit(x).equals("temp") && getUnit(y).equals("pressure") || getUnit(y).equals("temp") && getUnit(x).equals("pressure"))law = "lussac";
		if(getUnit(x).equals("volume") && getUnit(y).equals("pressure") || getUnit(y).equals("volume") && getUnit(x).equals("pressure"))law = "boyle";
		if(getUnit(x).equals("volume") && getUnit(y).equals("temp") || getUnit(y).equals("volume") && getUnit(x).equals("temp"))law = "charles"; 
		return law;
	}
	//Boyles = p1*v1 = p1*v2
	//Charles = v1/t1 = v2/t2:  num1 = volume, num2 = temp;
	//Lussac = p1/t1 = p2/t2
	public String boyles(String p1, String v1, String x1, String unit,Container container, Container container2){
		this.container = container;
		this.container2 = container2;
		container.setVisible(true);
		container2.setVisible(true);
		container.setHeat(0);
		container2.setHeat(0);
		num1 = getDouble(p1);
		num2 = getDouble(v1);
		num3 = getDouble(x1);
		if(getUnit(x1).equals("pressure")){
			if(getUnit(p1).equals("pressure")){
				v1 = v1.replace(".", "");
				v1.replaceAll("\\d", "");
				num4 = num2*(num1/num3);
				answer = (String.valueOf((float)(num4)))+v1;
				if(num2 > num4){
					container.setVelocity(1);
					container.setWidth(300);
					container.setHeight(300);
					container.setX(0);
					container.setY(0);
					container2.setWidth((int)(((300/num2))*num4));
					container2.setHeight((int)(((300/num2))*num4));
					container2.setX(150-(container2.getWidth()/2));
					container2.setY(450-(container2.getHeight()/2));
					container2.newParticles();
					container2.setVelocity((float)num3/num1);
				}else if(num2 < num4){
					container2.setVelocity(1);
					container2.setWidth(300);
					container2.setHeight(300);
					container2.setX(0);
					container2.setY(300);
					container.setWidth((int)(((300/num4))*num2));
					container.setHeight((int)(((300/num4))*num2));
					container.setX(150-(container.getWidth()/2));
					container.setY(150-(container.getHeight()/2));
					container.newParticles();
					container.setVelocity((float)num1/num3);
			}
			}
			else if(getUnit(p1).equals("volume")){
				p1 = p1.replace(".", "");
				p1.replaceAll("\\d", "");
				num4 = num1*(num2/num3);
				answer = (String.valueOf((float)(num4)))+p1;
				if(num1 > num4){
					container.setVelocity(1);
					container.setWidth(300);
					container.setHeight(300);
					container.setX(0);
					container.setY(0);
					container2.setWidth((int)(((300/num1))*num4));
					container2.setHeight((int)(((300/num1))*num4));
					container2.setX(150-(container2.getWidth()/2));
					container2.setY(450-(container2.getHeight()/2));
					container2.newParticles();
					container2.setVelocity((float)num3/num2);
				}else if(num1 < num4){
					container2.setVelocity(1);
					container2.setWidth(300);
					container2.setHeight(300);
					container2.setX(0);
					container2.setY(300);
					container.setWidth((int)(((300/num4))*num1));
					container.setHeight((int)(((300/num4))*num1));
					container.setX(150-(container.getWidth()/2));
					container.setY(150-(container.getHeight()/2));
					container.newParticles();
					container.setVelocity((float)num2/num3);
			}}
		}
		else if(getUnit(x1).equals("volume")){
			if(getUnit(p1).equals("pressure")){
				p1 = p1.replace(".", "");
				p1.replaceAll("\\d", "");
				num4 = num1*(num2/num3);
				answer = (String.valueOf((float)(num4)))+p1;
				if(num1 > num4){
					container.setVelocity(1);
					container.setWidth(300);
					container.setHeight(300);
					container.setX(0);
					container.setY(300);
					container2.setWidth((int)(((300/num1))*num4));
					container2.setHeight((int)(((300/num1))*num4));
					container2.setX(150-(container2.getWidth()/2));
					container2.setY(450-(container2.getHeight()/2));
					container2.newParticles();
					container2.setVelocity((float)num3/num2);
			}else if(num1 < num4){
				container2.setVelocity(1);
				container2.setWidth(300);
				container2.setHeight(300);
				container2.setX(0);
				container2.setY(300);
				container.setWidth((int)(((300/num4))*num1));
				container.setHeight((int)(((300/num4))*num1));
				container.setX(150-(container.getWidth()/2));
				container.setY(150-(container.getHeight()/2));
				container.newParticles();
				container.setVelocity((float)num2/num3);
			}
				}
			else if(getUnit(p1).equals("volume")){
				v1 = v1.replace(".", "");
				v1.replaceAll("\\d", "");
				num4 = num2*(num1/num3);
				answer = (String.valueOf((float)(num4)))+v1;
				if(num2 > num4){
					container.setVelocity(1);
					container.setWidth(300);
					container.setHeight(300);
					container.setX(0);
					container.setY(0);
					container.newParticles();
					container2.setWidth((int)(((300/num2))*num4));
					container2.setHeight((int)(((300/num2))*num4));
					container2.setX(150-(container2.getWidth()/2));
					container2.setY(450-(container2.getHeight()/2));
					container2.newParticles();
					container2.setVelocity((float)num3/num1);
				}else if(num2 < num4){
					container2.setVelocity(1);
					container2.setWidth(300);
					container2.setHeight(300);
					container2.setX(0);
					container2.setY(300);
					container.setWidth((int)(((300/num4))*num2));
					container.setHeight((int)(((300/num4))*num2));
					container.setX(150-(container.getWidth()/2));
					container.setY(150-(container.getHeight()/2));
					container.newParticles();
					container.setVelocity((float)num1/num3);
			}
			}}
		if(container.getWidth()<=150 && Math.abs(container.getVelocity())>=4){
			container.setVelocity(container.getVelocity()/2);
			container2.setVelocity(container2.getVelocity()/2);
		}
		if(container2.getWidth()<=150 && Math.abs(container2.getVelocity())>=4){
			container.setVelocity(container.getVelocity()/2);
			container2.setVelocity(container2.getVelocity()/2);
		}
		container.newParticles();
		container2.newParticles();
				return answer;
		}
	
	public String charles(String v1, String t1, String x1, String unit,Container container, Container container2){
		this.container = container;
		this.container2 = container2;
		container.setVisible(false);
		container2.setVisible(false);
		num3 = getDouble(x1);
		if(getUnit(v1).equals("temp")){
			num1 = getDouble(v1);
			num2 = getDouble(t1);
		}
		else if(getUnit(v1).equals("volume")){
			num1 = getDouble(t1);
			num2 = getDouble(v1);
		}
		
		if(getUnit(x1).equals("volume")){
			if(getUnit(v1).equals("temp")){
				exactUnit = v1.replace(".", "");
				exactUnit.replaceAll("\\d", "");
			}else if(getUnit(v1).equals("volume")){
				exactUnit = t1.replace(".", "");
				exactUnit.replaceAll("\\d", "");
			}
			num4 = (num3*num1)/num2;
			answer = String.valueOf(num4) + exactUnit;
			if(num3>num2){
				container.setWidth((int)(((300/num3))*num2));
				container.setHeight((int)(((300/num3))*num2));	
				container.setX(150-(container.getWidth()/2));
				container.setY(150-(container.getHeight()/2));
				container2.setWidth(300);
				container2.setHeight(300);
				container2.setX(0);
				container2.setY(300);
				container.setHeat(0);
				if(num4>num1)container2.setHeat((int)(50*(num4/num1)));
				else if(num4<num1)container.setHeat((int)(-50*(num4/num1)));
				container2.setVelocity(1);
				container.setVelocity(1);
			}else if(num2>num3){
				container2.setWidth((int)(((300/num2))*num3));
				container2.setHeight((int)(((300/num2))*num3));	
				container2.setX(150-(container2.getWidth()/2));
				container2.setY(450-(container2.getHeight()/2));
				container.setWidth(300);
				container.setHeight(300);
				container.setX(0);
				container.setY(0);
				container2.setHeat(0);
				if(num4>num1)container.setHeat((int)(50*(num1/num4)));
				else if(num4<num1)container2.setHeat((int)(-50*(num1/num4)));
				container.setVelocity(1);
				container2.setVelocity(1);
			}
		}else if(getUnit(x1).equals("temp")){
			if(getUnit(v1).equals("volume")){
				exactUnit = v1.replace(".", "");
				exactUnit.replaceAll("\\d", "");
			}else if(getUnit(v1).equals("temp")){
				exactUnit = t1.replace(".", "");
				exactUnit.replaceAll("\\d", "");
			}
			num4 = (num3*num2)/num1;
			answer = String.valueOf(num4) + exactUnit;
			if(num4>num2){
				container.setWidth((int)(((300/num4))*num2));
				container.setHeight((int)(((300/num4))*num2));	
				container.setX(150-(container.getWidth()/2));
				container.setY(150-(container.getHeight()/2));
				container2.setWidth(300);
				container2.setHeight(300);
				container2.setX(0);
				container2.setY(300);
				container.setHeat(0);
				if(num3>num1)container2.setHeat((int)(50*(num3/num1)));
				else if(num3<num1)container.setHeat((int)(-50*(num3/num1)));
				container2.setVelocity(1);
				container.setVelocity(1);
			}else if(num2>num4){
				container2.setWidth((int)(((300/num2))*num4));
				container2.setHeight((int)(((300/num2))*num4));	
				container2.setX(150-(container2.getWidth()/2));
				container2.setY(450-(container2.getHeight()/2));
				container.setWidth(300);
				container.setHeight(300);
				container.setX(0);
				container.setY(0);
				container2.setHeat(0);
				if(num3>num1)container.setHeat((int)(50*(num1/num3)));
				else if(num3<num1)container2.setHeat((int)(-50*(num1/num3)));
				container.setVelocity(1);
				container2.setVelocity(1);
			}
		}


		return answer;
	}
	
	public String lussac(String p1, String t1, String x1, String unit,Container container, Container container2){
		this.container = container;
		this.container2 = container2;
		container.setVisible(true);
		container2.setVisible(true);
		num3 = getDouble(x1);
		if(getUnit(p1).equals("temp")){
			num1 = getDouble(p1);
			num2 = getDouble(t1);
		}
		else if(getUnit(p1).equals("pressure")){
			num1 = getDouble(t1);
			num2 = getDouble(p1);
		}
		
		if(getUnit(x1).equals("pressure")){
			if(getUnit(p1).equals("temp")){
				exactUnit = p1.replace(".", "");
				exactUnit.replaceAll("\\d", "");
			}else if(getUnit(p1).equals("pressure")){
				exactUnit = t1.replace(".", "");
				exactUnit.replaceAll("\\d", "");
			}
			num4 = (num3*num1)/num2;
			answer = String.valueOf(num4) + exactUnit;

			container.setVelocity(1);
			container.setWidth(300);
			container.setHeight(300);
			container.setX(0);
			container.setY(0);
			container.newParticles();
			container2.newParticles();
			if(num3>num2)container2.setVelocity((num3/num2)+1);
			else if(num3<num2)container2.setVelocity((num3/num2)-.3);
			if(num4>num1)container2.setHeat((int)(50*(num1/num4)));
			else if(num4<num1)container2.setHeat((int)(-50*(num4/num1)));
			
			container2.setWidth(300);
			container2.setHeight(300);
			container2.setX(0);
			container2.setY(300);
			
		}else if(getUnit(x1).equals("temp")){
			if(getUnit(p1).equals("pressure")){
				exactUnit = p1.replace(".", "");
				exactUnit.replaceAll("\\d", "");
			}else if(getUnit(p1).equals("temp")){
				exactUnit = t1.replace(".", "");
				exactUnit.replaceAll("\\d", "");
			}
			num4 = (num2*num3)/num1;
			answer = String.valueOf(num4) + exactUnit;
			container.setVelocity(1);
			container.setWidth(300);
			container.setHeight(300);
			container.setX(0);
			container.setY(0);
			container.newParticles();
			container2.newParticles();
			if(num4>num2)container2.setVelocity((num4/num2)+1);
			else if(num4<num2)container2.setVelocity((num4/num2));
			else if(num4<num2 && num4/num2 > .3 )container2.setVelocity((num4/num2)-.3);
			if(num1<num3)container2.setHeat((int)(50*(num1/num3)));
			else if(num1>num3)container2.setHeat((int)(-50*(num3/num1)));
			container2.setWidth(300);
			container2.setHeight(300);
			container2.setX(0);
			container2.setY(300);
		}
		return answer;
	}
	
}
