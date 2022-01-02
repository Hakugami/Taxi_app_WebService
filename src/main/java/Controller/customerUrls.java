package Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class customerUrls {

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

