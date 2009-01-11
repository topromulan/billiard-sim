
import static java.lang.Math.abs;


public class Felt extends SimplePlane {

	public Felt(int width, int height) {

		super(width, height); //init the plane

	}


	public void rub(Rack rack) {

                for(int i=0; i < rack.set.length; i ++ )
                {

                        double vx = rack.set[i].motion.vx();
                        double vy = rack.set[i].motion.vy();

                        double x = rack.set[i].location.x();
                        double y = rack.set[i].location.y();

                        //add a little fluxion of the vector to the location
                        //

			//Newton currently values the fluxion at:
			float fluxion=0.1f;

                        x += vx*fluxion;
                        y += vy*fluxion;

			//does the location violate a border?
			//

			if( ! checkx(rack.set[i].location.x()))
			{
				System.err.format(
					"%s ball went out sideways.\n", 
					rack.set[i].adjective
					);

				// reverse the flux!!
				vx *= -1;			

				// send 'er back
				x += 2*vx*fluxion;


			}

			if( ! checky(rack.set[i].location.y()))
			{
				System.err.format(
					"%s ball went out up or down.\n", 
					rack.set[i].adjective
					);

				// reverse the flux!!
				vy *= -1;			

				// send 'er back
				y += 2*vy*fluxion;

			}


			//note: nothing slowing them down
			// at this point..
			//

			rack.set[i].motion.setvx(vx);
			rack.set[i].motion.setvy(vy);

			rack.set[i].location.setx(x);
			rack.set[i].location.sety(y);

			if( (abs(vx) < 0.05) && (abs(vy) < 0.05) )
			{
				rack.set[i].stop();
			}


                }

	}

	public boolean checkx(double x) {
			// check if x is negative or exceeds bounds
			//

			if ( 
				x > 0 	&&
				x < this.width()
			) 
				return true;

			return false;
	}

	public boolean checky(double y) {
			// check if y is negative or exceeds bounds
			//

			if ( 
				y > 0 	&&
				y < this.height()
			) 
				return true;

			return false;
	}







}





