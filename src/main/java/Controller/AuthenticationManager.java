package Controller;
import Controller.*;

import javax.xml.crypto.Data;

public class AuthenticationManager {
    private static final AuthenticationManager authenticationManager=new AuthenticationManager();
    private User currentAcc;

    private AuthenticationManager(){}

    public static AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

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

    public boolean loginCustomer(Credentials credentials){
        currentAcc=Database.getUserByUsername(credentials.getUserName());
        return Database.verify(credentials.getUserName(),credentials.getPassword());
    }
    public boolean loginDriver(Credentials credentials){
        currentAcc=Database.getDriverByUsername(credentials.getUserName());
        return Database.verifyDriver(credentials.getUserName(),credentials.getPassword());
    }
    public String logout(){
        if(currentAcc instanceof Customer){
            Database.getCustomer(currentAcc).setOnline(false);
        }
        else if(currentAcc instanceof  Driver){
            Database.getDriver(currentAcc).setOnline(false);
        }
        currentAcc=null;
        return "done";
    }

}
