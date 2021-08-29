package library.returnBook;
import java.util.Scanner;


public class ReturnBookUi {

	public static enum uiState { INITIALISED, READY, INSPECTING, COMPLETED };         //uIsTaTe

	private ReturnBookControl control;                                           // rETURN_bOOK_cONTROL CoNtRoL
	private Scanner input;                                                          //iNpUt
	private uiState state;                                                        //uI_sTaTe StATe

	
	public ReturnBookUI(returnBookControl control) {                            //rETURN_bOOK_cONTROL cOnTrOL
		this.control = control;                                          //CoNtRoL = cOnTrOL
		input = new Scanner(System.in);                                  //iNpUt
		state = uiState.INITIALISED;                                 //StATe = uI_sTaTe
		control.setUi(this);                                        //cOnTrOL.sEt_uI
	}


	public void run() {	                                        // RuN	
		output("Return Book Use Case UI\n");                     //oUtPuT
		
		while (true) {
			
			switch (state) {                              //StATe
			
			case INITIALISED:
				break;
				
			case READY:
				String bookInputString = input("Scan Book (<enter> completes): ");             //BoOk_InPuT_StRiNg = iNpUt
				if (bookInputString.length() == 0)                                 //BoOk_InPuT_StRiNg.length
					control.scanningComplete();                                 //CoNtRoL.sCaNnInG_cOmPlEtE
				
				else {
					try {
						int bookId = Integer.valueOf(bookInputString).intValue();            //int Book_Id = Integer.valueOf(BoOk_InPuT_StRiNg)
						control.bookScanned(bookId);                                  //CoNtRoL.bOoK_sCaNnEd(Book_Id
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");                                 //oUtPuT
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input ("Is book damaged? (Y/N): ");                 //AnS = iNpUt
				boolean isDamamged = false;                                        //Is_DAmAgEd
				if (ans.toUpperCase().equals("Y")) 	                //AnS.to				
					isDamaged = true;                              //Is_DAmAgEd 
				
				control.dischargeLoan(isDamaged);                   //CoNtRoL.dIsChArGe_lOaN(Is_DAmAgEd
			
			case COMPLETED:
				output("Return processing complete");                   //oUtPuT
				return;
			
			default:
				output("Unhandled state");                             //oUtPuT
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);		//StATe	
			}
		}
	}

	
	private String input(String prompt) {                //iNpUt(String PrOmPt
		System.out.print(prompt);                   //PrOmPt
		return input.nextLine();                    //iNpUt
	}	
		
		
	private void output(Object object) {                     //oUtPuT(Object ObJeCt
		System.out.println(object);                     //ObJeCt
	}
	
			
	public void display(Object object) {                //DiSpLaY
		output(object);                         //oUtPuT
	}
	
	public void setState(uiState state) {              //sEt_sTaTe(uI_sTaTe state
		this.state = state;                         //StATe
	}

	
}
