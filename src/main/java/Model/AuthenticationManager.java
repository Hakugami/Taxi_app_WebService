package Model;
import API.Credentials;
import Controller.Customer;
import Controller.Driver;
import View.Database;
import Controller.User;
import View.IDatabase;

public class AuthenticationManager {
    private static final AuthenticationManager authenticationManager=new AuthenticationManager();
    private User currentAcc;
    IDatabase database = Database.getInstance();
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
        currentAcc= Database.getUserByUsername(credentials.getUserName());
        return database.verify(credentials.getUserName(),credentials.getPassword());
    }
    public boolean loginDriver(Credentials credentials){
        currentAcc=Database.getDriverByUsername(credentials.getUserName());
        return database.verifyDriver(credentials.getUserName(),credentials.getPassword());
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
