package application.models;

import java.io.Serializable;

public class Author  implements Serializable{
	private int aId;
	private String firstName;
	private String lastName;
	private Address address;
	private String credential;
	private String description;
	private String nationality;

	public Author(int aId, String firstName, String lastName, Address address, String credential, String description, String nationality){
		this.aId = aId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.credential = credential;
		this.description = description;
		this.nationality = nationality;
	}

	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
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

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	private static final long serialVersionUID = 104L;


	@Override
	public String toString(){
		return this.firstName +" " + this.lastName;
	}
}
