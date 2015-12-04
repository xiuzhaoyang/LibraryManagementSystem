package application.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Publication implements Serializable{
	private static final long serialVersionUID = 1101L;
	
	private int pId;
	private String title;
	private String ISBN;
	private List<Author> authors;
	private AllowedBorrowDays allowedBorrowDays;
	private LocalDate addDate;

	private List<PublicationCopy> publicationCopies;
	private PublicationType publicationType;
	private int pcAmount;

	public AllowedBorrowDays getAllowedBorrowDays() {
		return allowedBorrowDays;
	}

	public void setAllowedBorrowDays(AllowedBorrowDays allowedBorrowDays) {
		this.allowedBorrowDays = allowedBorrowDays;
	}

	public LocalDate getAddDate() {
		return addDate;
	}

	public void setAddDate(LocalDate addDate) {
		this.addDate = addDate;
	}

	public List<PublicationCopy> getPublicationCopies() {
		return publicationCopies;
	}


	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public List<Author> getAuthors() {
		return authors;
	}



	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public void setAllowedBorrowkDays(AllowedBorrowDays allowedBorrowDays) {
		this.allowedBorrowDays = allowedBorrowDays;
	}

	public final List<PublicationCopy> getpublicationCopies() {
		return publicationCopies;
	}

	public final void setPublicationCopies(List<PublicationCopy> publicationCopies) {
		this.publicationCopies = publicationCopies;
	}

	public PublicationType getPublicationType() {
		return publicationType;
	}

	public void setPublicationType(PublicationType publicationType) {
		this.publicationType = publicationType;
	}

	public void addCopy(LocalDate addDate, Publication publication){
		pcAmount++;
		String nextPcId = this.pId + "-" + pcAmount;
		PublicationCopy cp = new PublicationCopy(pId, nextPcId, addDate, true, this);
		this.publicationCopies.add(cp);
	}

	Publication(){
		publicationCopies = new ArrayList<PublicationCopy>();
	}

	public Publication(int pId, LocalDate addDate, String title, String ISBN, List<Author> authors, AllowedBorrowDays allowedBorrowDays, PublicationType publicationType){
		this();
		this.pId = pId;
		this.addDate = addDate;
		this.title = title;
		this.ISBN = ISBN;
		this.authors = authors;
		this.allowedBorrowDays = allowedBorrowDays;
//		this.publicationCopies = publicationCopies;
		this.publicationType = publicationType;
		this.addCopy(addDate, this);
	}


	public String getAuthorString(){
		String tmpStr = "";
		for(Author author : this.authors){
			if(tmpStr.length() != 0){
				tmpStr += ",";

			}

			tmpStr += author.getFirstName()+ " " + author.getLastName();
		}
		return tmpStr;
	}

	public int getCopyCount(){
		return  this.publicationCopies.size();
	}

	public int getAvailableCount(){
		int i = 0;
		for(PublicationCopy copy : this.publicationCopies){
			if(copy.isAvailable()){
				i++;
			}
		}
		return  i;
	}

	@Override
	public String toString() {
	
		return "book id " + pId;
	}
}
