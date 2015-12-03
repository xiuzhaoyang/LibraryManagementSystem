package application.test;

import java.util.List;

import application.Dao.PersonDao;
import application.models.Person;
import application.util.Constants;

public class DaoTest  {
	
	public static void testWriteDB(){
		List<Person> ps =  Constants.getPersonData();

		PersonDao d = new PersonDao();
		d.saveObjects(ps);
		
		
	}
	
	public static void testReadDB(){
		PersonDao d = new PersonDao();
		List<Person> list = d.loadObjs();
		
		System.out.println("out size " + list.size());	
		for(Person l : list){
			System.out.println(l);	
		}
		
	}
	
	public static void testDB(){
		testWriteDB();
		testReadDB();
	}

	public static void main(String [] argv){
		testDB();
	}
	
//	public static void saveLibraryMember(String name,  LibraryMember member) {
//	
//		ObjectOutputStream out = null;
//		try {
//			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, name);
//			
//			out = new ObjectOutputStream(Files.newOutputStream(path));
//			out.writeObject(member);
//			
//		} catch(IOException e) {
//			e.printStackTrace();
//		} finally {
//			if(out != null) {
//				try {
//					out.close();
//				} catch(Exception e) {}
//			}
//		}
//	}
//	
	
}
