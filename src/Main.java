
// Tasks: Book system with several functions
//  Allow to enter books info from keyboard: V
//      ISBN, title, author, quality, borrowed amount, categories (Users permission)
//  Allow to write and read book info from a file V
//  Allow to modify books info by entering ISBN V
//  Allow to add, delete 1 or more books in the list (Admin permission) V
//      First read the file -> user add books -> call write book method to write new book into the file -> then display all the current books
//  Allow to search books base on ISBN or title or author
//  Show lists of Books based on categories or author (grouped)

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create three book objects using different constructors
        Book bookInfo = new Book();
        Scanner sc = new Scanner(System.in);
        System.out.println("---Welcome to Aura the best Book Management System!---");
        try {
            bookInfo.readBookInfo();
        } catch (FileNotFoundException e) {
            System.out.println("System does not have any data yet, please choose 1 to input data");
        }
        while (true) {
            System.out.println("1. Enter a number of Book Information");
            System.out.println("2. Display all Books");
            System.out.println("3. Modify Book Information");
            System.out.println("4. Add a new book");
            System.out.println("5. Remove a book");
            System.out.println("6. Search books");
            System.out.println("7. Save change to database");
            System.out.println("8. Exit");
            System.out.print("Please enter your choice (1-7) or 8 to exit: ");

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter how many books that you want to add: ");
                    int bookQuantity = sc.nextInt();
                    bookInfo.enterManyBook(bookQuantity);
                    System.out.print("---Press enter to continue---");
                    sc.nextLine();
                    break;
                case 2:
                    bookInfo.displayAllBooks();
                    System.out.print("---Press enter to continue---");
                    sc.nextLine();
                    break;
                case 3:
                    bookInfo.modifyBookInfo();
                    System.out.print("---Press enter to continue---");
                    sc.nextLine();
                    break;
                case 4:
                    bookInfo.addMoreBook();
                    System.out.print("---Press enter to continue---");
                    sc.nextLine();
                    break;
                case 5:
                    bookInfo.removeBook();
                    System.out.print("---Press enter to continue---");
                    sc.nextLine();
                    break;
                case 6:
                    bookInfo.findBooks();
                    System.out.print("---Press enter to continue---");
                    sc.nextLine();
                    break;
                case 7:
                    bookInfo.writeBookInfo();
                    System.out.println("\nBook has been added successfully!");
                    System.out.print("---Press enter to continue---");
                    break;
                case 8:
                    System.out.println("---Thank you for using the Aura!---");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
                    return;
            }
        }
    }
}