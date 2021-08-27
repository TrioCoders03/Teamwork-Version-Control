//Author : Prakriti
//Reviwer: Marium
//Mediator : Jeel


package library.fixbook;
import java.util.Scanner;


public class FixBookUi {  //FixBookUI

	public static enum uiState { INITIALISED, READY, FIXING, COMPLETED };//uI_sTaTe 

	private FixBookControl control; //fIX_bOOK_cONTROL CoNtRoL
	private Scanner input; //InPuT
	private uiState state; //uI_sTaTe StAtE

	
	public  FixBookUI(FixBookControl control) { //FixBookUI(fIX_bOOK_cONTROL CoNtRoL) 
		this.control = control; //this.CoNtRoL = CoNtRoL;
		input = new Scanner(System.in); //InPuT
		state = uiState.INITIALISED; //StAtE = uI_sTaTe.INITIALISED
		control.setUi(this); //CoNtRoL.SeT_Ui
	}


	public void setState(uiState state) { //SeT_StAtE(uI_sTaTe state) 
		this.state = state;
	}

	
	public void run() { //RuN
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) { //StAtE
			
			case READY:
				String  bookEntryString = input("Scan Book (<enter> completes): "); //BoOk_EnTrY_StRiNg = iNpUt
				if (bookEntryString.length() == 0)  //BoOk_EnTrY_StRiNg
					control.scanningComplete(); //CoNtRoL.SCannING_COMplete();
				
				else {
					try {
						int bookId = Integer.valueOf(bookEntryString).intValue(); // BoOk_Id = Integer.valueOf(BoOk_EnTrY_StRiNg).intValue();
						control.bookScanned(bookId); //CoNtRoL.BoOk_ScAnNeD(BoOk_Id);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String ans = input("Fix Book? (Y/N) : ");//String AnS = iNpUt
				boolean fix = false;
				if (ans.toUpperCase().equals("Y")) //AnS.toUpperCase()
					fix = true;
				
				control.fixBook(fix); //CoNtRoL.FiX_BoOk(FiX);
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state); //StaTe	
			
			}		
		}
		
	}

	
	private String input(String prompt) {  //iNPut
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) { //oUTPut
		System.out.println(object);
	}
	

	public void display(Object object) { //dIsPlAy
		output (object);
	}
	
	
}
