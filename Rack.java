
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
	 */
	public void collisions() {

		//Quick bubble scan

		for ( int i=0; i < set.length - 1; i++ )
		{
			for ( int j=i+1; j < set.length; j++ )
			{
					if ( set[i].isTouching(set[j]) )
					{
						if ( ! set[i].still && ! set[j].still )
							System.err.format("! %s ball touching %s ball\n", set[i].adjective, set[j].adjective);
						//Just stopping them for now
						set[i].stop();
						set[j].stop();
					}


			}

		}



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





