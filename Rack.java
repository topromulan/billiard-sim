


public class Rack {

	public Ball[] set;

	public Rack(Ball[] s) {

		set = s;
	}



	public boolean hasMotion() {


		//return true if any ball in the rack is unstill
		//
		// still the table for vectors below .. a low level

		for(int i=0; i < set.length; i++)
		{
			if (! set[i].still)
				return true;
			
		}

		return false;


	}

	public String report() {

		StringBuffer news = new StringBuffer(
"=============================RACK=============================\n" 
		);

		for(int i=0; i < set.length; i++)
		{
			news.append(set[i].report());
		}
		
		news.append(
"=============================++++=============================\n"
		);

		return news.toString();

	}


}





