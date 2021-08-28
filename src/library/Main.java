package library;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import library.borrowbook.BorrowBookUI;
import library.borrowbook.bORROW_bOOK_cONTROL;
import library.entities.Book;
import library.entities.Calendar;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;
import library.fixbook.FixBookUI;
import library.fixbook.FixBookControl;        //FIxBoOkcOnTrol
import library.payfine.PayFineUI;
import library.payfine.PayFineControl;         //.pAY_fINE_cONTROL
import library.returnBook.ReturnBookUI;
import library.returnBook.ReturnBookControl;      //rETURN_bOOK_cONTROL


public class Main {
	
	private static Scanner IN;
	private static Library LIB;
	private static String MENU;
	private static Calendar CAL;
	private static SimpleDateFormat SDF;
	
	
	private static String getMenu() {                   //String Get_menu
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			IN = new Scanner(System.in);
			LIB = Library.getInstance();       //GeTiNsTaNcE
			CAL = Calendar.getInstance();         //GeTiNsTaNcE
			SDF = new SimpleDateFormat("dd/MM/yyyy");
	
			for (Member m : LIB.listMembers()) {    //lIsT_MeMbErS
				output(m);
			}
			output(" ");
			for (Book b : LIB.listBooks())/lIsT_MeMbErS
				output(b);
			}
						
			MENU = getMenu   //Get_menu
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + SDF.format(CAL.getDAte())        //gEt_DaTe
				String c = input(menu)
				
				switch (c.toUpperCase()) {
				
				case "M": 
					AddMember();
					break;
					
				case "LM": 
					listMember();     //LIST_MEMBERS
					break;
					
				case "B": 
					addBook();   //ADD_BOOK
					break;
					
				case "LB": 
					listBooks();                 //LIST_BOOKS
					break;
					
				case "FB": 
					FixBooks();         //FIX_BOOKS
					break;
					
				case "L": 
					borrowBook();     //BORROW_BOOK
					break;
					
				case "R": 
					returnBook();           //RETURN_BOOK
					break;
					
				case "LL": 
					ListCurrentLoans();      //LIST_CURRENT_LOANS
					break;
					
				case "P": 
					payFines();               //PAY_FINES();
					break;
					
				case "T": 
					incrementDate();                 //INCREMENT_DATE();
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				Library.sve();         //Library.SaVe();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	
	private static void payFines()   {                //PAY_FINES
		new PayFineUI(new PayFineControl()).run();     //pAY_fINE_cONTROL()).RuN()
	}


	private static void listCurrentLoans() { // LIST_CURRENT_LOANS
		output("");
		for (Loan loan : LIB.listCurrentLoans(){         //LIB.lISt_CuRrEnT_LoAnS
			output(loan + "\n");
		}		
	}



	private static void listBooks() {          //LIST_BOOKS
		output("");
		for (Book book : LIB.ListBooks() {   //LIB.lIsT_BoOkS
			output(book + "\n");
		}		
	}



	private static void listMembers() {       //LIST_MEMBERS
		output("");
		for (Member member : LIB.listMembers()) {                   // LIB.lIsT_MeMbErS(
			output(member + "\n");
		}		
	}



	private static void borrowBook() {                 //BORROW_BOOK
		new BorrowBookUI(new BorrowBookControl()).run();    //new bORROW_bOOK_cONTROL()).RuN()
	}


	private static void returnBook() {        //RETURN_BOOK
		new ReturnBookUI(new ReturnBookControl()).run();                       //(new rETURN_bOOK_cONTROL()).RuN()
	}


	private static void fixBooks()  {                                 //void FIX_BOOKS() {
		new FixBookUI(new FixBookControl()).run();          //new fIX_bOOK_cONTROL()).RuN();
	}


	private static void incrementDate() {                    //INCREMENT_DATE(
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			CAL.incrementDate(days);
			LIB.checkCurrentLoans();                      //LIB.cHeCk_CuRrEnT_LoAnS()
			output(SDF.format(CAL.getDAte()))                             //SDF.format(CAL.gEt_DaTe()))
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook                         //ADD_BOOK
		
		String author = input("Enter author: ");                                           //AuThOr
		String title =input("Enter title: ");                                        //TiTlE  
		String callNumber = input("Enter call number: ");                          // //CaLl_NuMbEr
		Book BoOk = LIB.addBook(author, title, callNumber);            //LIB.aDd_BoOk(AuThOr, TiTlE, CaLl_NuMbEr);
		output("\n" + Book +"\n");                 //BoOk 
		
	}

	
	private static void addBook() {           //ADD_MEMBER
		try {
			String lastNamen = input("Enter last name: ");                   //LaSt_NaMe
			String firstName = input("Enter first name: ");                   //FiRsT_NaMe 
			String emailAddress  = input("Enter email address: ");                              //EmAiL_AdDrEsS 
			int phoneNumber = Integer.valueOf(input("Enter phone number: ")).intValue();                 // PhOnE_NuMbEr
			Member memebr = library.addMember(lastName, firstName, emailAddress, phoneNumber);      //AddMeMber
			output("\n" + member + "\n");              //MeMbEr
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return IN.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
