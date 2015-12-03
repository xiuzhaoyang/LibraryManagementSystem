package application.Dao;

import application.models.Publication;

import java.util.List;

/**
 * Created by su on 12/3/15.
 */
public class PublicationDao extends AbstractDao<Publication> {

    public PublicationDao(){
        super();
        TABLE_KEY = Publication.class.getSimpleName();
    }

    public List<Publication> loadAllPublication(){
        List<Publication>  publicationList = this.loadObjs();
        return publicationList;
    }

    public Publication getPublicationByPid(int pid){
    	List<Publication>  publicationList = this.loadObjs();
    	
    	for(Publication p: publicationList){
    		if(pid == p.getpId()){
    			return p;
    		}
    	}
    	
    	return null;
    }
}
