package application.models;

import java.io.Serializable;
import java.time.LocalDate;

public class PublicationCopy implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1100L;
	
	private int pId;		//publication Id
	private String pcId;	//publication copyId
	private LocalDate addDate;
	


	public LocalDate getAddDate() {
		return addDate;
	}

	public void setAddDate(LocalDate addDate) {
		this.addDate = addDate;
	}

	private boolean available;

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

//	public static int getNextPcId(){
//		return ++pcAmount;
//	}

//	public static String generateNextPcId(int pId){
//		return pId + "-" + getNextPcId();
//	}

	PublicationCopy(int pId, String pcId, LocalDate addDate, boolean available, Publication publication){
		super();
		this.pId = pId;
		this.pcId = pcId;
		this.addDate = addDate;
		this.available = available;
//		pcAmount++;
	}
}
