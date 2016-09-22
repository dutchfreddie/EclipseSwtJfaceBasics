package defaultrcp.holidaywizard;

public class HolidayModel 
{
	public static final String copyright = "(c) Copyright IBM Corporation 2002.";
		
	 String departureDate;
	
	protected String returnDate;

	protected String destination;
	
	protected String departure;
	
	protected boolean usePlane;
	
	protected boolean resetFlights;

	protected String selectedFlight;
	
	float price;
	
	protected String seatChoice;
	
	protected String rentalCompany;
	
	protected String carPrice;
	
	protected boolean buyInsurance;

	boolean discounted = false;	

	public String toString()
	{
		String s;
		s= "Your holiday: \n";
		if (usePlane) s= s+"Flying from ";
		else s = s+"Driving from ";
		s= s +departure+ " to "+  destination+
			"\nleaving on "+departureDate +" returning on "+returnDate;
		if (usePlane)
			s = s + "\nflight: " + selectedFlight + "\nseat: "+seatChoice+
				"\nprice: "+price;
		else {
			s = s+ "\nrental company " + rentalCompany +
				 "\nprice "+ carPrice;
			if (buyInsurance) s = s+ "\nbuy insurance from the rental company";
			else  s = s +"\ndo not buy insurance from the rental company";
		}
		return s;	
	}
}

