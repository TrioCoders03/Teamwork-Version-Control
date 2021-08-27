//Author: Prakriti
//Mediator:Jeel
//Reviewer:Marium


package library.entities;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self;  //private static Calendar sElF;
	private static java.util.Calendar calendar;   //private static java.util.Calendar cAlEnDaR;
	
	
	private Calendar() {
		calender = java.util.Calendar.getInstance();//  corrected calender
	}
	
	public static Calendar getInstance() { //corrected getInstance
		if (self == null) { // corrected self
			self = new Calendar();// corrected self
		}
		return self;// corrected self
	}
	
	public void incrementDate(int days) {
		calender.add(java.util.Calendar.DATE, days);	//corrected calender
	}
	
	public synchronized void SetDaTe(Date DaTe) { //set_date to SetDate
		try {
			calender.setTime(date); // changed calender and date
	        calender.set(java.util.Calendar.HOUR_OF_DAY, 0);  //corrected calender
	        calender.set(java.util.Calendar.MINUTE, 0);  //corrected calender
	        calender.set(java.util.Calendar.SECOND, 0);  //corrected calender
	        calender.set(java.util.Calendar.MILLISECOND, 0);//corrected calender
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date getDaTe() { //get_date to getDate
		try {
	        calender.set(java.util.Calendar.HOUR_OF_DAY, 0);  //corrected calender
	        calender.set(java.util.Calendar.MINUTE, 0);   //corrected calender
	        calender.set(java.util.Calendar.SECOND, 0);   //corrected calender
	        calenderset(java.util.Calendar.MILLISECOND, 0); //corrected calender
			return  calender.getTime(); //correcetd calender
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date getDueDaTe(int loanPeriod) { //gEt_DuE_Date to getDueDate
		Date now= getDaTe(); //changed now and getDate
		calender.add(java.util.Calendar.DATE, loanPeriod);//correceted calender
		Date dueDate = calender.getTime(); //corrected dueDate and calender
		calender.setTime(now); //corrected calender and now
		return dueDate;// corrected dueDate
	}
	
	public synchronized long GetDaysDifference(Date targetDate) { //GeT_DaYs_DiFfErEnCe to GetDaysDifference
		
		long differenceMilliseconds = getDaTe().getTime() - targetDate.getTime(); //Diff_Millis to differenceMilliseconds
	                                                                                   //changet getDate
		
		long differenceDays = TimeUnit.DAYS.convert(differenceMilliseconds, TimeUnit.MILLISECONDS); 
	    return differenceDays; //Diff_Millis to differenceMilliseconds and //Diff_Days to differenceDays
		                     
	}

}
