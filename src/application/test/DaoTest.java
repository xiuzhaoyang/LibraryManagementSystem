package application.test;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import application.Dao.LibmDao;
import application.models.LibraryMember;

public class DaoTest  {
	
	public static void testWriteDB(){
//		List<LibraryMember> list = new ArrayList<LibraryMember>();
//		LibraryMember lb = new LibraryMember("aa", "bb", null, "111" );
//		list.add(lb);
//		lb = new LibraryMember("cc", "dd", null, "222");
//		list.add(lb);
//		LibmDao d = new LibmDao();
//		d.saveObjects(list);
	}
	
	public static void testReadDB(){
		LibmDao d = new LibmDao();
		List<LibraryMember> list = d.loadObjs();
		
		System.out.println("out size " + list.size());	
		for(LibraryMember l : list){
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
