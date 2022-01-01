package Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    AuthenticationManager authenticationManager=new AuthenticationManager();
    Admin admin=new Admin();
    @PostMapping(value="/customer/register",consumes = "application/json",produces = "application/json")
    public boolean register(@RequestBody Customer customer){
        return authenticationManager.register(customer);
    }
    @PostMapping(value="/driver/register",consumes = "application/json",produces = "application/json")
    public boolean register(@RequestBody Driver driver){
        return authenticationManager.register(driver);
    }
    @GetMapping("/admin/review")
    public void reviewRequests(){
        admin.reviewRequests();
    }
}
