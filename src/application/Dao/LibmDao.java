package application.Dao;

import application.models.LibraryMember;

public class LibmDao extends AbstractDao<LibraryMember>{
	
	public LibmDao(){
		super();
		TABLE_KEY = LibraryMember.class.getSimpleName();
	}
	
	
}
