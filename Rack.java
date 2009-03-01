
import static java.lang.Math.abs;
import static java.lang.Math.pow;


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
		
		b1.motion.setvx(origvx1 - unitv.vx() * mag2);
		b1.motion.setvy(origvy1 - unitv.vy() * mag2);
		b2.motion.setvx(origvx2 + unitv.vx() * mag1);
		b2.motion.setvy(origvy2 + unitv.vx() * mag1);
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





