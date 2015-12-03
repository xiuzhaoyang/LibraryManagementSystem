package application.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Publication {
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

	public AllowedBorrowDays getAllowedBorrowkDays() {
		return allowedBorrowDays;
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

}
