
import static java.lang.Math.abs;


public class Rack {

	public Ball[] set;

	private int sectorSize;

	private int ballSectors[][];

	public Rack(Ball[] s) {

		set = s;

		//calculate the largest ball and thereby
		// the size of quadrants for collision check

		double biggestBall=0;

		for ( int i=0; i < set.length; i++ )
			if ( set[i].radius > biggestBall )
				biggestBall = set[i].radius;
		
		//just a rough guess of a reasonable sectorSize for now.. ****
		sectorSize = (int) ((biggestBall + 10.0) / 10.0) * 10;

		//just allocate this in the constructor so
		// we don't have to reallocate it every collision
		// check

		ballSectors = new int[set.length][2];
		
	}

	/* 
	 * check for collisions in the rack
	 *
	 * invoke the conservation of momentum
	 *
	 */
	public void collisions() {

		//Recalculate what sector x and y each ball is in
		for ( int i=0; i < set.length; i++ )
		{
			ballSectors[i][0] = (int) set[i].location.x() / sectorSize;
			ballSectors[i][1] = (int) set[i].location.y() / sectorSize;
			
		}

		//Quick bubble scan through the sectors and do a more
		// comprehensive check if any are within a sector of 
		// one another

		for ( int i=0; i < set.length - 1; i++ )
		{
			for ( int j=i+1; j < set.length; j++ )
			{
				if ( 	abs(
						ballSectors[i][0] -
						ballSectors[j][0]
						) <= 1
					&&
				 	abs(
						ballSectors[i][1] -
						ballSectors[j][1]
						) <= 1
					)
					collisionHandler(i,j);


			}

		}



	}

	/*
	 * checks if balls A and B are actually colliding
	 *  and handle if so
	 */

	private void collisionHandler(int a, int b)
	{

		//just assume so and slow them down for now..
		set[a].motion.set(
			set[a].motion.vx() * 0.5,
			set[a].motion.vy() * 0.5
			);
		set[b].motion.set(
			set[a].motion.vx() * 0.5,
			set[a].motion.vy() * 0.5
			);

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

		news.append(String.format(
			"The rack sector size is: %d\n",
			sectorSize
			));
		
		for(int i=0; i < set.length; i++)
		{
			news.append(String.format(
				"The %s ball is in sector(%d, %d)\n",
				set[i].adjective,
				ballSectors[i][0],
				ballSectors[i][1]
			));
		}


		news.append(
"=============================++++=============================\n"
		);

		return news.toString();

	}


}





