package defaultrcp.holidaywizard_new;

public class HolidayModel 
{
	public static final String copyright = "(c) Copyright IBM Corporation 2002.";
		
	private String departureDate;	
	private String returnDate;
	private String destination;	
	private String departure;	
	private boolean usePlane;	
	private boolean resetFlights;
	private String selectedFlight;	
	private float price;	
	private String seatChoice;	
	private String rentalCompany;	
	private String carPrice;	
	private boolean buyInsurance;
	private boolean discounted = false;	

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public boolean isUsePlane() {
		return usePlane;
	}

	public void setUsePlane(boolean usePlane) {
		this.usePlane = usePlane;
	}

	public boolean isResetFlights() {
		return resetFlights;
	}

	public void setResetFlights(boolean resetFlights) {
		this.resetFlights = resetFlights;
	}

	public String getSelectedFlight() {
		return selectedFlight;
	}

	public void setSelectedFlight(String selectedFlight) {
		this.selectedFlight = selectedFlight;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getSeatChoice() {
		return seatChoice;
	}

	public void setSeatChoice(String seatChoice) {
		this.seatChoice = seatChoice;
	}

	public String getRentalCompany() {
		return rentalCompany;
	}

	public void setRentalCompany(String rentalCompany) {
		this.rentalCompany = rentalCompany;
	}

	public String getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(String carPrice) {
		this.carPrice = carPrice;
	}

	public boolean isBuyInsurance() {
		return buyInsurance;
	}

	public void setBuyInsurance(boolean buyInsurance) {
		this.buyInsurance = buyInsurance;
	}

	public boolean isDiscounted() {
		return discounted;
	}

	public void setDiscounted(boolean discounted) {
		this.discounted = discounted;
	}

	public static String getCopyright() {
		return copyright;
	}

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

