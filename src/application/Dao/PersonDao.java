package application.Dao;

import java.util.List;

import application.models.Person;

public class PersonDao extends AbstractDao<Person>{
	
	/*
	 * CONSTRUCTOR
	 * 
	 * IMPORTANT, COPY THIS PATTERN TO OTHER DAO SO THAT IT CAN WORK WELL
	 * */
	public PersonDao(){
		super();
		TABLE_KEY = Person.class.getSimpleName();
	}

	public Person loadPersonById(int pid){
		List<Person> ps = loadObjs();
		
		for(Person p : ps){
			if(pid == p.getPid()){
				return p;
			}
		}
		
		return null;
	}
	
	public Person loadPersonByNameAndPwd(String uName, String pwd){
		List<Person> ps = loadObjs();
		
		for(Person p : ps){
			if(uName.equals(p.getUserName()) && pwd.equals(p.getPwd())){
				return p;
			}
		}
		
		return null;
	}

	public List<Person> loadALlPersons(){
		List<Person> ps = loadObjs();
		return ps;
	}
}
