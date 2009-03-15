

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





//			new Ball("White ball", "white",
//					2.0, 5, 
//					50, 50, 30, 0),
////			new Ball("Red ball", "red", 
//					2.0, 5, 
//					450, 50, -10, -20),
//			new Ball("Green ball", "green", 
//					2.0, 5, 
//					470, 50, -20, -15),
//			new Ball("Blue ball", "blue", 
////					2.0, 5, 
//					400, 150, 25, -20),
//
//


//			new Ball("Red ball", "red", 
//					2.0, 5, 
//					10, 10, 10, 0),
//			new Ball("Target 1", "t1", 
//					2.0, 5, 
//					260, 10, 0, 0),
//					
//			new Ball("Orange ball", "orange", 
//					2.0, 5, 
//					10, 45, 10, 0),
//			new Ball("Target 2", "t2", 
//					2.0, 5, 
//					260, 50, 0, 0),
//					
//			new Ball("Yellow ball", "yellow", 
//					2.0, 5, 
//					10, 90, 10, 0),
//			new Ball("Target 3", "t3", 
//					2.0, 5, 
//					260, 90, 0, 0),
//
//			new Ball("Green ball", "green", 
//					2.0, 5, 
//					10, 130, 10, 0),
//			new Ball("Target 4", "t4", 
//					2.0, 5, 
//					260, 130, 0, 0),
//
//			new Ball("Blue ball", "blue", 
//					2.0, 5, 
//					10, 170, 10, 0),
//			new Ball("Target 5", "t5", 
//					2.0, 5, 
//					260, 170, 0, 0),
//
//			new Ball("Indigo ball", "indigo",   
//					2.0, 5, 
//					10, 210, 10, 0),
//			new Ball("Target 6", "t6", 
//					2.0, 5, 
//					260, 210, 0, 0),
//
//			new Ball("Violet ball", "violet",   
//					2.0, 5, 
//					410, 50, 10, 0),
//			new Ball("Target 7", "t7", 
//					2.0, 5, 
//					408, 150, 10, -10)
//
//			});

		Felt Table = new Felt(640, 320); // pool table is domino shaped
						// adjust for screen ratio in
						// higher layer


		

		System.out.println(theRack.report());


		
			try {

				java.lang.Thread.sleep(5000);
			} catch (Exception e) {}
		


		System.err.format("%c[2J", 0x1B);
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
