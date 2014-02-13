import java.awt.Color;

import javax.swing.JFrame;


public class Runner {

	static double Me = (5.9736 * Math.pow(10, 24));
	static double Mm = (7.3477 * Math.pow(10, 22));
	
	static double Re = 6378140;
	static double Rm = 1737100;
	
	static double Dem = 384303000;
	
	public static void main(String[] args) throws InterruptedException {
		JFrame f = new JFrame();
		f.setBounds(50, 50, 1350, 750);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Space space = new Space(0, (long)(Dem * 2.3) / f.getWidth());
		space.setBackground(Color.BLACK);

		Rock earth = new Rock(new Vector(0, 0), Re, Me, Color.GREEN.darker().darker());
		space.addObject(earth);
		
		//Rock moon = new Rock(new Vector(Dem, 0), Rm, Mm, Color.GRAY);
		//moon.addVelocity(new Vector(1023, Math.PI / 2));
		//space.addObject(moon);
		
		Rock moon2 = new Rock(new Vector(Dem, 2*Math.PI - .566), Rm, Mm, Color.GRAY);
		moon2.addVelocity(new Vector(1023, moon2.getPosition().getAngle() + Math.PI / 2));
		space.addObject(moon2);
		
		/*
		Ship apollo9 = new Ship(new Vector(Re, 3.5 * 10000, true), 100, 1000, Color.RED);
		apollo9.addVelocity(new Vector(7901 + 3243.5, 0));
		space.addObject(apollo9);
		
		Ship apollo10 = new Ship(new Vector(Re, 2.5 * 10000, true), 100, 1000, Color.ORANGE);
		apollo10.addVelocity(new Vector(7901 + 3243.5, 0));
		space.addObject(apollo10);
		
		Ship apollo11 = new Ship(new Vector(Re, 1.5 * 10000, true), 100, 1000, Color.YELLOW);
		apollo11.addVelocity(new Vector(7901 + 3243.5, 0));
		space.addObject(apollo11);
		*/
		
		Ship apollo12 = new Ship(new Vector(Re, 0), 100, 1000, Color.GREEN);
		apollo12.addVelocity(new Vector(7901 + 3243.5, 0));
		space.addObject(apollo12);
		
		/*
		Ship apollo13 = new Ship(new Vector(Re, 1.5 * -10000, true), 100, 1000, Color.BLUE);
		apollo13.addVelocity(new Vector(7901 + 3243.5, 0));
		space.addObject(apollo13);
		
		Ship apollo14 = new Ship(new Vector(Re, 2.5 * -10000, true), 100, 1000, Color.CYAN);
		apollo14.addVelocity(new Vector(7901 + 3243.5, 0));
		space.addObject(apollo14);
		
		Ship apollo15 = new Ship(new Vector(Re, 3.5 * -10000, true), 100, 1000, Color.PINK);
		apollo15.addVelocity(new Vector(7901 + 3243.5, 0));
		space.addObject(apollo15);
		*/
		
		/*
		Ship ship1 = new Ship(new Vector(6600 * 1000, 0), 100, 1000, Color.ORANGE);
		ship1.addVelocity(new Vector(7.8*1000, Math.PI / 2));
		space.addObject(ship1);
		
		Ship ship2 = new Ship(new Vector(42000 * 1000, 0), 100, 1000, Color.CYAN);
		ship2.addVelocity(new Vector(3.1 * 1000, Math.PI / 2));
		space.addObject(ship2);
		
		Ship ship3 = new Ship(new Vector(16000 * 1000, 0), 100, 1000, Color.PINK);
		ship3.addVelocity(new Vector(3.5 * 1000, Math.PI / 2));
		space.addObject(ship3);
		
		Ship sskejin = new Ship(new Vector(66666 * 1000, 0), 100, 1000, Color.RED);
		sskejin.addVelocity(new Vector(1.3 * 1000, Math.PI / 2));
		space.addObject(sskejin);
		
		Ship sssam = new Ship(new Vector(69666 * 1000, 0), 100, 1000, Color.RED.darker().darker().darker());
		sssam.addVelocity(new Vector(1.3 * 1000, Math.PI / 2));
		space.addObject(sssam);
		*/
		
		f.add(space);
		f.setVisible(true);
		
		Thread.sleep(500);
		space.start();
		
	}
	
	public static class Rock extends SpaceObject {

		public Rock(Vector position, double radius, double mass, Color color) {
			super(position, radius, mass, color);
			setVelocityScaleFactor(2000);
			setAccelerationScaleFactor(500000000);
		}

	}
	
	public static class Ship extends SpaceObject {

		int t = 0;
		
		public Ship(Vector position, double radius, double mass, Color color) {
			super(position, radius, mass, color);
			setVelocityScaleFactor(2000);
			setAccelerationScaleFactor(750000);
		}

	}
}
