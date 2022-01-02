package View;
import Model.*;
import Controller.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class  Database implements IDatabase {

    private static String date = "";
    private static final ArrayList<Customer> allUsers=new ArrayList<>();
    private static final ArrayList<Driver> allDrivers= new ArrayList<>();
    private static final ArrayList<Ride> allRides=new ArrayList<>();
    private static final ArrayList<Driver> driversRequests=new ArrayList<>();
    public static Map<String,Double> areaDiscounts =new HashMap<>();
    private static final ArrayList<String> PublicHoliday=new ArrayList<>();
    private static final Database instance=new Database();//singleton

    public static String getDate() {
        return date;
    }
    public static void setDate(String date1){
        date=date1;
    }

    public static void addPublicHoliday(String holiday){
        PublicHoliday.add(holiday);
    }

    public static ArrayList<String> getPublicHoliday(){
        return PublicHoliday;
    }

    public static Map<String, Double> getAreaDiscounts() {
        return areaDiscounts;
    }

    public static void setAreaDiscounts(Map<String, Double> areaDiscounts) {
        Database.areaDiscounts = areaDiscounts;
    }

    private Database() {}

    public static Database getInstance() {
        return instance;
    }

    public static void saveLogs(String log){
        Admin.storeLogs(log);
    }

    public static String getAreaDiscounts(String area){
        String result;
        for(String area1: areaDiscounts.keySet()){
            if(area.equals(area1)){
               result= String.valueOf(areaDiscounts.get(area1));
               return result;
            }
        }
        return null;
    }

    public boolean verify(String user, String pass){
        for (User user1:allUsers) {
            if(user1.getUserName().equals(user)&&user1.getPassword().equals(pass)){
                user1.setOnline(true);

                return true;
            }
        }
        return false;
    }

    public boolean verifyDriver(String user,String pass){
        for(Driver driver:allDrivers){
            if(driver.getUserName().equals(user) && driver.getPassword().equals(pass)){
                driver.setOnline(true);

                return true;
            }
        }
        return false;
    }



    public boolean addUser(Customer user){
        if(allUsers.contains(user)){
            System.out.println("This user already exists");
            return false;
        }
        else{
            allUsers.add(user);
            System.out.println("user has been added successfully");
            return true;
        }
    }


    public Customer getUser(String user,String pass){
        for (Customer user1:allUsers){
            if(user1.getUserName().equals(user)||user1.getPassword().equals(pass)){
                return user1;
            }
        }
        return null;
    }

    public static Customer getUserByUsername(String user){
        for (Customer user1:allUsers){
            if(user1.getUserName().equals(user) && user1.isOnline()){
                return user1;
            }
        }
        return null;
    }

    public static void getUserBySource(String source) {
    	for (Ride ride:allRides){
            if(ride.getSource().equals(source)){
            	System.out.println("Customer: "+ ride.getCustomer().getUserName() +"    "+ ride.getCustomer().getEmail());
            }
    	}
     }

    public boolean addDriver(Driver driver){
        if(allDrivers.contains(driver)){
            System.out.println("This driver already exists");
            return false;
        }
        else{
            allDrivers.add(driver);
            System.out.println("Driver has been added successfully");
            return true;
        }
    }


    public Driver getDriver(String user,String pass){
        for (Driver driver:allDrivers){
            if(driver.getUserName().equals(user)&&driver.getPassword().equals(pass)){
                return driver;
            }
        }
        return null;
    }

    public static Driver getDriverByUsername(String user){
        for (Driver driver:allDrivers){
            if(driver.getUserName().equals(user) && driver.isOnline()){
                return driver;
            }
        }
        return null;
    }

    public static Driver getDriver(User user){
        int index=allDrivers.indexOf((Driver) user);
        return allDrivers.get(index);
    }
    public static Customer getCustomer(User user){
        int index=allUsers.indexOf((Customer) user);
        return allUsers.get(index);
    }





    public static ArrayList<Customer> getAllUsers() {
        return allUsers;
    }

    public static ArrayList<Driver> getAllDrivers() {
        return allDrivers;
    }

    public static ArrayList<Ride> getAllRides() {
        return allRides;
    }

    public static ArrayList<Driver> getDriversRequests() {
        return driversRequests;
    }


}
