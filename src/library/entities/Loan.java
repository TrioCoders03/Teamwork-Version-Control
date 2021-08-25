package library.entities;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable {
	
	public static enum LOAN_STATE { CURRENT, OVER_DUE, DISCHARGED };      //enum lOaN_sTaTe
	
	private int LoanId;                   //LoAn_Id
	private Book book;                    //BoOk
	private Member member;                //MeMbEr;
	private Date date;                    //DaTe
	private LoanState state;              //lOaN_sTaTe StAtE

	
	public Loan(int loanId, Book booK, Member member, Date dueDate) {       //change Book bOoK, Member mEmBeR, Date DuE_dAtE
		this.loAn_Id = loanId;                                        //this.LoAn_Id = loanId
		this.book = book;                                             //this.BoOk = bOoK
		this.member = member;                                         //this.MeMbEr = mEmBeR
		this.date = dueDate;                                         //this.DaTe = DuE_dAtE;
		this.state = loanState.CURRENT;                             //this.StAtE = lOaN_sTaTe.CURRENT
	}

	
	public void checkOverDue() {                                         // void cHeCk_OvEr_DuE
		if (state == loanState.CURRENT &&                            //if (StAtE == lOaN_sTaTe.CURRENT
			Calendar.getInstance().getDate().after(date))       //Calendar.gEtInStAnCe().gEt_DaTe().after(DaTe)) 
			this.state = loanState.OVER_DUE;	            //this.StAtE = lOaN_sTaTe.OVER_DUE;		
		
	}

	
	public boolean isOverDue() {                                   //boolean Is_OvEr_DuE
		return state == loanState.OVER_DUE;                   //return StAtE == lOaN_sTaTe.OVER_DUE;
	}

	
	public integer getId() {                //Integer GeT_Id
		return loanId;                 //return LoAn_Id
	}


	public date GetDuEDate() {           //public Date GeT_DuE_DaTe
		return date;                 //return DaTe
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");               

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(loanId).append("\n")                        //LoAn_Id
		  .append("  Borrower ").append(member.getID()).append(" : ")           //MeMbEr.GeT_ID
		  .append(member.getlastName()).append(", ").append(member.getFirstName()).append("\n")   //MeMbEr.GeT_LaSt_NaMe   //append(MeMbEr.GeT_FiRsT_NaMe
		  .append("  Book ").append(book.getId()).append(" : " )                                  //append(BoOk.gEtId())
		  .append(book.getTitle()).append("\n")                                                 //append(BoOk.gEtTiTlE())
		  .append("  DueDate: ").append(sdf.format(date)).append("\n")                         //format(DaTe))
		  .append("  State: ").append(state);	                                                //StAtE	
		return sb.toString();
	}


	public Member getMember() {                   //GeT_MeMbEr
		return member;                        // MeMbEr
	}


	public Book getBook() {                  //Book GeT_BoOk
		return book;                     //return BoOk
	}


	public void discharge() {                 // void DiScHaRgE
		State = loanState.DISCHARGED;	   //StAtE = lOaN_sTaTe.DISCHARGED	
	}

}
