
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.atan;


public class Rack {

	public Ball[] set;

	public Rack(Ball[] s) {

		set = s;

		//calculate the largest ball and thereby
		// the size of quadrants for collision check

		double biggestBall=0;

		for ( int i=0; i < set.length; i++ )
			if ( set[i].radius > biggestBall )
				biggestBall = set[i].radius;
		
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
		//hypo-theory:
		//
		// calculate the magnitude of each ball's vector
		//  and multiply that times a unit vector pointing
		//  from the ball's center to the other ball's center
		//  then add that to the other ball's center

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

		SimpleVector unitv = new SimpleVector( diffx / diffmag, diffy / diffmag );

		SimpleVector testv = new SimpleVector(diffx, diffy);

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

		double	angle1 = atan(unitv.vy() / unitv.vx()),
			angle2 = atan(b1.motion.vy() / b1.motion.vx());

		System.err.format("%f & %f. diff: %f\n", angle1, angle2, angle1 - angle2);


		/*double 	tx1 = 
			ty1 =
			tx2 = 
			ty2 = 
		
		b1.motion.setvx(
		b1.motion.setvy(
		b2.motion.setvx(
		b2.motion.setvy(*/
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





