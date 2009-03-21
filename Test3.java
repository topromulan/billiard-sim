

public class Test3 {

	public static void main(String[] args) {

		//Rack1: Head-on collision..
		Rack Rack1 = new Rack (new Ball[] { 
			
			//  Ball(n, clr, a, m, r, x, y, vx, vy)
			//
	
			new Ball("White ball", "1;37", "white",
					2.0, 4, 
					50, 150, 30, 0),
			new Ball("Red ball", 31, "red", 
					2.0, 4, 
					450, 150, 0, 0)
		});

		//Rack2: Collision at pi/4
		Rack Rack2 = new Rack (new Ball[] { 
			
			//  Ball(n, clr, a, m, r, x, y, vx, vy)
			//
	
			new Ball("White ball", "1;37", "white",
					2.0, 4, 
					50, 149, 30, 0),
			new Ball("Red ball", 31, "red", 
					2.0, 4, 
					450, 150, 0, 0)
		});

		//Rack3: Glancing collision
		Rack Rack3 = new Rack (new Ball[] { 
			
			//  Ball(n, clr, a, m, r, x, y, vx, vy)
			//
	
			new Ball("White ball", "1;37", "white",
					2.0, 4, 
					50, 148.01, 30, 0),
			new Ball("Red ball", 31, "red", 
					2.0, 4, 
					450, 150, 0, 0)
		});


		Felt Table = new Felt(640, 320); // pool table is domino shaped
						// adjust for screen ratio in
						// higher layer
						//


		//Clear the debug output screen
		System.err.format("%c[2J", 0x1B);


		//Run the tests
		runTable(Table, Rack1);
		runTable(Table, Rack2);
		runTable(Table, Rack3);


	}


		
	/*
	 * runTable():
	 *
	 * Run rack on table until a key is pressed.
	 *
	 * Currently requires setting stty properly
	 *  before starting program
	 */

	static void runTable(Felt table, Rack rack) {

		System.out.println(rack.report());
		
			try {
				java.lang.Thread.sleep(5000);
			} catch (Exception e) {}
		
		System.err.println("New pool hall test.\n----------");
		

		do {

			table.rub(rack); 

			rack.collisions();

			render(table, rack);
			
			//System.err.format("%c[2J", 0x1B);
			//System.err.println(theRack.report());

			try {
				// Is a character waiting?
				if ( System.in.available() > 0 )
				{
					//Flush it and break
					System.in.read();
					break;
				}

				// Arbitrary speed of table..
				java.lang.Thread.sleep(100);

			} catch (Exception e) {/* **** */}

		} while ( rack.hasMotion( ) );


		System.out.println("Test complete.");



	}

	//Well I should classify this somewhere else since it is a fine
	// upstanding text mode billiards table.
	public static void render(Felt felt, Rack rack)
	{
			//Draw a table 80x20
			StringBuffer table = new StringBuffer(
"+------------------------------------------------------------------------------+\n"
			);

			for(int v=1; v<=18; v++)
			{
				table.append("|");

				double yMin, yMax;

				yMin = (felt.height() / 18) * (v-1);
				yMax = (felt.height() / 18) * v;

				for(int h=1; h<=78; h++)
				{
					double xMin, xMax;

					xMin = (felt.width() / 78) * (h-1);
					xMax = (felt.width() / 78) * h;

					boolean foundOne=false;
					String colour="";

					for(int b=0; b < rack.set.length; b++)
					{
						double x=rack.set[b].location.x();
						double y=rack.set[b].location.y();

						if( (x > xMin && x <= xMax) &&
						    (y > yMin && y <= yMax) )
						{
							foundOne=true;
							colour=rack.set[b].colour;
							break;
						}

					}

					if(foundOne)
						table.append(
							String.format("%c[%smO%c[0m", 0x1B, colour, 0x1B)
						);
					else
						table.append(" ");
				}
				table.append("|\n");
			}

			table.append(
"+------------------------------------------------------------------------------+\n"
			);


			//Clear the screen
			System.out.format("%c[2J", 0x1B);

			////Print the report

			System.out.println(table);


	}


}
