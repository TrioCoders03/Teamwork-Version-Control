package library.entities;
import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable {
	
	private String title;  //private String tItLe
	private String athor;  //private String AuThOr;
	private String callNo;  //private String CALLNO;
	private int id;         //private int iD;
	
	private enum State { AVAILABLE, ONLOAN, DAMAGED, RESERVED }; //private enum sTaTe { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private State state;    //private sTaTe StAtE;
	
	
	public Book(String author, String title, String callNo, int id) {
		this.author = author;       //this.AuThOr = author;
		this.title = title;       // tITle
		this.callNo = callNo;      // this.CALLNO
		this.id = id;             //this.iD = id;
		this.state = State.Available;  //STate = sTaTe.AVAILABLE
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(Id).append("\n")           //changed iD
		  .append("  Title:  ").append(title).append("\n")    //tItLe
		  .append("  Author: ").append(author).append("\n")   //AuThOr
		  .append("  CallNo: ").append(callNo).append("\n")   //CALLNO
		  .append("  State:  ").append(state);                //StAtE
		
		return sb.toString();
	}

	public Integer getId() {   //change gEtId
		return id;         //return iD
	}

	public String getTitle() {      // change gEtTiTlE
		return title;          //return tItLe;
	}


	
	public boolean isAvailable() {  //public boolean iS_AvAiLaBlE()
		return state == state.AVAILABLE;  //return State == Sate.AVAILABLE;
	}

		return state == state.AVAILABLE      //return StAtE == sTaTe.AVAILABLE;
	}

	
	public boolean isOnLoan() {           //boolean iS_On_LoAn()
		return state == state.ONLOAN;       //return StAtE == sTaTe.ON_LOAN
	}


	public boolean isDamaged() {                //public boolean iS_DaMaGeD
		return state == state.DAMAGED;   //return StAtE == sTaTe.DAMAGED;
	}

	
	public void borrow() {        //void BoRrOw
		if (state.equals(state.AVAILABLE))    //if (StAtE.equals(sTaTe.AVAILABLE))
			state = state.ONLOAN;          //StAtE = sTaTe.ON_LOAN;
		
		else 
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", State));  //StAtE
		
		
	}


	public void return(Boolean DAMAGED) {     //public void ReTuRn(boolean DaMaGeD)
		if (state.equals(State.ONLOAN))    //if (StAtE.equals(sTaTe.ON_LOAN)) 
			if (DAMAGED)                 //if (DaMaGeD)
				state = state.DAMAGED;  //StAtE = sTaTe.DAMAGED;
			
			else 
				state = state.AVAILABLE;    //StAtE = sTaTe.DAMAGED;
			
		
		else 
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", State));    //StAtE
				
	}

	
	public void repair() {                   //void RePaIr
		if (state.equals(state.DAMAGED))   //if (StAtE.equals(sTaTe.DAMAGED)) 
			state = state.AVAILABLE;    //StAtE = sTaTe.AVAILABLE
		
		else 
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", State));   ///StAtE
		
	}


}
