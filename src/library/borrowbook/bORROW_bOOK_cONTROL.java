// Author: Jeel  (I updated some of the content of the file as reviewer told me through mediator)
// Mediator: Mariam
// Reviewer: Prakriti 

package library.borrowbook;
import java.util.ArrayList;
import java.util.List;

import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;

public class BorrowBookControl { 	//bORROW_bOOK_cONTROL
	
	private BorrowBookUI ui;	//uI
	
	private Library library;	//lIbRaRy
	private Member member;		//mEmBeR
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };  //CONTROL_STATE
	private ControlState state;	//CONTROL_STATE & sTaTe
	
	private List<Book> pendingList;	//pEnDiNg_LiSt
	private List<Loan> completedList;	//cOmPlEtEd_LiSt
	private Book book;			//bOoK
	
	
	public BorrowBookControl() {			//bORROW_bOOK_cONTROL
		this.library = Library.getInstance();	//lIbRaRy & GeTiNsTaNcE
		state = ControlState.INITIALISED;	//sTaTe & CONTROL_STATE
	}
	
	
	public void setUi(BorrowBookUI ui) {			//setUi & Ui
		if (!state.equals(ControlState.INITIALISED)) 	//sTaTe & CONTROL_STATE 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.ui = ui;			//Ui & uI
		ui.setState(BorrowBookUI.UiState.READY);	//Ui & SeT_StAtE & uI_STaTe
		state = ControlState.READY;			//sTaTe & CONTROL_STATE
	}

		
	public void swiped(int memberId) {			//SwIpEd & mEmBeR_Id
		if (!state.equals(ControlState.READY)) 		//sTaTe & CONTROL_STATE
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = library.getMember(memberId);		//mEmBeR & lIbRaRy & gEt_MeMbEr & mEmBeR_Id
		if (member == null) {				//mEmBeR
			ui.Display("Invalid memberId");		//uI & DiSpLaY
			return;
		}
		if (library.canMemberBorrow(member)) {			//lIbRaRy & cAn_MeMbEr_BoRrOw & mEmBeR
			pendingList = new ArrayList<>();		//pEnDiNg_LiSt 
			ui.setState(BorrowBookUI.UiState.SCANNING);	//uI & SeT_StAtE & uI_STaTe
			state = ControlState.SCANNING; 			//sTaTe & CONTROL_STATE
		}
		else {
			ui.Display("Member cannot borrow at this time");	//uI & DiSpLaY
			ui.setState(BorrowBookUI.UiState.RESTRICTED); 		//uI & SeT_StAtE & uI_STaTe
		}
	}
	
	
	public void Scanned(int bookId) {		//ScAnNeD & bOoKiD 
		book = null;				//bOoK 
		if (!state.equals(ControlState.SCANNING))	// sTaTe & CONTROL_STATE
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
			
		book = library.getBook(bookId);		//bOoK & lIbRaRy & gEt_BoOk & bOoKiD
		if (book == null) {			//bOoK
			ui.Display("Invalid bookId");	//uI & DiSpLaY 
			return;
		}
		if (!book.isAvailable()) {			//bOoK & iS_AvAiLaBlE 
			ui.Display("Book cannot be borrowed");	//uI & DiSpLaY 
			return;
		}
		pendingList.add(book);			//pEnDiNg_LiSt & bOoK
		for (Book B : pendingList) 		//pEnDiNg_LiSt 
			ui.Display(B.toString());	//uI & DiSpLaY
		
		//if (lIbRaRy.gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr(mEmBeR) - pEnDiNg_LiSt.size() == 0) {
		if (library.getNumberOfLoansRemainingForMember(member) - pendingList.size() == 0) {	
			ui.Display("Loan limit reached");	//uI & DiSpLaY 
			Complete();				//CoMpLeTe 
		}
	}
	
	
	public void CoMpLeTe() {
		if (pEnDiNg_LiSt.size() == 0) 
			CaNcEl();
		
		else {
			uI.DiSpLaY("\nFinal Borrowing List");
			for (Book bOoK : pEnDiNg_LiSt) 
				uI.DiSpLaY(bOoK.toString());
			
			cOmPlEtEd_LiSt = new ArrayList<Loan>();
			uI.SeT_StAtE(BorrowBookUI.uI_STaTe.FINALISING);
			sTaTe = CONTROL_STATE.FINALISING;
		}
	}


	public void CoMmIt_LoAnS() {
		if (!sTaTe.equals(CONTROL_STATE.FINALISING)) 
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
			
		for (Book B : pEnDiNg_LiSt) {
			Loan lOaN = lIbRaRy.iSsUe_LoAn(B, mEmBeR);
			cOmPlEtEd_LiSt.add(lOaN);			
		}
		uI.DiSpLaY("Completed Loan Slip");
		for (Loan LOAN : cOmPlEtEd_LiSt) 
			uI.DiSpLaY(LOAN.toString());
		
		uI.SeT_StAtE(BorrowBookUI.uI_STaTe.COMPLETED);
		sTaTe = CONTROL_STATE.COMPLETED;
	}

	
	public void CaNcEl() {
		uI.SeT_StAtE(BorrowBookUI.uI_STaTe.CANCELLED);
		sTaTe = CONTROL_STATE.CANCELLED;
	}
	
	
}
