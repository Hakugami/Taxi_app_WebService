package View;

public class Rate {
	private String username;
	private double rate;
	public Rate(String username, double rate) {
		this.username = username;
		this.rate = rate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
}
