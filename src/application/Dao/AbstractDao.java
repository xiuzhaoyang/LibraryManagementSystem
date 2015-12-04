package application.Dao;

import java.util.List;

public class AbstractDao <T>{

	protected String TABLE_KEY = "wrongTable";
	
	public AbstractDao(){
		
	}
	public List<T> loadObjs(){
		return Db.readObjects(TABLE_KEY);
		
	}
	
	public void saveObjects(List<T> objs){
		Db.saveObjects(TABLE_KEY, objs);
	}
}
