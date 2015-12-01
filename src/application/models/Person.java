package application.models;

import java.io.Serializable;

public class Person implements Serializable{

	int pid;
	String firstName;
	String lastName;
	
	Address address;
	
	String phoneNum;
	
	public Person(){
		
	}
	
	public Person(String fName, String lName, Address ad, String phoneNum){
		this.firstName = fName;
		this.lastName = lName;
		this.address = ad;
		this.phoneNum = phoneNum;
	}
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	private static final long serialVersionUID = 100222637282828L;
	
}
