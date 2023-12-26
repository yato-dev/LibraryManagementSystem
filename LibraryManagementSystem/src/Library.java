import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    List<Book> books;
    List<Member> members;
    List<Loan> loans;
    Scanner scanner = new Scanner(System.in);

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public Book takeInputBook() {

        System.out.println("Input title");
        String tempTitle = scanner.nextLine();
        System.out.println("Input author");
        String tempAuthor = scanner.nextLine();
        System.out.println("Input Publisher");
        String tempPublisher = scanner.nextLine();
        System.out.println("Input Genre");
        String tempGenre = scanner.nextLine();
        System.out.println("Input no of Pages");
        int tempPages = scanner.nextInt();
        scanner.nextLine();
        Book tempBook = new Book(tempTitle, tempAuthor, tempPublisher, tempGenre, tempPages);
        return tempBook;
    }

    public Member takeInputMember() {
        System.out.println("Input name");
        String tempName = scanner.nextLine();
        System.out.println("Input address");
        String tempAddress = scanner.nextLine();
        System.out.println("Input Phone");
        String tempPhone = scanner.nextLine();
        System.out.println("Input Email");
        String tempEmail = scanner.nextLine();
        Member tempMember = new Member(tempName, tempAddress, tempPhone, tempEmail);
        return tempMember;

    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book has been successfully added:" + book.title);
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("Memeber has been successfull added:" + member.name);
    }

    public void checkoutBook(String bookTitle, String memberName, LocalDate dueDate) {
        Book book = findBookByTitle(bookTitle);
        Member member = findMemberByName(memberName);

        if (book != null && member != null) {
            loans.add(new Loan(book, member, dueDate));
            books.remove(book);
            System.out.println("Book Checked out Successfully to: " + member.getName());
        } else {
            System.out.println("Book or member not present, kindly check your input");
        }
    }

    public void checkinBook(String bookTitle) {
        Book book = findBookByTitle(bookTitle);
        Loan loanToRemove = null;

        for (Loan loan : loans) {
            if (loan.getBook().equals(book)) {
                loanToRemove = loan;
                break;
            }
        }

        if (loanToRemove != null) {
            loans.remove(loanToRemove);
            books.add(book);
            System.out.println("Book checked in successfully");
        } else {
            System.out.println("Book not checked in");
        }
    }

    private Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    private Member findMemberByName(String name) {
        for (Member member : members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    public void calculateOverdueFines(String name) {
        double totalFines = 0;
        Member member=findMemberByName(name);

        for (Loan loan : loans) {
            if (loan.member.equals(member) && loan.dueDate.isBefore(LocalDate.now())) {
                totalFines += CalculateFine(loan, loan.dueDate);
            }
        }

        System.out.println("Total overdue fines for" + member.name + ": rupees" + totalFines);

    }

    private double CalculateFine(Loan loan, LocalDate dueDate) {
        double FINE_COST = 3;
        long day = LocalDate.now().toEpochDay() - dueDate.toEpochDay();

        if (day > 0)
            return FINE_COST * day;
        else
            return 0;

    }

    public void generateReports() {
        System.out.println("List of all books");
        books.forEach(b -> System.out.println("-" + b.getTitle()));

        System.out.println("List of all members");
        members.forEach(m -> System.out.println("-" + m.getName()));

        System.out.println("List of all books that are checked out");
        loans.forEach(l -> System.out.println("-" + l.getBook().getTitle()));
    }

}
