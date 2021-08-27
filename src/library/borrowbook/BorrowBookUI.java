// Author: Jeel  (I updated some of the content of the file as reviewer told me through mediator)
// Mediator: Mariam
// Reviewer: Prakriti 


package library.borrowbook;
import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UIState  { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };		//uI_STaTe

	private BorrowBookControl control;		//bORROW_bOOK_cONTROL & CoNtRoL
	private Scanner input;				//InPuT
	private UIState state;				//uI_STaTe & StaTe

	
	public BorrowBookUI(BorrowBookControl  control) { 	//bORROW_bOOK_cONTROL
		this.control = control;				//CoNtRoL
		input = new Scanner(System.in);			//InPuT
		state = UISTate.INITIALISED;			//StaTe & uI_STaTe
		control.setUI(this);				//SeT_Ui
	}

	
	private String input(String prompt) {		//iNpUT & PrOmPt
		System.out.print(prompt);		//PrOmPt
		return input.nextLine();		//InPuT
	}	
		
		
	private void output(Object object) {		//OuTpUt & ObJeCt
		System.out.println(object);		//ObJeCt
	}
	
			
	public void setState(UIState state) { 	//SeT_StAtE & uI_STaTe & StAtE
		this.state = state;			//StaTe & StAtE 
	}

	
	public void run() {				//RuN
		output("Borrow Book Use Case UI\n");	//OuTpUt
		
		while (true) {
			
			switch (state) {	//StaTe	
			
			case CANCELLED:
				output("Borrowing Cancelled");	//OuTpUt
				return;

				
			case READY:
				String memberString = input("Swipe member card (press <enter> to cancel): "); 	//MEM_STR change to memberString becasue it is string  & iNpUT
				if (memberString.length() == 0) {		//MEM_STR
					control.cancel();			//CoNtRoL & CaNcEl
					break;
				}
				try {
					int memberID = Integer.valueOf(memberString).intValue();	//MeMbEr_Id & MEM_STR 
					control.swiped(memberID);				//CoNtRoL & SwIpEd & MeMbEr_Id
				}
				catch (NumberFormatException e) {		
					output("Invalid Member Id");		//OuTpUt
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");	//iNpUT
				control.cancel();			//CoNtRoL &  CaNcEl
				break;
			
				
			case SCANNING:
				String bookStringInput = input("Scan Book (<enter> completes): ");		//BoOk_StRiNg_InPuT & iNpUT
				if (bookStringInput.length() == 0) {						//BoOk_StRiNg_InPuT 
					control.complete();							//CoNtRoL & CoMpLeTe
					break;
				}
				try {
					int bookID = Integer.valueOf(bookStringInput).intValue();		//BiD & BoOk_StRiNg_InPuT
					control.scanned(bookID);							//CoNtRoL & ScAnNeD & BiD
					
				} catch (NumberFormatException e) {		
					output("Invalid Book Id");		//OuTpUt
				} 
				break;
					
				
			case FINALISING:
				String answer = input("Commit loans? (Y/N): ");	// AnS & iNpUT
				if (answer.toUpperCase().equals("N")) {		//AnS  
					control.cancel();			//CoNtRoL  & CaNcEl
					
				} else {
					control.commitLoans();				//CoNtRoL & CoMmIt_LoAnS
					input("Press <any key> to complete ");		//iNpUT 
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");  	//OuTpUt
				return;
	
				
			default:
				output("Unhandled state");		//OuTpUt
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state);		//StaTe	
			}
		}		
	}


	public void display(Object object) {	//DiSpLaY
		output(object);			//OuTpUt
	}


}
