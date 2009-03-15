
import static java.lang.Math.pow;

public class Ball {

	String name;
	String adjective;

	SimpleVector motion;
	SimpleCoordinates location;

	double mass;
	double radius;

	String colour;

	boolean still;

	public Ball() {
		name = "Number 1";
		adjective = "generic";
		colour = "7";			//white
		motion = new SimpleVector(0,0);
		location = new SimpleCoordinates(0,0);
		mass = 1.5;
		radius = 1/3;
		still = true;
	}

	public Ball(String n, int clr, String a, double m, double r, double x, double y, double vx, double vy) {
		this(n, String.format("%d", clr), a, m, r, x, y, vx, vy);
	}


	public Ball(String n, String clr, String a, double m, double r, double x, double y, double vx, double vy) {
		name = n;

		adjective = a;

		colour = clr;

		mass = m;
		radius = r;

		location = new SimpleCoordinates(x,y);

		motion = new SimpleVector(vx,vy);

	}

	public void set(double h, double v) {
		
		location.set(h,v);
	}

	public void roll(double x, double y) {
		motion.set(x,y);
		this.still=false;

	}

	public void stop() {
		motion.set(0,0);
		this.still = true;
	}

	public boolean isTouching(Ball other) {
		 /*  use pythagoras */

		double distance = pow(
				pow(location.x() - other.location.x(), 2) +
				pow(location.y() - other.location.y(), 2),
					0.5);

		if ( distance < radius + other.radius ) 
			return true;

		return false;
	}

	public String report() {
		

		return String.format(

"---------------------------------------------------------------\n" + 
"Ball: %s\n" + 
" %s ball near (%.3f, %.3f).\n" + 
" Diameter %.3f, mass %.3f, heading [%.3f, %.3f]\n" + 
" Still ball: %b\n" +
"---------------------------------------------------------------\n",
		//add in date/time into this report
		//

		this.name,
		this.adjective,
		this.location.x(),
		this.location.y(),
		this.radius * 2,
		this.mass,
		this.motion.vx(),
		this.motion.vy(),
		this.still
		);

	}


}
