package Controller;

import View.Database;
import View.IDatabase;

public abstract class User extends Person {
    protected boolean online=false;
    public boolean active=true;
    IDatabase database=Database.getInstance();
    public User() {
        super();
    }

    public boolean register(String user, String pass, String Email,String phone){
        this.setUserName(user);
        this.setPassword(pass);
        this.setEmail(Email);
        this.setPhone(phone);
        if (!(this instanceof Driver)) {
            database.addUser((Customer) this);
        }
        return true;

    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isOnline() {
        return online;
    }

    public boolean login(String username, String pass){
        return database.verify(username,pass);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User(String userName, String password, String email) {
        super(userName, password, email);
    }
}
