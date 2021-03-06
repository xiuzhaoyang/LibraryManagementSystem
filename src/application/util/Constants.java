package application.util;

import application.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static List<Person> getPersonData() {
		List<Person> members = new ArrayList<>();

		List<PersonRole> list1 = new ArrayList<PersonRole>();
		list1.add(new Librarian());

		List<PersonRole> list2 = new ArrayList<PersonRole>();
		list2.add(new Administrator());

		List<PersonRole> list3 = new ArrayList<PersonRole>();
		list3.add(new LibraryMember());
		list3.add(new Administrator());
		list3.add(new Librarian());

		List<PersonRole> list4 = new ArrayList<PersonRole>();
		list4.add(new LibraryMember());

		List<PersonRole> list5 = new ArrayList<PersonRole>();
		list5.add(new LibraryMember());
		list5.add(new Librarian());

		List<PersonRole> list6 = new ArrayList<PersonRole>();
		list6.add(new LibraryMember());

		List<PersonRole> list7 = new ArrayList<PersonRole>();
		list7.add(new Administrator());
		list7.add(new Librarian());

		List<PersonRole> list8 = new ArrayList<PersonRole>();
		list8.add(new LibraryMember());

		List<PersonRole> list9 = new ArrayList<PersonRole>();
		list9.add(new LibraryMember());

		//Hard code some data
		//Person(int pid, String firstName, String lastName, String street, String city, String state, String zip, String phoneNum, LocalDate dob)
		Person p = new Person(1, "Tim", "TM", "1st ST", "New York", "NY", "11231", "123456789", LocalDate.of(1980, 5, 20), list1);
		p.setUserName("100001");
		p.setPwd("lin");
		members.add(p);

		p = new Person(2, "Jessica", "JS", "12st ST", "Fairfield", "IA", "55231", "213456789", LocalDate.of(1983, 9, 12), list2);
		p.setUserName("100002");
		p.setPwd("cui");
		members.add(p);

		p = new Person(3, "Monica", "MN", "11st ST", "Washington", "DC", "20231", "123547689", LocalDate.of(1988, 11, 5), list3);
		p.setUserName("100000");
		p.setPwd("su");
		members.add(p);

		members.add(new Person(4, "Jack", "JK", "5st ST", "New York", "NY", "13511", "993456789", LocalDate.of(1981, 06, 10), list4));
		members.add(new Person(5, "Tony", "TN", "3st ST", "Baltimore", "MD", "20931", "128765439", LocalDate.of(1990, 03, 26), list5));
		members.add(new Person(6, "Serena", "SE", "8st ST", "North Potomac", "MD", "20878", "871234569", LocalDate.of(1999, 07, 19), list6));
		members.add(new Person(7, "Fei", "Zhang", "11st ST", "Seattle", "MA", "22631", "431257789", LocalDate.of(1985, 03, 15), list7));
		members.add(new Person(8, "Yu", "Guan", "20st ST", "Boston", "MA", "71231", "123410989", LocalDate.of(1992, 8, 21), list8));
		members.add(new Person(9, "Bei", "Liu", "8st ST", "Rockville", "MD", "27231", "162216789", LocalDate.of(1989, 7, 11), list9));


		return members;
	}

	public static List<Publication> getPublications(){
		
		Address addr1 = new Address("1st", "New York", "NY", "12345");
		List<Author> authorList1 = new ArrayList<Author>();
		Author author = new Author(1, "Fei", "Zhang", addr1, "PhD", "novelist", "CHN");
		authorList1.add(author);
		Publication bk1 = new Publication(1, LocalDate.of(2011, 11, 2),"C#", "123456789", authorList1, AllowedBorrowDays.TWENTY_ONE_DAYS, new Book());
		Publication bk2 = new Publication(2, LocalDate.of(2013, 5, 2), "Java", "789123456", authorList1, AllowedBorrowDays.TWENTY_ONE_DAYS, new Book());
		Publication bk3 = new Publication(3, LocalDate.of(2009, 7, 2), "C++", "456123789", authorList1, AllowedBorrowDays.TWENTY_ONE_DAYS, new Book());

		List<Publication> publicationList = new ArrayList<Publication>();

		Address addr2 = new Address("1st", "New York", "NY", "12345");
		List<Author> authorList2 = new ArrayList<Author>();
		Author author2 = new Author(1, "Fei", "Zhang", addr2, "PhD", "novelist", "CHN");
		authorList2.add(author2);


		Publication bk4 = new Publication(4, LocalDate.of(2012, 10, 2), "C#2", "923456789", authorList2, AllowedBorrowDays.TWENTY_ONE_DAYS, new Book());
		Publication bk5 = new Publication(5, LocalDate.of(2013, 8, 5), "Java2", "789123456", authorList2, AllowedBorrowDays.TWENTY_ONE_DAYS, new Book());
		Publication bk6 = new Publication(6, LocalDate.of(2014, 9, 12), "C++2", "456123789", authorList2, AllowedBorrowDays.TWENTY_ONE_DAYS, new Book());
		bk4.addCopy(LocalDate.of(2012, 3, 12));
		bk4.addCopy(LocalDate.of(2013, 4, 1));
		bk4.addCopy(LocalDate.of(2015, 8, 8));
		
		publicationList.add(bk1);
		publicationList.add(bk2);
		publicationList.add(bk3);
		publicationList.add(bk4);
		publicationList.add(bk5);
		publicationList.add(bk6);

		return publicationList;
	}
	
	
	public static CheckoutRecord getCheckoutRecord(){
		List<CheckoutEntry> enties2 = new ArrayList<>();
		CheckoutEntry ckEntry4 = new CheckoutEntry(4, 5, LocalDate.of(2015, 5, 10), LocalDate.of(2015, 5, 25), LocalDate.of(2015, 5, 24), false, 0.0f);
		CheckoutEntry ckEntry5 = new CheckoutEntry(5, 5, LocalDate.of(2015, 5, 1), LocalDate.of(2015, 5, 22), LocalDate.of(2015, 5, 22), false, 0.0f);
		CheckoutEntry ckEntry6 = new CheckoutEntry(6, 5, LocalDate.of(2015, 5, 1), LocalDate.of(2015, 5, 22), LocalDate.of(2015, 5, 20), false, 0.0f);

		enties2.add(ckEntry4);
		enties2.add(ckEntry5);
		enties2.add(ckEntry6);
		CheckoutRecord ckRecord2 = new CheckoutRecord(2, 3, enties2);	//member ID 3's record
		
		return ckRecord2;
	}
	
}
