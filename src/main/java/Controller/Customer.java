package Controller;

import java.util.Vector;

public class Customer extends User {

	protected Ride myRide;

    
    public void requestRide (String source,String destination){
		Ride r=new Ride(source,destination,this);//leave this, remove the rest
		Database.getAllRides().add(r);//this too
		myRide=r;// added this to see his ride

    }
	Vector<Offer>customOffers = null;
	public String displayOffers(){
		String Offers="";
		for (int i = 0; i < myRide.getOffers().size(); i++) {
			Offers+=i+1+"-"+myRide.getOffers().get(i);
			customOffers.add(myRide.getOffers().get(i));
		}
		return Offers;
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
