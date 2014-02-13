import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashSet;

public abstract class SpaceObject {

	protected double scale = 1;
	protected double objectScale = 1;
	
	protected double velocityScaleFactor = 1;
	protected double accelerationScaleFactor = 1;
	
	protected Vector position;
	
	protected Vector velocity;
	
	protected Vector acceleration;
	
	protected double radius;
	protected double mass;
	
	protected Color color;
	
	protected HashSet<Coordinate> coordinates = new HashSet<Coordinate>();

	public SpaceObject(Vector position, double radius, double mass, Color color) {
		this.position = position;
		this.radius = radius;
		this.mass = mass;
		this.color = color;
		velocity = new Vector(0, 0);
		acceleration = new Vector(0, 0);
	}

	public void setScale(double scale) {
		this.scale = scale;
	}
	
	public void setObjectScale(double objectScale){
		this.objectScale = objectScale;
	}
	
	public void setVelocityScaleFactor(double velocityScaleFactor) {
		this.velocityScaleFactor = velocityScaleFactor;
	}

	public void setAccelerationScaleFactor(double accelerationScaleFactor) {
		this.accelerationScaleFactor = accelerationScaleFactor;
	}

	public double getX() {
		return position.getXComponent();
	}

	public double getY() {
		return position.getYComponent();
	}
	
	public Vector getPosition() {
		return position;
	}

	public double getRadius() {
		return radius;
	}

	public double getMass() {
		return mass;
	}

	public void addVelocity(Vector adjustment){
		velocity = velocity.addVector(adjustment);
	}
	
	public void setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
	}
	
	public void addAcceleration(Vector adjustment) {
		acceleration = acceleration.addVector(adjustment);
	}
	
	public Vector getDistanceVector(SpaceObject object){
		return object.getPosition().subtractVector(this.getPosition());
	}

	public void drawSelf(Graphics2D g) {
		int minSize = 3;
		int r = (int)(radius/scale * objectScale);
		if(r < minSize)
			r = minSize;

		int x = (int)(this.getX()/scale);
		int y = (int)(this.getY()/scale);
		y *= -1;
		x -= r/2;
		y -= r/2;
		
		coordinates.add(new Coordinate(x, y));
		g.setColor(color.darker().darker());
		for(Coordinate coordinate : coordinates){
			g.fillOval(coordinate.getX(), coordinate.getY(), r-1, r-1);
		}
		
		g.setColor(color);
		g.fillOval(x, y, r, r);
	}
	
	public void drawVelocity(Graphics2D g){
		int x = (int) (this.getX()/scale);
		int y = (int) (this.getY()/scale);
		velocity.scale(velocityScaleFactor).drawSelf(g, x, y, scale, Color.RED);
	}
	
	public void drawAcceleration(Graphics2D g){
		int x = (int) (position.addVector(velocity.scale(velocityScaleFactor)).getXComponent()/scale);
		int y = (int) (position.addVector(velocity.scale(velocityScaleFactor)).getYComponent()/scale);
		acceleration.scale(accelerationScaleFactor).drawSelf(g, x, y, scale, Color.YELLOW);
	}
	
	public void tick() {
		velocity = velocity.addVector(acceleration);
		position = position.addVector(velocity);
	}
	
	public static class Coordinate {
		private final int x, y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() { return x; }
		public int getY() { return y; }

		@Override
		public int hashCode() { return Integer.toString(x).hashCode() ^ Integer.toString(y).hashCode(); }

		@Override
		public boolean equals(Object o) {
			if (o == null) return false;
			if (!(o instanceof Coordinate)) return false;
			Coordinate ocoord = (Coordinate) o;
			return x == ocoord.getX() && y == ocoord.getY();
		}

	}
	
}
