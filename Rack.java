
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.atan;
import static java.lang.Math.PI;


public class Rack {

	public Ball[] set;

	public Rack(Ball[] s) {

		set = s;

		
	}

	/* 
	 * check for collisions in the rack
	 *
	 * invoke the conservation of momentum
	 *
	 * this is probably not the best place for this
	 *
	 */
	public void collisions() {

		//the area is somewhat larger than necessary.. really only
		// doing set.length choose 2 operations.. but seems like 
		// the easiest way to keep track
		
		boolean alreadyChecked[][] = new boolean[set.length][set.length];

		for ( int i=0; i < set.length - 1; i++ )
		{
			for ( int j=i+1; j < set.length; j++ )
			{
					if ( alreadyChecked[i][j] )
						continue;

					if ( set[i].isTouching(set[j]) )
					{
						if ( ! set[i].still && ! set[j].still )
							System.err.format("! %s ball touching %s ball\n", set[i].adjective, set[j].adjective);
						////Just stopping them for now
						//set[i].stop();
						//set[j].stop();
						collide(set[i], set[j]);

						alreadyChecked[i][j] = true;
					}


			}

		}



	}

	private void collide(Ball b1, Ball b2)
	{
		double	origvx1 = b1.motion.vx(),
			origvy1 = b1.motion.vy(),
			origvx2 = b2.motion.vx(),
			origvy2 = b2.motion.vy();

		double mag1 = pow( pow(origvx1, 2) + pow(origvy1, 2), 0.5);
		double mag2 = pow( pow(origvx2, 2) + pow(origvy2, 2), 0.5);

		//create a unit vector pointing from b1 to b2
		double 	diffx = b2.location.x() - b1.location.x(),
			diffy = b2.location.y() - b1.location.y(),
			diffmag = pow( pow(diffx, 2) + pow(diffy, 2), 0.5);

		System.err.format("%s ball hitting %s ball\n", b1.adjective, b2.adjective);

		SimpleVector unitv = new SimpleVector( diffx / diffmag, diffy / diffmag );

		//SimpleVector testv = new SimpleVector(diffx, diffy);

		//(i): how much of the unit vector to add to b2 depends
		//      on how differently angled it is from the
		//      vector of b1. if exactly the same, 100%. 
		//      if nearly orthogonal, nearly nothing.
		//
		//     I'll try just accounting for that linearly
		//      from zero to pi = zero to 100% different.
		//
		//  so i want like the abs(arctan(y/x))

		//first for that unit vector i made

		double	collisionAngle = atan(unitv.vy() / unitv.vx()),
			ball1VectorAngle = atan(b1.motion.vy() / b1.motion.vx());

		double diff = collisionAngle - ball1VectorAngle;

		if ( diff > PI ) {
			System.err.println("Subtracting PI from diff.");

			diff -= PI;
		}

		System.err.format("cA: %f b1v: %f. diff: %f\n", 	
			collisionAngle, ball1VectorAngle,
			diff);

		//Hypothesis: find the absolute value of the difference
		// 
		// Multiply the unit vector times a percentage of ball 1's mass and velocity
		//  from 100 to zero percent depending on this scale:
		//
		//                     pi/2 = 0%
		//                      |
		//                      |
		//                      |
		//                      |
		//pi = 100% ____________|____________ zero = 100%
		// 
		//
		// calculate bounceVector from unit vector
		//  and apply + to ball 1 and - to ball 2

		double magBounce;

		if ( diff >= (PI/2) ) {

			System.err.format("Case 1.\n");
			magBounce = 1.0 * ( (PI - diff) / (PI / 2 ) ) ;

		}
		

		else /* diff < PI/2 */ {
			System.err.format("Case 2.\n");
			magBounce = 1.0 * ( (PI / 2 - diff) / ( PI / 2 ) ) ;

		}


		

		SimpleVector bounceVector = new SimpleVector(unitv.vx()*magBounce, unitv.vy()*magBounce);

		b2.motion.add(bounceVector);
		b1.motion.subtract(bounceVector);

		System.err.print(b1.report());
		System.err.print(b2.report());

		System.err.format("magBounce is %f. Bounce vector was %f, %f\n", magBounce, bounceVector.vx(), bounceVector.vy());

	}


			


	public boolean hasMotion() {


		//return true if any ball in the rack is unstill
		//
		// still the table for vectors below .. a low level

		for(int i=0; i < set.length; i++)
		{
			if (! set[i].still)
				return true;
			
		}

		return false;


	}

	public String report() {

		StringBuffer news = new StringBuffer(
"=============================RACK=============================\n" 
		);

		for(int i=0; i < set.length; i++)
		{
			news.append(set[i].report());
		}


		news.append(
"=============================++++=============================\n"
		);

		return news.toString();

	}


}





