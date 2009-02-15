

public class Test2 {

	public static void main(String[] args) {

		Rack theRack = new Rack (new Ball[] { 
			
			//  Ball(n, a, m, r, x, y, vx, vy)
			//
			new Ball("Orange ball", "orange", 
					2.0, 5, 
					150, 250, 16, 12),
			new Ball("White ball", "white",   
					2.0, 5, 
					630, 200, 40, 5),
			new Ball("Green ball", "green",   
					2.1, 5.01, 
					250, 285, -6, -20),
			new Ball("Wow 1 ball", "wow",   
					2.0, 5, 
					200, 185, -16, -25),
			new Ball("Wow 2 ball", "wow",   
					2.0, 5, 
					290, 125, -16, -25),
			new Ball("Wow 3 ball", "wow",   
					2.0, 5, 
					210, 155, -16, -25),
			new Ball("Wow 4 ball", "wow",   
					2.0, 5, 
					600, 135, -16, -25),
			new Ball("Wow 5 ball", "wow",   
					2.0, 5, 
					500, 175, -16, -25),
			new Ball("Wow 6 ball", "wow",   
					2.0, 5, 
					400, 185, -16, -25),
			new Ball("Crazy ball", "crazy",   
					2.0, 3, 
					290, 215, -32, -1)

			});

		Felt Table = new Felt(640, 320); // pool table is domino shaped
						// adjust for screen ratio in
						// higher layer


		

		System.out.println(theRack.report());


		
			try {

				java.lang.Thread.sleep(5000);
			} catch (Exception e) {}
		


		System.err.println("New pool hall test.\n----------");
		

		do {

			Table.rub(theRack); 

			theRack.collisions();

			render(Table, theRack);
			
			//System.err.format("%c[2J", 0x1B);
			//System.err.println(theRack.report());

			try {

				java.lang.Thread.sleep(100);
			} catch (Exception e) {}

		} while ( theRack.hasMotion( ) );


		System.out.println("Test complete.");



	}

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

					for(int b=0; b < rack.set.length; b++)
					{
						double x=rack.set[b].location.x();
						double y=rack.set[b].location.y();

						if( (x > xMin && x <= xMax) &&
						    (y > yMin && y <= yMax) )
						{
							foundOne=true;
							break;
						}

					}

					if(foundOne)
						table.append("O");
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
