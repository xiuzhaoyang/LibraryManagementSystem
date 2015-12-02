package application.models;

public class Address {
	String street;
	String zip;
	String state;
	String city;

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public Address(String street, String city, String state, String zip){
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
}
