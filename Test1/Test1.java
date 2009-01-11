

public class Test1 {

	public static void main(String[] args) {


		Ball Number1 = new Ball("White ball", "white", 4.2, 1, 0, 0, 0, 0);
		Ball Number2 = new Ball("Green ball", "green", 4.0, 1, -5, -2, 1, 2.5);


		System.out.println("Pool hall test.");
		

		System.out.print(Number1.report());
		System.out.print(Number2.report());

		System.out.println("Test complete.");



	}





}
