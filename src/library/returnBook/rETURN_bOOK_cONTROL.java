package library.returnBook;
import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;

public class ReturnBookControl {                                                 //rETURN_bOOK_cONTROL

	private ReturnBookUI Ui;
	private enum ControlState { INITIALISED, READY, INSPECTING };           //cOnTrOl_sTaTe
	private ControlState state;                                           //cOnTrOl_sTaTe sTaTe
	
	private Library library;                        //lIbRaRy
	private Loan currentLoan;                        //CurrENT_loan


	public ReturnBookControl() {                          //rETURN_bOOK_cONTROL
		this.library = Library.getInstance();         //this.lIbRaRy = Library.GeTiNsTaNcE
		state = ControlState.INITIALISED;            //sTaTe = cOnTrOl_sTaTe.INITIALISED
	}
	
	
	public void setUi(ReturnBookUI uI) {                   //void sEt_uI
		if (!state.equals(ControlState.INITIALISED))    //sTaTe.equals(cOnTrOl_sTaTe.INITIALISED
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		
		this.Ui = ui;                                     //uI
		ui.setState(ReturnBookUI.uiState.READY);              //uI.sEt_sTaTe(ReturnBookUI.uI_sTaTe.READY);
		state = ControlState.READY;	                     //sTaTe = cOnTrOl_sTaTe	
	}


	public void bookScanned(int bookId) {                          //bOoK_sCaNnEd(int bOoK_iD
		if (!state.equals(ControlState.READY))                   //sTaTe.equals(cOnTrOl_sTaTe.READY
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		
		Book currentBook = library.getBook(bookId);                //cUrReNt_bOoK = lIbRaRy.gEt_BoOk(bOoK_iD);
		
		if (currentBook == null) {                                //cUrReNt_bOoK 
			ui.display("Invalid Book Id");                   //Ui.DiSpLaY
			return;
		}
		if (!cUrReNt_bOoK.iS_On_LoAn()) {
			Ui.DiSpLaY("Book has not been borrowed");
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);             //CurrENT_loan = lIbRaRy.GeT_LoAn_By_BoOkId(bOoK_iD);	
		double OverDueFine = 0.0;                                  // Over_Due_Fine
		if (currentLoan.isOverDue())                            //CurrENT_loan.Is_OvEr_DuE
			OverDueFine = library.calculateOverDueFine(currentLoan);                 //Over_Due_Fine = lIbRaRy.CaLcUlAtE_OvEr_DuE_FiNe(CurrENT_loan)
		
		ui.dispaly("Inspecting");                                         //Ui.DiSpLaY("Inspecting
		ui.display(currentBook.toString());                             //Ui.DiSpLaY(cUrReNt_bOoK.toStrin
		ui.display(currentLoan.toString());                            //Ui.DiSpLaY(CurrENT_loan
		
		if (currentLoan.isOverDue())                                //CurrENT_loan.Is_OvEr_DuE
			ui.display(String.format("\nOverDue fine : $%.2f", overDueFine));               //Ui.DiSpLaY(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));
		
		ui.setState(ReturnBookUI.uiState.INSPECTING);                          //Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.INSPECTING)
		state = controlState.INSPECTING;	                                  //sTaTe = cOnTrOl_sTaTe	
	}


	public void scanningComplete) {                              //void sCaNnInG_cOmPlEtE
		if (!state.equals(ControlState.READY))              //sTaTe.equals(cOnTrOl_sTaTe.
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
			
		ui.setstate(ReturnBookUi.uiState.COMPLETED);	                   //Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.COMPLETED)	
	}


	public void dischargeLoan(boolean isDamaged) {                          //dIsChArGe_lOaN(boolean iS_dAmAgEd)
		if (!state.equals(ControlState.INSPECTING))                    //sTaTe.equals(cOnTrOl_sTaTe.INSPECTING) 
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		
		library.dischargeLoan(currentLoan, isDamaged);              //lIbRaRy.DiScHaRgE_LoAn(CurrENT_loan, iS_dAmAgEd)
		currentLoan = null;                                        //CurrENT_loan = null
		ui.setState(ReturnBookUi.uiState.READY);                 //Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.READY);  
		state = controlState.READY;	                         //sTaTe = cOnTrOl_sTaTe.READY			
	}


}
