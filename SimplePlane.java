


public class SimplePlane {

	
	private double[] data;	//constructor will initialize data to 2 doubles


	public SimplePlane(double w, double h) {
		data = new double[2];

		data[0] = w;
		data[1] = h;
	}

	public double width() {
		return data[0];
	}

	public double height() {
		return data[1];
	}



}
