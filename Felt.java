
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
                        double y = rack.set[i].location.x();

                        //add a little fluxion of the vector to the location
                        //

			//Newton currently values the fluxion at:
			float fluxion=0.1f;

                        x = x + vx*fluxion;
                        y = y + vy*fluxion;

			if(check(rack.set[i].location));
			//does the location violate a border?
			//

			//if so, reflect the vector.

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

	public boolean check(SimpleCoordinates xy) {
			// check if xy is negative or exceeds bounds
			//

			if ( 
				xy.x() > 0 	&&
				xy.y() > 0 	&&
				xy.x() < this.width() &&
				xy.y() < this.height()
			) 

			{

				return false;


			}

			return true;




		}






}





