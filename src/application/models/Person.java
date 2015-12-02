package application.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {

	public static final String PERSON_TYPE_ADMIN = "ADMINISTRATOR";
	public static final String PERSON_TYPE_LIBRARIAN = "LIBRARIAN";
	public static final String PERSON_TYPE_MEMBER = "LIB_MEMBER";
	
	//user properties for all fields of a model call will notify us when a property is changed
	private  int pid;
	private  String firstName;
	private  String lastName;
	private  Address address;
	private  String phoneNum;
	private  LocalDate dob;
	private List<PersonRole> personRoles;
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
		this.pid = pid ;
		this.firstName = firstName;
		this.lastName = lastName;

		Address addr = new Address(street, city, state, zip);
		this.address = addr;

		this.phoneNum = phoneNum;
		this.dob = dob;
		this.personRoles = personRoles;
		lastPid++;
	}

	
    public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public void setPersonRoles(List<PersonRole> personRoles) {
		this.personRoles = personRoles;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Address getAddress() {
		return address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public LocalDate getDob() {
		return dob;
	}

	public List<PersonRole> getPersonRoles(){
    	return this.personRoles;
    }

    public String getPersonRolesToString(){
    	StringBuilder sb = new StringBuilder();

    	List<PersonRole> roles = this.personRoles;
    	if(roles != null){
    		for(PersonRole role : this.personRoles){
        		sb.append(role.toString());
        		sb.append(",");
        	}

    		sb.deleteCharAt(sb.length() - 1);
    	}

    	return sb.toString();
    }

    public void setPersonRole(List<PersonRole> personRoles){
    	this.personRoles = personRoles;
    }

    public void setPersonRoleString(String personRoles){
    	String[] roles = personRoles.split(",");

    	List<PersonRole> personRolesList = new ArrayList<PersonRole>();

    	for(String role : roles){
    		if(role.equals(PERSON_TYPE_LIBRARIAN)){
    			personRolesList.add(new Librarian());
    		}else if(role.equals(PERSON_TYPE_MEMBER)){
    			personRolesList.add(new LibraryMember());
    		}else{
    			personRolesList.add(new Administrator());
    		}
    	}

    	this.personRoles = (personRolesList);
    }

    
}
