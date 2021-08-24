package library.entities;
import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable {
	
	private String title;  //private String tItLe
	private String athor;  //private String AuThOr;
	private String callNo;  //private String CALLNO;
	private int id;         //private int iD;
	
	private enum State { AVAILABLE, ONLOAN, DAMAGED, RESERVED }; //private enum sTaTe { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private State State;    //private sTaTe StAtE;
	
	
	public Book(String author, String title, String callNo, int id) {
		this.author = author;       //this.AuThOr = author;
		this.title = title;       // tITle
		this.callNO = callNo;      // this.CALLNO
		this.id = id;             //this.iD = id;
		this.State = State.Available;  //STate = sTaTe.AVAILABLE
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(id).append("\n")           //changed iD
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
		return State == State.AVAILABLE;  //return State == Sate.AVAILABLE;
	}

		return State == State.AVAILABLE      //return StAtE == sTaTe.AVAILABLE;
	}

	
	public boolean isOn_Loan() {           //boolean iS_On_LoAn()
		return State == State.ON_LOAN;       //return StAtE == sTaTe.ON_LOAN
	}


	public boolean isDamaged() {                //public boolean iS_DaMaGeD
		return State == State.DAMAGED;   //return StAtE == sTaTe.DAMAGED;
	}

	
	public void Borrow() {        //void BoRrOw
		if (State.equals(state.AVAILABLE))    //if (StAtE.equals(sTaTe.AVAILABLE))
			State = State.ON_LOAN;          //StAtE = sTaTe.ON_LOAN;
		
		else 
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", State));  //StAtE
		
		
	}


	public void return(Boolean damaged) {     //public void ReTuRn(boolean DaMaGeD)
		if (State.equals(State.ON_LOAN))    //if (StAtE.equals(sTaTe.ON_LOAN)) 
			if (damaged)                 //if (DaMaGeD)
				State = State.DAMAGED;  //StAtE = sTaTe.DAMAGED;
			
			else 
				State = State.AVAILABLE;    //StAtE = sTaTe.DAMAGED;
			
		
		else 
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", State));    //StAtE
				
	}

	
	public void repair() {                   //void RePaIr
		if (State.equals(State.DAMAGED))   //if (StAtE.equals(sTaTe.DAMAGED)) 
			State = State.AVAILABLE;    //StAtE = sTaTe.AVAILABLE
		
		else 
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", State));   ///StAtE
		
	}


}
