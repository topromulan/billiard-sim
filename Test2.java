

public class Test2 {

	public static void main(String[] args) {

		Rack theRack = new Rack (new Ball[] { 
			
			new Ball("Orange ball", "orange", 2.0, 1, 1, 1, 1, 1),
			new Ball("White ball", "white",   2.0, 1, 0, 0, 1, 1),
			new Ball("Green ball", "green",   2.1, 1, -1, -1, -1, -1)

			});

		Felt Table = new Felt(640, 320); // pool table is domino shaped
						// adjust for screen ratio in
						// higher layer

		
		


		System.err.println("New pool hall test.\n----------");
		

		do {

			Table.rub(theRack); 

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
