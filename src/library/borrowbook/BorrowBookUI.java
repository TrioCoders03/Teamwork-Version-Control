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
					int MeMbEr_Id = Integer.valueOf(MEM_STR).intValue();
					CoNtRoL.SwIpEd(MeMbEr_Id);
				}
				catch (NumberFormatException e) {
					OuTpUt("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				iNpUT("Press <any key> to cancel");
				CoNtRoL.CaNcEl();
				break;
			
				
			case SCANNING:
				String BoOk_StRiNg_InPuT = iNpUT("Scan Book (<enter> completes): ");
				if (BoOk_StRiNg_InPuT.length() == 0) {
					CoNtRoL.CoMpLeTe();
					break;
				}
				try {
					int BiD = Integer.valueOf(BoOk_StRiNg_InPuT).intValue();
					CoNtRoL.ScAnNeD(BiD);
					
				} catch (NumberFormatException e) {
					OuTpUt("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String AnS = iNpUT("Commit loans? (Y/N): ");
				if (AnS.toUpperCase().equals("N")) {
					CoNtRoL.CaNcEl();
					
				} else {
					CoNtRoL.CoMmIt_LoAnS();
					iNpUT("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				OuTpUt("Borrowing Completed");
				return;
	
				
			default:
				OuTpUt("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + StaTe);			
			}
		}		
	}


	public void DiSpLaY(Object object) {
		OuTpUt(object);		
	}


}
