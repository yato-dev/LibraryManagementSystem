import java.time.LocalDate;
import java.util.Scanner;

public class LibraryInterface {

    public static void main(String[] args) {
        Library library = new Library();

        int y = 1;
        Scanner scanner = new Scanner(System.in);

        while (y == 1) {
            int x = -1;
            System.out.println("Welcome to Library Management System");
            System.out.println("Choose from the following features");

            System.out.println("1:add a new book");
            System.out.println("2:add a new member");
            System.out.println("3:Check out a book");
            System.out.println("4:Check in a book");
            System.out.println("5:Calculate overdue fine for a member");
            System.out.println("6: Generate reports");
            System.out.println("7:exit");
            System.out.println("Enter your choice");
            x = scanner.nextInt();
            scanner.nextLine();

            switch (x) {
                case 1: {
                    library.addBook(library.takeInputBook());
                }
                    break;
                case 2: {
                    library.addMember(library.takeInputMember());
                }
                    break;
                
                case 3: {
                    System.out.println("Enter checkout date (YYYY-MM-DD): ");
                    String checkoutDateInput = scanner.nextLine();
                    LocalDate checkoutDate = LocalDate.parse(checkoutDateInput);

                    System.out.println("Enter the title of the book: ");
                    String bookTitle = scanner.nextLine();

                    System.out.println("Enter the name of the member: ");
                    String memberName = scanner.nextLine();

                    library.checkoutBook(bookTitle, memberName, checkoutDate);
                }
                    break;
                case 4: {
                    System.out.println("Enter the title of the book: ");
                    String bookTitle = scanner.nextLine();

                    library.checkinBook(bookTitle);
                }
                    break;

                case 5: {
                    System.out.println("Enter the name of the member: ");
                    String memberName2 = scanner.nextLine();
                    library.calculateOverdueFines(memberName2);
                }
                    break;
                case 6: {
                    library.generateReports();
                }
                    break;
                case 7: {
                    y = 0;
                }
                    break;
                default: {
                    System.out.println("Invalid input");
                }

            }

        }
        scanner.close();

    }
}
