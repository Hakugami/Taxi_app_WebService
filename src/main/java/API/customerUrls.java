package API;

import Controller.Customer;
import Model.AuthenticationManager;
import View.Database;
import org.springframework.web.bind.annotation.*;

@RestController
public class customerUrls {

    AuthenticationManager authenticationManager=AuthenticationManager.getAuthenticationManager();
    @PostMapping(value="user/register",consumes = "application/json",produces = "application/json")
    public boolean register(@RequestBody Customer customer){
        return authenticationManager.register(customer);
    }
    @PostMapping(value="/user/login",consumes = "application/json",produces = "application/json")
    public boolean login(@RequestBody Credentials credentials){
        return authenticationManager.loginCustomer(credentials);
    }
    @GetMapping("user/logout")
    public String logout(){
        return authenticationManager.logout();
    }

    @PostMapping("user/{username}/requestRide/{source}/{dest}")
    public void requestRide(@PathVariable("username") String username,@PathVariable("source") String source,@PathVariable("dest") String dest)
    {
        Customer customer1= Database.getUserByUsername(username);
        customer1.requestRide(source, dest);
    }

    @PostMapping("user/{username}/addRate/{rate}")
    public boolean addRate(@PathVariable("username") String username,@PathVariable("rate") double rate)
    {
        Customer customer1 = Database.getUserByUsername(username);
        try{
            customer1.addRate(rate);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    @GetMapping("user/{username}/displayOffers")
    public String displayOffers(@PathVariable("username") String username)
    {
        Customer customer1 = Database.getUserByUsername(username);
        return customer1.displayOffers();
    }

    @PostMapping("user/{username}/chooseOffer/{ridenumber}")
    public void  chooseOffer(@PathVariable("username")String username, @PathVariable("ridenumber") int ridenumber)
    {
        Customer customer1 = Database.getUserByUsername(username);
        customer1.chooseOffer(ridenumber);
    }

    @PostMapping("user/{username}/rateRide/{rate}")
    public boolean rateRide(@PathVariable("username")String username,@PathVariable("rate") double rate)
    {
        Customer customer1 = Database.getUserByUsername(username);
        try{
            customer1.addRate(rate);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


}

