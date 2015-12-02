package application.Dao;

import application.models.Person;

public class PersonDao extends AbstractDao<Person>{
	public PersonDao(){
		super();
		TABLE_KEY = Person.class.getSimpleName();
	}

}
