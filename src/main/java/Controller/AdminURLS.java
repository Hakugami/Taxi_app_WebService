package Controller;

import Actors.Admin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminURLS {
    Admin admin=Admin.getAdmin();
    @GetMapping("admin/requests")
    public String displayRequests(){
        return admin.displayRequests();
    }
    @PostMapping("admin/requests/accept/{id}")
    public boolean acceptRequest(@PathVariable String id){return admin.acceptRequest(id);}
    @PostMapping("admin/requests/deny/{id}")
    public boolean denyRequest(@PathVariable String id){
        return admin.denyRequest(id);
    }
    @PostMapping("admin/suspend/driver/{id}")
    public boolean suspendDriver(@PathVariable String id){
        return admin.suspendDriver(id);
    }
    @PostMapping("admin/suspend/customer/{userName}")
    public boolean suspendCustomer(@PathVariable String userName){
        return admin.suspendCustomer(userName);
    }
    @GetMapping("admin/logs")
    public String getLogs(){
        return admin.showLogs();
    }


}
