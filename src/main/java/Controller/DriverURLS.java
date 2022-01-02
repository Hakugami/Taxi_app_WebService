package Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@RestController

public class DriverURLS {

    AuthenticationManager authenticationManager=AuthenticationManager.getAuthenticationManager();

    @PostMapping(value="/driver/register",consumes = "application/json",produces = "application/json")
    public boolean register(@RequestBody Driver driver){
        return authenticationManager.register(driver);
    }
    @PostMapping(value="/driver/login",consumes = "application/json",produces = "application/json")
    


    @GetMapping("/driver/{username}/availableRides")
    public Vector<Ride> displayRequests(@PathVariable("username") String username ){
        Driver driver = Database.getDriverByUsername(username);
        return driver.displayDriverRides();
    }

    @PostMapping("driver/{username}/chooseRideToOffer/{numOfRide}/{price}")
    public void chooseRide(@PathVariable("username") String username ,@PathVariable("numOfRide") int numOfRide,@PathVariable("price") double price){
        Driver driver = Database.getDriverByUsername(username);
        driver.chooseRide(numOfRide,price);
        return;
    }

    @GetMapping("driver/{username}/currentRide")
    public String displayCurrentRide(@PathVariable("username") String username){
        Driver driver = Database.getDriverByUsername(username);
        String currentRide="";
        currentRide=driver.getDriverRide().getSource()+"-------->"+driver.getDriverRide().getDestination()+"\n";
        currentRide+=driver.getDriverRide().getCustomer().getUserName()+"\n";
        currentRide+=driver.getDriverRide().getPrice(driver.getUserName());
        return currentRide;
    }

    @GetMapping("driver/{username}/rating")
    public String displayRating(@PathVariable("username") String username){
        Driver driver = Database.getDriverByUsername(username);
        String currentRate="";
        currentRate+= driver.getAllRating()+"\n";
        currentRate+=driver.getAverageRating();
        return currentRate;
    }

    @PostMapping ("driver/{username}/setFavoriteArea/{area}")
    public void setFavoriteArea(@PathVariable("username") String username,@PathVariable("area") String area)
    {
        Driver driver = Database.getDriverByUsername(username);
        driver.setFavouriteArea(area);
    }

    @GetMapping("driver/{username}/getFavouriteArea")
    public String getFavouriteArea(@PathVariable("username") String username){
        Driver driver = Database.getDriverByUsername(username);
        return driver.getFavouriteArea();
    }

}
