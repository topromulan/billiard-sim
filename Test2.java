

public class Test2 {

	public static void main(String[] args) {

		Rack theRack = new Rack (new Ball[] { 
			
			//  Ball(n, clr, a, m, r, x, y, vx, vy)
			//
	
			new Ball("White ball", "1;37", "white",
					2.0, 5, 
					50, 150, 30, 0),
			new Ball("Red ball", 31, "red", 
					2.0, 5, 
					450, 150, 0, 0)
		});


		Felt Table = new Felt(640, 320); // pool table is domino shaped
						// adjust for screen ratio in
						// higher layer
						//

		runTable(Table, theRack);


	}


		
	static void runTable(Felt table, Rack rack) {

		System.out.println(rack.report());
		
			try {

				java.lang.Thread.sleep(5000);
			} catch (Exception e) {}
		

		System.err.format("%c[2J", 0x1B);
		System.err.println("New pool hall test.\n----------");
		

		do {

			table.rub(rack); 

			rack.collisions();

			render(table, rack);
			
			//System.err.format("%c[2J", 0x1B);
			//System.err.println(theRack.report());

			try {

				java.lang.Thread.sleep(100);
			} catch (Exception e) {}

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
