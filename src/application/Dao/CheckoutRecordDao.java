package application.Dao;

import java.util.List;

import application.models.CheckoutEntry;
import application.models.CheckoutRecord;

public class CheckoutRecordDao extends AbstractDao<CheckoutRecord>{
	
	public CheckoutRecordDao(){
		super();
		TABLE_KEY = CheckoutRecord.class.getSimpleName();
	}
	
	
	public CheckoutRecord getCheckoutRecordFromPid(int pid){
		List<CheckoutRecord> rds = loadObjs();
		
		if(rds.isEmpty())
			return null;
		
		for(CheckoutRecord r: rds){
			if(r.getmId() == pid){
				return r;
			}
		}
		return null;
	}

	public void addCheckoutRecord(CheckoutRecord cr){
		List<CheckoutRecord> rds = loadObjs();
		boolean isFind = false;
		for(CheckoutRecord ce : rds){
			if(cr.getmId() == ce.getmId()){
				isFind = true;
			}
		}
		
		if(!isFind){
			rds.add(cr);
			saveObjects(rds);
		}
	}
	
	public void saveCheckRecord(CheckoutRecord cr){
		List<CheckoutRecord> rds = loadObjs();
		
		CheckoutRecord oldOne  = null;
		
		for(CheckoutRecord r: rds){
			if(r.getmId() == cr.getmId()){
				oldOne = r;
				break;
			}
		}
		
		if(oldOne != null){
			rds.remove(oldOne);
		}
		
		rds.add(cr);
		
		saveObjects(rds);
	}
	
}
