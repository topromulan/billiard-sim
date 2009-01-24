

public class Test2 {

	public static void main(String[] args) {

		Rack theRack = new Rack (new Ball[] { 
			
			//  Ball(n, a, m, r, x, y, vx, vy)
			//
			new Ball("Orange ball", "orange", 
					2.0, 5, 
					150, 250, 6, 2),
			new Ball("White ball", "white",   
					2.0, 5, 
					630, 200, 4, 5),
			new Ball("Green ball", "green",   
					2.1, 5.01, 
					250, 285, -6, -2)

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

			//Clear the screen
			System.out.format("%c[2J", 0x1B);

			//Print the report
			System.out.println(theRack.report());

			try {

				java.lang.Thread.sleep(100);
			} catch (Exception e) {}

		} while ( theRack.hasMotion( ) );


		System.out.println("Test complete.");



	}




}
