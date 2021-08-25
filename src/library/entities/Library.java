// Author: Jeel
// Mediator: Mariam
// Reviewer: Prakriti


package library.entities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable {
	
	private static final String LIBRARY_FILE = "library.obj";  	//private static final String lIbRaRyFiLe = "library.obj";
	private static final int LOAN_LIMIT = 2;       			//private static final int lOaNlImIt = 2;
	private static final int LOAN_PERIOD = 2;			//private static final int loanPeriod = 2;
	private static final double FINE_PER_DAY = 1.0;			//private static final double FiNe_PeR_DaY = 1.0;	
	private static final double MAX_FINES_OWED = 1.0;		//private static final double maxFinesOwed = 1.0;
	private static final double DAMAGE_FEE = 2.0;			//private static final double damageFee = 2.0;
	
	private static Library self;					//private static Library SeLf;			
	private int bookId;						//private int bOoK_Id;	
	private int memberId;						//private int mEmBeR_Id;
	private int loanId;						//private int lOaN_Id;
	private Date loanDate;						//private Date lOaN_DaTe;
	
	private Map<Integer, Book> catalog;			//CaTaLoG
	private Map<Integer, Member> members;			//MeMbErS
	private Map<Integer, Loan> loans;			//LoAnS
	private Map<Integer, Loan> currentLoans;		//CuRrEnT_LoAnS
	private Map<Integer, Book> damagedBooks;		//DaMaGeD_BoOkS
	

	private Library() {
		catalog = new HashMap<>();			//CaTaLoG
		members = new HashMap<>();			//MeMbErS
		loans = new HashMap<>();			//LoAnS
		currentLoans = new HashMap<>();		//CuRrEnT_LoAnS
		damagedBooks = new HashMap<>();		//DaMaGeD_BoOkS
		bookId = 1;				//bOoK_Id = 1;
		memberId = 1;				//mEmBeR_Id = 1;	
		loanId = 1;				//lOaN_Id = 1;	
	}

/*
	public static synchronized Library GeTiNsTaNcE() {		
		if (SeLf == null) {
			Path PATH = Paths.get(lIbRaRyFiLe);			
			if (Files.exists(PATH)) {	
				try (ObjectInputStream LiBrArY_FiLe = new ObjectInputStream(new FileInputStream(lIbRaRyFiLe));) {
			    
					SeLf = (Library) LiBrArY_FiLe.readObject();
					Calendar.gEtInStAnCe().SeT_DaTe(SeLf.lOaN_DaTe);
					LiBrArY_FiLe.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else SeLf = new Library();
		}
		return SeLf;
	}
*/
	public static synchronized Library getInstance() {		
		if (self == null) {
			Path PATH = Paths.get(LIBRARY_FILE);			
			if (Files.exists(PATH)) {	
				try (ObjectInputStream LIBRARY_FILE  = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) {
			    
					self = (Library) LIBRARY_FILE.readObject();
					Calendar.getInstance().setDate(self.loanDate);
					LIBRARY_FILE.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new Library();
		}
		return self;
	}

/*	public static synchronized void SaVe() {
		if (SeLf != null) {
			SeLf.lOaN_DaTe = Calendar.gEtInStAnCe().gEt_DaTe();
			try (ObjectOutputStream LiBrArY_fIlE = new ObjectOutputStream(new FileOutputStream(lIbRaRyFiLe));) {
				LiBrArY_fIlE.writeObject(SeLf);
				LiBrArY_fIlE.flush();
				LiBrArY_fIlE.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
*/
	public static synchronized void save() {
		if (SeLf != null) {
			SeLf.lOaN_DaTe = Calendar.getInstance().getDate();
			try (ObjectOutputStream LIBRARY_FILE  = new ObjectOutputStream(new FileOutputStream(lIbRaRyFiLe));) {
				LIBRARY_FILE.writeObject(SeLf);
				LIBRARY_FILE.flush();
				LIBRARY_FILE.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int getBookId() {	//gEt_BoOkId() 
		return bookId;		//bOoK_Id()
	}
	
	
	public int getMemberId() {	//gEt_MeMbEr_Id()
		return memberId;	//mEmBeR_Id()
	}
	
	
	private int getNextBookId() {	//gEt_NeXt_BoOk_Id()
		return bookId++;	//bOoK_Id()
	}

	
	private int getNextMemberId() {	//gEt_NeXt_MeMbEr_Id()
		return memberId++;	//mEmBeR_Id()
	}

	
	private int getNextLoanId() {	//gEt_NeXt_loanId()
		return loanId++;	//lOaN_Id()
	}

	
	public List<Member> listMembers() {				//lIsT_MeMbErS()
		return new ArrayList<Member>(members.values()); 	//return new ArrayList<Member>(MeMbErS.values()); 
	}


	public List<Book> listBooks() {					//lIsT_BoOkS()
		return new ArrayList<Book>(catalog.values()); 		//return new ArrayList<Book>(CaTaLoG.values()); 
	}


	public List<Loan> listCurrentLoans() {				//lISt_CuRrEnT_LoAnS()
		return new ArrayList<Loan>(currentLoans.values());	//return new ArrayList<Loan>(CuRrEnT_LoAnS.values());
	}


	public Member aDd_MeMbEr(String lastName, String firstName, String email, int phoneNo) {		
		Member member = new Member(lastName, firstName, email, phoneNo, gEt_NeXt_MeMbEr_Id());
		MeMbErS.put(member.GeT_ID(), member);		
		return member;
	}

	
	public Book aDd_BoOk(String a, String t, String c) {		
		Book b = new Book(a, t, c, gEt_NeXt_BoOk_Id());
		CaTaLoG.put(b.gEtId(), b);		
		return b;
	}

	
	public Member gEt_MeMbEr(int memberId) {
		if (MeMbErS.containsKey(memberId)) 
			return MeMbErS.get(memberId);
		return null;
	}

	
	public Book gEt_BoOk(int bookId) {
		if (CaTaLoG.containsKey(bookId)) 
			return CaTaLoG.get(bookId);		
		return null;
	}

	
	public int gEt_LoAn_LiMiT() {
		return lOaNlImIt;
	}

	
	public boolean cAn_MeMbEr_BoRrOw(Member member) {		
		if (member.gEt_nUmBeR_Of_CuRrEnT_LoAnS() == lOaNlImIt ) 
			return false;
				
		if (member.FiNeS_OwEd() >= maxFinesOwed) 
			return false;
				
		for (Loan loan : member.GeT_LoAnS()) 
			if (loan.Is_OvEr_DuE()) 
				return false;
			
		return true;
	}

	
	public int gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr(Member MeMbEr) {		
		return lOaNlImIt - MeMbEr.gEt_nUmBeR_Of_CuRrEnT_LoAnS();
	}

	
	public Loan iSsUe_LoAn(Book book, Member member) {
		Date dueDate = Calendar.gEtInStAnCe().gEt_DuE_DaTe(loanPeriod);
		Loan loan = new Loan(gEt_NeXt_LoAn_Id(), book, member, dueDate);
		member.TaKe_OuT_LoAn(loan);
		book.BoRrOw();
		LoAnS.put(loan.GeT_Id(), loan);
		CuRrEnT_LoAnS.put(book.gEtId(), loan);
		return loan;
	}
	
	
	public Loan GeT_LoAn_By_BoOkId(int bookId) {
		if (CuRrEnT_LoAnS.containsKey(bookId)) 
			return CuRrEnT_LoAnS.get(bookId);
		
		return null;
	}

	
	public double CaLcUlAtE_OvEr_DuE_FiNe(Loan LoAn) {
		if (LoAn.Is_OvEr_DuE()) {
			long DaYs_OvEr_DuE = Calendar.gEtInStAnCe().GeT_DaYs_DiFfErEnCe(LoAn.GeT_DuE_DaTe());
			double fInE = DaYs_OvEr_DuE * FiNe_PeR_DaY;
			return fInE;
		}
		return 0.0;		
	}


	public void DiScHaRgE_LoAn(Loan cUrReNt_LoAn, boolean iS_dAmAgEd) {
		Member mEmBeR = cUrReNt_LoAn.GeT_MeMbEr();
		Book bOoK  = cUrReNt_LoAn.GeT_BoOk();
		
		double oVeR_DuE_FiNe = CaLcUlAtE_OvEr_DuE_FiNe(cUrReNt_LoAn);
		mEmBeR.AdD_FiNe(oVeR_DuE_FiNe);	
		
		mEmBeR.dIsChArGeLoAn(cUrReNt_LoAn);
		bOoK.ReTuRn(iS_dAmAgEd);
		if (iS_dAmAgEd) {
			mEmBeR.AdD_FiNe(damageFee);
			DaMaGeD_BoOkS.put(bOoK.gEtId(), bOoK);
		}
		cUrReNt_LoAn.DiScHaRgE();
		CuRrEnT_LoAnS.remove(bOoK.gEtId());
	}


	public void cHeCk_CuRrEnT_LoAnS() {
		for (Loan lOaN : CuRrEnT_LoAnS.values()) 
			lOaN.cHeCk_OvEr_DuE();
				
	}


	public void RePaIr_BoOk(Book cUrReNt_BoOk) {
		if (DaMaGeD_BoOkS.containsKey(cUrReNt_BoOk.gEtId())) {
			cUrReNt_BoOk.RePaIr();
			DaMaGeD_BoOkS.remove(cUrReNt_BoOk.gEtId());
		}
		else 
			throw new RuntimeException("Library: repairBook: book is not damaged");
		
		
	}
	
	
}
