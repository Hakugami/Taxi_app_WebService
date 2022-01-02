package Controller;

import View.Database;
import View.Offer;
import View.Rate;

import java.util.Vector;

public class Customer extends User {

	protected Ride myRide;
	protected boolean firstRide=true;

	public void checkDiscount(){
		boolean flag=false;
		for(String string: Database.getPublicHoliday()){
			flag=string.equals(Database.getDate());

		}
		double price=myRide.getSelectedOffer().getPrice();
		if(this.getBirthDate().equals(Database.getDate())){
			myRide.getSelectedOffer().setPrice(price - (price * 0.05));
		}
		else if(this.firstRide){
			myRide.getSelectedOffer().setPrice(price - (price * 0.1));
		}
		else if(myRide.getNumOfPeople()>1){
			myRide.getSelectedOffer().setPrice(price - (price * 0.05));
		}
		else if(flag){
			myRide.getSelectedOffer().setPrice(price - (price * 0.05));
		}
		else if(Database.getAreaDiscounts().size()>0){
			for(String string : Database.getAreaDiscounts().keySet()){
				if(string.equals(myRide.getDestination())){
					myRide.getSelectedOffer().setPrice(price - (price * 0.1));
				}
			}
		}
	}

    public void requestRide (String source,String destination,int numOfppl){
		Ride r=new Ride(source,destination,this,numOfppl);//leave this, remove the rest
		Database.getAllRides().add(r);//this too
		myRide=r;// added this to see his ride

    }
	Vector<Offer>customOffers = null;
	public String displayOffers(){
		StringBuilder Offers= new StringBuilder();
		for (int i = 0; i < myRide.getOffers().size(); i++) {
			Offers.append(i).append(1).append("-").append(myRide.getOffers().get(i));
			customOffers.add(myRide.getOffers().get(i));
		}
		return Offers.toString();
	}
    // parameter to choose offer
    public boolean chooseOffer(int choice){
		if(myRide==null)
		{
			return false;
		}
		if(customOffers!=null) {
			if (choice == 0) {
				return false;
			}
			myRide.setSelectedOffer(customOffers.get(choice));
			checkDiscount();
			firstRide=false;
			myRide.log("Customer accepted offer",this);
		}
		return true;
    }
    public void addRate(double rate){
			Rate customerRate= null;
			if (myRide.getSelectedOffer() != null) {
				customerRate = new Rate(myRide.getSelectedOffer().getDriver().getUserName(), rate);
				myRide.getSelectedOffer().getDriver().setRate(customerRate);
			}
			else {
				System.out.println("No recent rides");
			}

    }

	@Override
	public String toString() {
		return "Customer{" +
				"myRide=" + myRide +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", Email='" + Email + '\'' +
				", phone='" + phone + '\'' +
				", active=" + active +
				'}';
	}
}
