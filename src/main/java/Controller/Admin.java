package Controller;


import View.Database;
import View.IDatabase;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Admin extends Person {
    public static ArrayList<Driver> allRequests=new ArrayList<>();
    private static final ArrayList<String> logs=new ArrayList<>();
    IDatabase database = Database.getInstance();
    private static final Admin admin= new Admin();
    private Admin(){}

    public static Admin getAdmin() {
        return admin;
    }

    public static void storeLogs(String log){
        logs.add(log);
    }
    public String showLogs(){
        StringBuilder stringBuilder=new StringBuilder();
        for(String string:logs){
            stringBuilder.append(string).append("\n");
        }
        return stringBuilder.toString();
    }

    public void setDiscounts(String area,Double value){
        Database.areaDiscounts.put(area,value);
    }

    public void setHolidays(String holidays){
        Database.addPublicHoliday(holidays);
    }




    public static void getDriversRequests(Driver driver){
        allRequests.add(driver);
    }


    public boolean reviewRequests(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("all the current requests : \n"+allRequests.toString());
        System.out.println("Please enter the ID of the driver you wish to select");
        String selectDriver=scanner.nextLine();
        for(int i=0;i<allRequests.size();i++){
            if(Objects.equals(allRequests.get(i).getId(), selectDriver)){
                System.out.println("1-Accept request\n2-Deny request");
                int choice=scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        database.addDriver(allRequests.get(i));
                        allRequests.remove(i);
                        return true;
                    }
                    case 2 -> {
                        allRequests.remove(i);
                        return false;
                    }
                    default -> {
                        System.out.println("something went wrong !");
                        return false;
                    }
                }


            }
            else {
                System.out.println("You have entered a wrong ID");
            }
        }
        return false;
    }

    public void suspend(){
        Scanner scanner=new Scanner(System.in);
        Scanner scannerInt=new Scanner(System.in);
        System.out.println("1-Suspend Driver\n2-Suspend Customer");
        int choice= scannerInt.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("Suspension : \n" + Database.getAllDrivers().toString());
                System.out.println("Please enter the ID of the driver you wish to select");
                String select = scanner.nextLine();
                for (Driver driver : Database.getAllDrivers()) {
                    if (Objects.equals(driver.getId(), select)) {
                        driver.setActive(false);
                        System.out.println(driver.getUserName() + " has been suspended");
                        break;
                    }
                }
            }
            case 2 -> {
                System.out.println("Suspension : \n" + Database.getAllUsers().toString());
                System.out.println("Please enter the Username of the Customer you wish to select");
                String select = scanner.nextLine();
                for (Customer customer : Database.getAllUsers()) {
                    if (Objects.equals(customer.getUserName(), select)) {
                        customer.setActive(false);
                        System.out.println(customer.getUserName() + " has been suspended");
                        break;
                    }
                }
            }
        }

    }

    public String displayRequests(){
        ArrayList<Driver> temp=allRequests;
        if(allRequests != null){
            return temp.toString();
        }
        else{
            return "There are no requests currently";
        }
    }
    public boolean acceptRequest(String id){
        for(int i=0;i<allRequests.size();i++){
            if(Objects.equals(allRequests.get(i).getId(), id)){
                database.addDriver(allRequests.get(i));
                allRequests.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean denyRequest(String id){
        for(int i=0;i<allRequests.size();i++){
            if(Objects.equals(allRequests.get(i).getId(), id)){
                allRequests.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean suspendDriver(String id){
        for (Driver driver : Database.getAllDrivers()) {
            if (Objects.equals(driver.getId(), id)) {
                driver.setActive(false);
                System.out.println(driver.getUserName() + " has been suspended");
                return true;
            }
        }

        return false;
    }
    public boolean suspendCustomer(String userName) {
        for (Customer customer : Database.getAllUsers()) {
            if (Objects.equals(customer.getUserName(), userName)) {
                customer.setActive(false);
                System.out.println(customer.getUserName() + " has been suspended");
                return true;
            }
        }
            return false;
    }

}
