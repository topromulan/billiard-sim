


public class SimpleCoordinates {

	
	private double[] data;	//constructor will initialize data to 2 doubles


	public SimpleCoordinates(double x, double y) {
		data = new double[2];

		data[0] = x;
		data[1] = y;
	}

	public void set(double x, double y) {
		setx(x);
		sety(y);
	}

	public void setx(double x) {
		data[0] = x;
	}
	public void sety(double y) {
		data[1] = y;
	}

	public double x() {
		return data[0];
	}

	public double y() {
		return data[1];
	}


}
