
//Author: Prakriti
//Mediator : Jeel
//Reviewer: Marium
package library.fixbook;
import library.entities.Book;
import library.entities.Library;

public class FixBookControl { //fIX_bOOK_cONTROL 
	
	private FixBookUI Ui;
	private enum ControlState  { INITIALISED, READY, FIXING }; //CoNtRoL_StAtE
	private  ControlState  state; //CoNtRoL_StAtE StAtE;
	
	private Library library //LiBrArY;
	private Book currentBook; //CuRrEnT_BoOk


	public FixBookControl()  { //fIX_bOOK_cONTROL()
		this.libary = Library.GetInstance(); //this.LiBrArY = Library.GeTiNsTaNcE()
		state = ControlState.INITIALISED; //StAtE = CoNtRoL_StAtE.INITIALISED
	}
	
	
public void  SetUI(FixBookUI ui)  { //SeT_Ui(FixBookUI ui) 
		if (!state.equals(ControlState.INITIALISED)) //StAtE.equals(CoNtRoL_StAtE.
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
			
		this.ui = ui; //Ui
		ui.SetState(FixBookUI.uiState.READY); //SeT_StAtE(FixBookUI.uI_sTaTe.READY)
		state = ControlState.READY; //StAtE = CoNtRoL_StAtE.READY	
	}


	public void BookScanned(int BookId) { //BoOk_ScAnNeD(int BoOkId) 
		if (!state.equals(ControlState.READY))  //StAtE.equals(CoNtRoL_StAtE
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
			
		currentBook = libary.getBook(BookId); //CuRrEnT_BoOk = LiBrArY.gEt_BoOk(BoOkId)
		
		if (currentBook == null) { //CuRrEnT_BoOk
			ui.dIsPlAy("Invalid bookId"); //Ui.dIsPlAy
			return;
		}
		if (!currentBook.isDamaged()) { //CuRrEnT_BoOk.iS_DaMaGeD
			ui.display("Book has not been damaged");
			return;
		}
		 ui.display(currentBook.toString()); //Ui.dIsPlAy(CuRrEnT_BoOk.toString())
		  ui.setState(FixBookUI.uiState.FIXING); //Ui.SeT_StAtE(FixBookUI.uI_sTaTe.FIXING)
		  state = ControlState.FIXING; //StAtE = CoNtRoL_StAtE.FIXING	
	}


	public void FixBook(boolean mustFix)  { //FiX_BoOk(boolean mUsT_FiX)
		if (!state.equals(ControlState.FIXING))  //StAtE.equals(CoNtRoL_StAtE
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
			
		if (mustFix) { //(mUsT_FiX) 
			libary.repairBook(currentBook); //LiBrArY.RePaIr_BoOk(CuRrEnT_BoOk)
		
		currentBook  = null; //CuRrEnT_BoOk
		ui.setState(FixBookUI.uiState.READY); //Ui.SeT_StAtE(FixBookUI.uI_sTaTe.READY)
		state = ControlState.READY; //StAtE = CoNtRoL_StAtE.READY	
	}

	
	public void scanningComplete  { //SCannING_COMplete() 
		if (!state.equals(ControlState.READY)) //StAtE.equals(CoNtRoL_StAtE
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
			
		ui.setState(FixBookUI.uiState.COMPLETED); //Ui.SeT_StAtE(FixBookUI.uI_sTaTe.COMPLETED);	
	}

}
