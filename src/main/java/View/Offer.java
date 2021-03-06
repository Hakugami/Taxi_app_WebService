package View;

import Controller.Driver;

public class Offer {
    private Driver driver;
    private double price;
    private String Details;
    
    
    public Offer(Driver driver, double price) {
		this.driver = driver;
		this.price = price;
	}

	public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "driver=" + driver +
                ", price=" + price +
                ", Details='" + Details + '\'' +
                '}';
    }
}
