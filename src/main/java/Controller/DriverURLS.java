package Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Vector;

public class DriverURLS {
    Driver driver=new Driver();

    @GetMapping("driver/availableRides")
    public Vector<Ride> displayRequests(){
        return driver.displayDriverRides();
    }

    @PostMapping("driver/chooseRideToOffer/{numOfRide}/{price}")
    public void chooseRide(@PathVariable("numOfRide") int numOfRide,@PathVariable("price") double price){
        driver.chooseRide(numOfRide,price);
        return;
    }

    @GetMapping("driver/currentRide")
    public String displayCurrntRide(){
        String currentRide="";
        currentRide=driver.getDriverRide().getSource()+"-------->"+driver.getDriverRide().getDestination()+"/n";
        currentRide+=driver.getDriverRide().getCustomer().getUserName()+"/n";
        currentRide+=driver.getDriverRide().getPrice(driver.getUserName());
        return currentRide;
    }

    @GetMapping("driver/rating")
    public String displayrating(){
        String currentRate="";
        currentRate+= driver.getAllRating();
        currentRate+=driver.getAverageRating();
        return currentRate;
    }

}
