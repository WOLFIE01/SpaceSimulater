import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Space extends DoubleBufferedCanvas {

	ArrayList<SpaceObject> objects = new ArrayList<SpaceObject>();

	double scale;

	double G = 6.67428 * Math.pow(10, -11);

	int t = 0;
	
	File f = new File("data.txt");
	FileWriter fw;

	public Space(int fps, double scale) {
		super(fps);
		this.scale = scale;
		try {
			fw = new FileWriter(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Space(double scale){
		this(45, scale);
	}

	public void addObject(SpaceObject object) {
		object.setScale(scale);
		objects.add(object);
	}

	@Override
	void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(this.getWidth() / 6, this.getHeight()/2);
		for(SpaceObject object : objects){
			object.drawSelf(g2);
			object.drawVelocity(g2);
			object.drawAcceleration(g2);
		}
	}

	@Override
	protected void updateVars() {
		for(SpaceObject object : objects){
			object.setAcceleration(new Vector(0, 0));
			for(SpaceObject gravitator : objects){
				Vector distance = object.getDistanceVector(gravitator);
				if(distance.getLength() > 10){
					double M = gravitator.getMass();
					double m = object.getMass();
					double force = G*M*m / Math.pow(distance.getLength(), 2);

					double angle = distance.getAngle();
					double forceX = force * Math.cos(angle);
					double forceY = force * Math.sin(angle);
					double accelerationX = (double) (forceX / m);
					double accelerationY = (double) (forceY / m);

					Vector acceleration = new Vector(accelerationX, accelerationY, true);

					object.addAcceleration(acceleration);
				}
			}

			object.tick();
		}

		if(t%60 == 0){
			SpaceObject earth = objects.get(0);
			SpaceObject ship = objects.get(2);

			double distToEarth = ship.getPosition().subtractVector(earth.getPosition()).getLength();

			try {
				fw.append("time " + t + "\tdist to earth:  " + distToEarth + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		t++;
	}

}