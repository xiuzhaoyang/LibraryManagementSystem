package application.Dao;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Db {
	public static final String OUTPUT_DIR = System.getProperty("user.dir") + "/storage";
	// + "\\src\\projectstartup\\librarysample\\dataaccess\\storage";
	public static final String DATE_PATTERN = "MM/dd/yyyy";

	public static <T> void saveObjects(String KEY, List<T> objs) {

		ObjectOutputStream out = null;
		try {
			File f = new File(OUTPUT_DIR);
			if(!f.exists()){
				f.mkdir();
			}
			
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, KEY);

			out = new ObjectOutputStream(Files.newOutputStream(path));
			for (T o : objs) {
				out.writeObject(o);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	public static <T> List<T> readObjects(String KEY) {
		ObjectInputStream in = null;
		
		List<T> list = new ArrayList<T>();
		
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, KEY);
			in = new ObjectInputStream(Files.newInputStream(path));
			while(true){
				try{
					list.add((T) in.readObject());
				}catch(EOFException e){
					break;
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return list;
	}
	
}
