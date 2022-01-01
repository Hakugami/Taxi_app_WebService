package Controller;
import Controller.*;
public class AuthenticationManager {
    public boolean register(User user){
        if(user instanceof Customer){
            ((Customer)user).register(((Customer)user).getUserName(),((Customer)user).getPassword()
                    ,((Customer)user).getEmail(),((Customer)user).getPhone() );
            return true;
        }
        else if(user instanceof Driver){
            ((Driver)user).register(((Driver)user).getUserName(),((Driver)user).getPassword(),((Driver)user).getEmail(),((Driver)user).getPhone()
                    ,((Driver)user).getId(),((Driver)user).getLicence());
            return true;
        }
        return false;
    }
}
