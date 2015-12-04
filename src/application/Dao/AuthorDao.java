package application.Dao;

import application.models.Author;
import application.models.LibraryMember;

import java.util.List;

/**
 * Created by su on 12/3/15.
 */
public class AuthorDao extends AbstractDao<Author> {

    public AuthorDao(){
        super();
        TABLE_KEY = Author.class.getSimpleName();
    }

    public List<Author> loadAllAuthor(){
     return null;
    }
}

