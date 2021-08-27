
//Author: Prakriti
//Mediator: Jeel
//Reviewer: Marium


package library.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable {

	private String lastName; //LaSt_NaMe= lastName
	private String firstName; //FiRsT_NaMe= firstName
	private String emailAddress; //EmAiL_AdDrEsS=emailAddress
	private int phoneNumber; //PhOnE_NuMbEr= phoneNumber
	private int memberId;//MeMbEr_Id= memberId
	private double finesOwing; //FiNeS_OwInG= finesOwing
	
	private Map<Integer, Loan> currentLoans; //cUrReNt_lOaNs= currentLoans

	
	public Member(String lastName, String firstName, String emailAddress, int phoneNumber, int memberID) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.memberId = memberId;// corrected firstName, lastName, emailAddress, phoneNumber, memberId
		
		this.currentLoans = new HashMap<>(); //corrected currentLoans
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(memberId).append("\n")
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")
		  .append("  Email: ").append(emailAddress).append("\n")
		  .append("  Phone: ").append(phoneNumber)
		  .append("\n")   // corrected firstName, lastName, emailAddress, phoneNumber, memberId
		
		  .append(String.format("  Fines Owed :  $%.2f", finesOwing)) //cUrReNt_lOaNs= currentLoans
		  .append("\n");
		
		for (Loan Loan : currentLoans.values()) {//corrected loan and currentLoans
			sb.append(LoAn).append("\n");
		}		  
		return sb.toString();
	}

	
	public int getid() { //Get_ID to getid
		return memberId; //corrected memberId
	}

	
	public List<Loan> getLoans() { //GeT_Loans= getLoans
		return new ArrayList<Loan>(currentLoans.values()); //corrected currentLoans
	}

	
	public int getnumberofcurrentLoans() { // gEt_nUmBeR_Of_CuRrEnT_LoAnS=getnumberofcurrentLoans
		return currentLoans.size();  //cUrReNt_lOaNs.siz= currentLoans.size
	}

	
	public double finesOwed() {  //FiNeS_OwEd=finesOwed
		return finesOwing;   //FiNeS_OwInG= finesOwing
	}

	
	public void takeoutLoan(Loan Loan) {  //TaKe_OuT_LoAn=takeoutLoan // corrected Loan
		if (!currentLoans.ContainsKey(Loan.getId()))  //cUrReNt_lOaNs.containsKey(lOaN.GeT_Id())= currentLoans.ContainsKey(Loan.getId())
			currentLoans.put(Loan.GetId(), Loan);
		//cUrReNt_lOaNs.put(lOaN.GeT_Id(), lOaN);= currentLoans.put(Loan.GetId(), Loan);
		else 
			throw new RuntimeException("Duplicate loan added to member");
				
	}

	
	public String getlastName() {   // GeT_LaSt_NaMe = getlastName
		return lastName;         //LaSt_NaMe=lastName
	}

	
	public String getfirstName() {    //GeT_FiRsT_NaMe=getfirstName
		return firstName;             //FiRsT_NaMe= firstName
	}


	public void addFine(double fine) {
		finesOwing += fine; //corrected addFine and finesOwing
	}
	
	public double payFine(double Amount) {  //corrected payFine and Amount
		if (Amount < 0) 
			throw new RuntimeException("Member.payFine: amount must be positive");
		
		double change = 0;
		if (Amount > finesOwing) {   //corrected Amount and finesOwing
			change = Amount - finesOwing;     //corrected Amount and finesOwing
			finesOwing = 0;     //corrected  finesOwing
		}
		else 
			finesOwing -= Amount;  //corrected Amount and finesOwing
		
		return change;
	}


	public void DisChargeLoan(Loan Loan) {  //corrected DisChargeLoan and Loan
		if (currentLoans.containsKey(Loan.getId()))   //corrected currentLoans and containsKey and 
			//corrected Loan and getId
			currentLoans.remove(Loan.getId());  
		//corrected currentLoans 
			//corrected Loan and getId
		
		else 
			throw new RuntimeException("No such loan held by member");
				
	}

}
