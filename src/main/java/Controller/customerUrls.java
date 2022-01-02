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

}

