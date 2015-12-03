package application.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable{
	private int ckrId;
	int mId;
	
	List<CheckoutEntry> enties;

	public CheckoutRecord(){
		enties = new ArrayList<CheckoutEntry>();
		
	}
	public int getmId() {
		return mId;
	}

        public CheckoutRecord(int ckrId, int mId, List<CheckoutEntry> enties){
		this.ckrId = ckrId;
		this.mId = mId;
		this.enties = enties;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public List<CheckoutEntry> getEnties() {
		return enties;
	}

	public void setEnties(List<CheckoutEntry> enties) {
		this.enties = enties;
	}

	
	@Override
	public String toString() {
		
		return "Checkout mid " + mId + " entities size " + enties.size() + " " + enties;
	}
	
	private static final long serialVersionUID = 108L;
}
