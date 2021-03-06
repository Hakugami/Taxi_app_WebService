package Controller;

import View.*;

import java.util.ArrayList;
import java.util.Vector;


public class Driver extends User implements driverAndRideObserver {
	protected String id;
	protected String licence;
	protected double rate;
	protected ArrayList<String> favouriteArea = new ArrayList<>();
	protected ArrayList<Rate> allRating = new ArrayList<>();
	protected Ride driverRide = null;
	protected static int ridesCounter = 0;
	protected String currentLocation;
	protected boolean isBusy=false;

	public void setDriverRide(Ride driverRide) {
		this.driverRide = driverRide;
	}

	Vector<Ride>driverRides = null;
	public Vector<Ride> displayDriverRides(){
		//filter area depending on fav area and offer an offer and register it in the database
		for (String area : favouriteArea) {
			for(Ride ride : Database.getAllRides()) {
				if(ride.getSource().equals(area)){
					driverRides.add(ride);
				}
			}
		}
		return driverRides;
	}//print Rides
	public void chooseRide( int numOfRide, double price){
		setOffer(driverRides.get(numOfRide-1),price);
	}//assign offer to Ride.

	public void setOffer(Ride ride,double price) {
		Offer offer = new Offer(this, price);
		ride.getOffers().add(offer);
		ride.log("Captain added offer price",this);
	}
	public boolean register(String user, String pass, String Email,String phone,String id, String licence){
		this.register(user,pass,Email,phone);
		this.id=id;
		this.licence=licence;
		System.out.println("Pending approval");
		Admin.getDriversRequests(this);
		return true;
	}

	public void addRide(Ride r) {
		driverRide =r;
	}

	public boolean listRidesWithSourceArea() {
		ridesCounter=0;
		for (String area : favouriteArea) {
			for (Ride r : Database.getAllRides())
				if (area.equals(r.getSource())) {
				System.out.println("Source: "+r.getSource()+"   "+"destination: "+r.getDestination());
				Database.getUserBySource(area);
				ridesCounter++;
				}
		}
		return ridesCounter != 0;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean busy) {
		isBusy = busy;
	}

	public void addArea(String Area) {
		favouriteArea.add(Area);              //add area to the fav areas
	}
	public void offer(double price) {
		Offer offer = new Offer(this, price);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public void setFavouriteArea(ArrayList<String> favouriteArea) {
		this.favouriteArea = favouriteArea;
	}

	public void setAllRating(ArrayList<Rate> allRating) {
		this.allRating = allRating;
	}

	public Ride getDriverRide() {
		return driverRide;
	}

	public void setLicence(String licence) {
		this.licence=licence;
	}
	public String getLicence() {
		return licence;
	}
	public void setRate(String username,double rate) {
		this.rate=rate;
		Rate r=new Rate(username,rate);
		allRating.add(r);
	}
	public void setRate(Rate rate) {
		allRating.add(rate);
	}
	public double getRate() {
		return rate;
	}
	public void setFavouriteArea(String favArea) {
		favouriteArea.add(favArea);
		
	}

	public String getFavouriteArea() {
		StringBuilder Result= new StringBuilder();
		for (String favArea: favouriteArea) {
			Result.append(favArea).append("\n");
		}
		return Result.toString();
	}
	public void setAllRating(String username,double rate) {
		Rate r=new Rate(username,rate);
		allRating.add(r);
	}
	public String getAllRating() {
		String allRating="";
		for(Rate allRate : this.allRating)
		{
			allRating +=allRate.getUsername()+"   "+allRate.getRate();
		}
		return allRating;
	}
	public String getAverageRating() {
		double r=0;
		for(Rate allRate : allRating){
			r+=allRate.getRate();
		}
		return String.valueOf(r/allRating.size());
	}
	@Override
	public String toString() {
		return "Driver{" +
				"id='" + id + '\'' +
				", licence='" + licence + '\'' +
				", rate=" + rate +
				", favouriteArea=" + favouriteArea +
				", allRating=" + allRating +
				", driverRide=" + driverRide +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", Email='" + Email + '\'' +
				", phone='" + phone + '\'' +
				", active=" + active +
				'}'+"\n";
	}
	@Override
	public void update(Ride ride) {
		setDriverRide(ride);
	}
}
