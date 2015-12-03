package application.Dao;

import application.models.Publication;

import java.util.List;

/**
 * Created by su on 12/3/15.
 */
public class PublicationDao extends AbstractDao {

    public PublicationDao(){
        super();
        TABLE_KEY = PublicationDao.class.getSimpleName();
    }

    public List<Publication> loadAllPublication(){
        List<Publication>  publicationList = this.loadObjs();
        return publicationList;
    }

}
