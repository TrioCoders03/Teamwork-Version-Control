//Author : Prakriti 
//Reviewed : Marium
//Mediator :Jeel

package library.payfine;
import java.util.Scanner;


public class PayFineUI {


	public static enum UIState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; //uIsTate

	private  payFineControl control; //pAY_fINE_cONTROL CoNtRoL
	private Scanner input;
	private  UIState State;  //uI_sTaTe StAtE

	
	public  PayFineUI(payFineConrol control) { //PayFineUI(pAY_fINE_cONTROL control) 
		this.control  = control;//cOntrol
		input = new Scanner(System.in);
		State = UIState.INITIALISED; //sTate
		 control.setUI(this);  //control.SeT_uI
	}
	
	
	public void  setState(UIState State) { //SeT_StAtE(uI_sTaTe state) 
		this.state = state;  //sTatE
	}


	public void Run() { //RuN
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) { //sTaTe
			
			case READY:
				String memberString = input("Swipe member card (press <enter> to cancel): "); //mem_String
				if (memberString.length() == 0) {
					 control.Cancel(); //CoNtRoL.CaNcEl();
					break;
				}
				try {
					int memberId = Integer.valueOf(memberString).intValue(); //Member
					 control.cardSwiped(memberId); //CoNtRoL.CaRd_sWiPeD(Member_ID)
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double Amount = 0; //AmouNT
				String amountString = input("Enter amount (<Enter> cancels) : "); //Amt_String
				if (amountString.length() == 0) {
					control.Cancel(); //CoNtRoL.CaNcEl()
					break;
				}
				try {
					Amount = Double.valueOf(amountString).doubleValue(); //Amt_String
				}
				catch (NumberFormatException e) {}
				if (Amount <= 0) {
					output("Amount must be positive");
					break;
				}
				control.payFine(Amount); //CoNtRoL.PaY_FiNe(AmouNT)
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);	//sTaTe		
			
			}		
		}		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}	
			

	public void Display(Object object) {  //DiSpLay
		output(object);
	}


}
