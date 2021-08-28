//Author :Prakriti
//Reviewr :Marium
//Mediator :Jeel

package library.payfine;
import library.entities.Library;
import library.entities.Member;

public class PayFineControl  { //pAY_fINE_cONTROL=PayFineControl
	
	private PayFineUI ui //Ui;
	private enum  ControlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; //cOnTrOl_sTaTe 
	private    ControlState state; //cOnTrOl_sTaTe StAtE  ;
	
	private Library library //LiBrArY;
	private Member member //MeMbEr;


	public PayFineControl () { //pAY_fINE_cONTROL
		this.library //LiBrArY = Library.GeTiNsTaNcE(); //LiBrArY
		state = controlState.INITIALISED;    //StAtE = cOnTrOl_sTaTe.INITIALISED;
	}
	
	
	  public void setUI(PayFineUI ui) { //public void SeT_uI(PayFineUI uI) 
		if (!state.equals(ControlState.INITIALISED)) { //StAtE.equals(cOnTrOl_sTaTe
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.Ui = ui;//uI
		ui.set_State(PayFineUI.uiState.READY);
		state = ControlState.READY;	//StAtE = cOnTrOl_sTaTe.READY	
	}


	public void cardSwiped(int memberId)  {   //CaRd_sWiPeD(int MeMbEr_Id
		if (!state.equals(ControlState.READY))  //StAtE.equals(cOnTrOl_sTaTe.READY
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
			
		member = library.getMember(memberId);  //MeMbEr = LiBrArY.gEt_MeMbEr(MeMbEr_Id)
		
		if (member == null) {   //MeMbEr
			ui.display("Invalid Member Id"); //Ui.diSplAY
			return;
		}
		ui.display(member.toString());  //Ui.DiSplAY(MeMbEr.toString()
		ui.setState(PayFineUI.uiState.PAYING);   //Ui.SeT_StAtE(PayFineUI.uI_sTaTe.PAYING)
		state = ControlState.PAYING;	  //StAtE = cOnTrOl_sTaTe.PAYING;
	}
	
	
	public void  cancel() { // CaNcEl
	ui.setState(PayFineUI.uiState.CANCELLED); //Ui.SeT_StAtE(PayFineUI.uI_sTaTe.CANCELLED);
		state = ControlState.CANCELLED;   //StAtE = cOnTrOl_sTaTe
	}


	public double payFine(double amount) {  // PaY_FiNe(double AmOuNt)
		if (!state.equals(ControlState.PAYING))   //state.equals(ControlState
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
			
		double change = member.payFine(amount);		 //double Change = MeMbEr.PaY_FiNe(AmOuNt);
		if (change > 0) 
		ui.display(String.format("Change: $%.2f", change));	//Ui.DiSplAY(String.format("Change: $%.2f", ChAnGe)
		
		ui.display(member.toString());	//Ui.DiSplAY(MeMbEr
		ui.setState(PayFineUI.uiState.COMPLETED);	//Ui.SeT_StAtE(PayFineUI.uI_sTaTe
		state = ControlState.COMPLETED;	 //StAtE = cOnTrOl_sTaTe
		return change; //ChAnGe
	}
	


}
