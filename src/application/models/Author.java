package application.models;

public class Author {
	private String aId;
	private String firstName;
	private String lastName;
	private Address address;
	private String credential;
	private String description;
	private String nationality;

	public Author(String aId, String firstName, String lastName, Address address, String credential, String description, String nationality){
		this.aId = aId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.credential = credential;
		this.description = description;
		this.nationality = nationality;
	}
}
