import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


public class Vector {

	private double length;
	private double angle; // 0 is directly right
	
	private float strokeSize = 1.5f;
	
	public Vector(double length, double angle) {
		this.length = length;
		this.angle = angle;
	}
	
	public Vector(double x, double y, boolean xy){
		double length = Math.sqrt(x*x + y*y);
		double angle = Math.atan(Math.abs(y/x));
		if(x < 0 && y > 0)
			angle = Math.PI - angle;
		if(x < 0 && y < 0)
			angle += Math.PI;
		if(x > 0 && y < 0)
			angle = 2*Math.PI - angle;
		if(x == 0 && y > 0)
			angle = Math.PI / 2;
		if(x == 0 && y < 0)
			angle = 3 * Math.PI / 2;
		if(y == 0 && x > 0)
			angle = 0;
		if(y == 0 && x < 0)
			angle = Math.PI;
		
		this.length = length;
		this.angle = angle;
	}

	public double getLength() {
		return length;
	}

	public double getAngle() {
		return angle;
	}

	public double getXComponent(){
		return length * Math.cos(angle);
	}
	
	public double getYComponent(){
		return length * Math.sin(angle);
	}
	
	public Vector addVector(Vector vector){
		double xComponent = this.getXComponent() + vector.getXComponent();
		double yComponent = this.getYComponent() + vector.getYComponent();
		return new Vector(xComponent, yComponent, true);
	}
	
	public Vector subtractVector(Vector vector){
		double xComponent = this.getXComponent() - vector.getXComponent();
		double yComponent = this.getYComponent() - vector.getYComponent();
		return new Vector(xComponent, yComponent, true);
	}
	
	public Vector scale(double factor){
		return new Vector(length * factor, angle);
	}
	
	public void drawSelf(Graphics2D g, int x, int y, double scale, Color color){
		int x2 = x + (int) (this.getXComponent()/scale);
		int y2 = y + (int) (this.getYComponent()/scale);
		g.setColor(color);
		g.setStroke(new BasicStroke(strokeSize));
		g.drawLine(x, -y, x2, -y2);
	}
	
}
