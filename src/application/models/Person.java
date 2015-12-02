package application.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

	//user properties for all fields of a model call will notify us when a property is changed
	private final IntegerProperty pid;
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final ObjectProperty<Address> address;
	private final StringProperty phoneNum;
	private final ObjectProperty<LocalDate> dob;
	private final ObjectProperty<List<PersonRole>> personRoles;
	private static int lastPid;

	/**
     * Default constructor.
     */
	public Person(){
		this(++lastPid, null, null, null, null, null, null, null, null, null);	//can remove this line
	}

	/**
     * Constructor with some initial data.
     *
     * @param firstName
     * @param lastName
     */
	public Person(int pid, String firstName, String lastName, String street, String city, String state, String zip, String phoneNum, LocalDate dob, List<PersonRole> personRoles){
		this.pid = new SimpleIntegerProperty(pid);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);

		Address addr = new Address(street, city, state, zip);
		this.address = new SimpleObjectProperty<Address>(addr);

		this.phoneNum = new SimpleStringProperty(phoneNum);
		this.dob = new SimpleObjectProperty<LocalDate>(dob);
		this.personRoles = new SimpleObjectProperty<List<PersonRole>>(personRoles);
		lastPid++;
	}

	public int getId(){
		return pid.get();
	}

	public void setId(int pid){
		this.pid.set(pid);
	}

	public IntegerProperty idProperty(){
		return pid;
	}

	public String getFirstName(){
		return firstName.get();
	}

	public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public Address getAddress(){
    	return address.get();
    }

    public void setAddress(Address address){
    	this.address.set(address);	//setValue() also works
    }

    public ObjectProperty<Address> dobProperty(){
    	return address;
    }

    public String getPhoneNum(){
    	return this.phoneNum.get();
    }

    public void setPhoneNum(String phoneNum){
    	this.phoneNum.set(phoneNum);
    }

    public StringProperty phoneNumProperty(){
    	return phoneNum;
    }

    public LocalDate getBirthday() {
        return dob.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.dob.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return dob;
    }

    public List<PersonRole> getPersonRoles(){
    	return this.personRoles.get();
    }

    public String getPersonRolesToString(){
    	StringBuilder sb = new StringBuilder();

    	List<PersonRole> roles = this.personRoles.get();
    	if(roles != null){
    		for(PersonRole role : this.personRoles.get()){
        		sb.append(role.toString());
        		sb.append(",");
        	}

    		sb.deleteCharAt(sb.length() - 1);
    	}

    	return sb.toString();
    }

    public void setPersonRole(List<PersonRole> personRoles){
    	this.personRoles.set(personRoles);
    }

    public void setPersonRoleString(String personRoles){
    	String[] roles = personRoles.split(",");

    	List<PersonRole> personRolesList = new ArrayList<PersonRole>();

    	for(String role : roles){
    		if(role.equals("Librarian")){
    			personRolesList.add(new Librarian());
    		}else if(role.equals("LibraryMember")){
    			personRolesList.add(new LibraryMember());
    		}else{
    			personRolesList.add(new Administrator());
    		}
    	}

    	this.personRoles.set(personRolesList);
    }

    public ObjectProperty<List<PersonRole>> personRoleProperty(){
    	return personRoles;
    }
}
