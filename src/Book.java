import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


class Book {
    private String title;
    private String author;
    private String ISBN;
    private int availableCopies;
    private int borrowedCopies;
    private String categories;
    private ArrayList<Book> bookList = new ArrayList<>();

    public Book() {
    }

    // Parameterized constructor/Overloading
    public Book(String title, String author, String ISBN, int availableCopies, int borrowedCopies, String categories) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.availableCopies = availableCopies;
        this.borrowedCopies = borrowedCopies;
        this.categories = categories;
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getCategories() {
        return categories;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public int getBorrowedCopies() {
        return borrowedCopies;
    }

    // Setter methods
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public void setBorrowedCopies(int borrowedCopies) {
        this.borrowedCopies = borrowedCopies;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    // Set up book information
    public Book setBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();
        System.out.print("Enter ISBN: ");
        String ISBN = sc.nextLine();
        System.out.print("Enter available copies: ");
        int availableCopies = sc.nextInt();
        System.out.print("Enter borrowed copies: ");
        int borrowedCopies = sc.nextInt();
        System.out.print("Enter categories: ");
        String categories = sc.next();

        return new Book(title, author, ISBN, availableCopies, borrowedCopies, categories);

    }

    // Add n number of books
    public void enterManyBook(int n) {
        for (int i = 0; i < n; i++) {
            Book book = setBook();
            bookList.add(book);
            if (i == n - 1) {
                break;
            } else {
                System.out.println("Please add more books to the list");
                System.out.println("--------------------------------");
            }
        }
        System.out.println("All books has been added to the list!");
        System.out.println("----------------------------");
    }

    // Write book info into a file
    public void writeBookInfo() {
        try (BufferedWriter bookListFile = new BufferedWriter(new FileWriter("book_list.txt"))) {
            bookListFile.write(String.valueOf(bookList.size()));
            bookListFile.newLine();
            for (int i = 0; i < bookList.size(); i++) {
                bookListFile.write(bookList.get(i).getTitle());
                bookListFile.newLine();
                bookListFile.write(bookList.get(i).getAuthor());
                bookListFile.newLine();
                bookListFile.write(bookList.get(i).getISBN());
                bookListFile.newLine();
                bookListFile.write(String.valueOf(bookList.get(i).getAvailableCopies())); // String.valueOf convert to string when write into the text files
                bookListFile.newLine();
                bookListFile.write(String.valueOf(bookList.get(i).getBorrowedCopies()));
                bookListFile.newLine();
                bookListFile.write(bookList.get(i).getCategories());
                bookListFile.newLine();
                bookListFile.newLine();
            }
        } catch (IOException ioe) {
            System.out.println("Error while writing file");
        }
    }

    public void readBookInfo() throws FileNotFoundException {
        bookList.clear(); // clear old list to avoid duplicate output
        FileReader fr = new FileReader("book_list.txt");

        try (BufferedReader bookListFile = new BufferedReader(fr)) {
            String line = bookListFile.readLine();

            while ((line = bookListFile.readLine()) != null) {
                // Read books details
                String title = line;
                String author = bookListFile.readLine();
                String ISBN = bookListFile.readLine();
                int availableCopies = Integer.parseInt(bookListFile.readLine()); // Wrapper class convert from booklist to interger when read
                int borrowedCopies = Integer.parseInt(bookListFile.readLine());
                String categories = bookListFile.readLine();

                Book newBook = new Book();
                newBook.setTitle(title);
                newBook.setAuthor(author);
                newBook.setISBN(ISBN);
                newBook.setAvailableCopies(availableCopies);
                newBook.setBorrowedCopies(borrowedCopies);
                newBook.setCategories(categories);

                // add new obj to global book list
                bookList.add(newBook);
                bookListFile.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Allow user to modify a book information that they just entered, searched by isbn
    public void modifyBookInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the ISBN of the book: ");
        String bookIsbn = sc.nextLine();
        System.out.println("----------------------------");
        System.out.println("1. Title: ");
        System.out.println("2. Author: ");
        System.out.println("3. Available copies: ");
        System.out.println("4. Borrowed copies: ");
        System.out.println("5. Categories: ");
        System.out.print("Please choose the specified information that you want to modify: ");

        boolean bookFound = false;

        for (Book book : bookList) {
            if (book.getISBN().equalsIgnoreCase(bookIsbn)) {
                bookFound = true;
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline left by nextInt

                switch (choice) {
                    case 1:
                        System.out.print("Enter the new title: ");
                        book.setTitle(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("Enter the new author: ");
                        book.setAuthor(sc.nextLine());
                        break;
                    case 3:
                        System.out.print("Enter the new number of available copies: ");
                        book.setAvailableCopies(sc.nextInt());
                        sc.nextLine(); // Consume newline
                        break;
                    case 4:
                        System.out.print("Enter the new number of borrowed copies: ");
                        book.setBorrowedCopies(sc.nextInt());
                        sc.nextLine(); // Consume newline
                        break;
                    case 5:
                        System.out.print("Enter the new categories: ");
                        book.setCategories(sc.nextLine());
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
                System.out.println("Book information updated successfully.");
                writeBookInfo();
                break;
            }
        }
        if (!bookFound) {
            System.out.println("No book found with the specified ISBN.");
        }
    }

    // Check if ISBN is duplicate
    private boolean isIsbnDuplicate(String isbn) {
        for (Book book : bookList) {
            if (book.getISBN().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    // add book function
    public void addMoreBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("The new book that you want to add: ");
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();
        System.out.print("Enter ISBN: ");
        String ISBN = sc.nextLine();

        // Check if ISBN is duplicate
        if (isIsbnDuplicate(ISBN)) {
            System.out.println("Error: This ISBN already exists in the database!");
            System.out.println("\n----------------------");
            return;
        }

        System.out.print("Enter available copies: ");
        int availableCopies = sc.nextInt();
        System.out.print("Enter borrowed copies: ");
        int borrowedCopies = sc.nextInt();
        System.out.print("Enter categories: ");
        String categories = sc.next();

        // add the new book to the book list
        Book newBook = new Book(title, author, ISBN, availableCopies, borrowedCopies, categories);
        bookList.add(newBook);

        // write new book into the files
        System.out.println("Book added successfully!");
        System.out.println("Current number of books: " + bookList.size() + "\n");
    }

    // Function to delete book
    public void removeBook() {
        // Place a flag to check if the user input the available isbn in the database or not
        boolean check = false;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ISBN of the book that you want to remove: ");
        String bookIsbn = sc.nextLine();
        Book element = null;
        for (Book book : bookList) {
            if (book.getISBN().equalsIgnoreCase(bookIsbn)) {
                element = book;
                check = true;
            }
        }
        if (check) {
            bookList.remove(element);
            System.out.println("Book removed successfully!");
            System.out.println("Current number of books: " + bookList.size() + "\n");
        } else {
            System.out.println("Error: This ISBN does not exist in the database!");
            System.out.println("--------------------------------");
        }
    }

    // Search for books by ISBN, title or categories
    public void findBooks() {
        try {
            if (bookList.isEmpty()) {
                readBookInfo();
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("Search books by:");
            System.out.println("1. ISBN");
            System.out.println("2. Title");
            System.out.println("3. Author Name");
            System.out.println("4. Categories");
            System.out.print("Enter your choice (1-4): ");

            boolean check = false;
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter ISBN to search: ");
                    String bookIsbn = sc.nextLine();
                    System.out.println("--------------------------------");
                    for (Book book : bookList) {
                        if (book.getISBN().equalsIgnoreCase(bookIsbn)) {
                            displayOneBooks(book);
                            check = true;
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter Title to search: ");
                    String bookTitle = sc.nextLine();
                    System.out.println("--------------------------------");
                    for (Book book : bookList) {
                        if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                            displayOneBooks(book);
                            check = true;
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter Author to search: ");
                    String bookAuthor = sc.nextLine();
                    System.out.println("--------------------------------");
                    System.out.println(bookAuthor + ":");
                    for (Book book : bookList) {
                        if (book.getAuthor().equalsIgnoreCase(bookAuthor)) {
                            displayOneBooks(book);
                            check = true;
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter Category to search: ");
                    String category = sc.nextLine();
                    System.out.println("--------------------------------");
                    System.out.println(category + ":");
                    for (int i = 0; i < bookList.size(); i++) {
                        if (bookList.get(i).getCategories().equalsIgnoreCase(category)) {
                            displayOneBooks(bookList.get(i));
                            check = true;
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid Input");
                    return;
            }
            if (!check) {
                System.out.println("No books found matching your search criteria.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find the book list file.");
        } catch (Exception e) {
            System.out.println("Error while searching: " + e.getMessage());
        }
    }

    public void displayOneBooks(Book n) {
        System.out.println("Book Title: " + n.getTitle());
        System.out.println("Author: " + n.getAuthor());
        System.out.println("ISBN: " + n.getISBN());
        System.out.println("Available Copies: " + n.getAvailableCopies());
        System.out.println("Borrowed Copies: " + n.getBorrowedCopies());
        System.out.println("Categories: " + n.getCategories());
        System.out.println(" ");
    }

    public void displayAllBooks() {
        try {
            if (bookList.isEmpty()) {
                readBookInfo();
            }
            System.out.println("\n---Available books---");
            for (int i = 0; i < bookList.size(); i++) {
                System.out.println((i + 1)+".");
                displayOneBooks(bookList.get(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: There is no book list file.");
        }
    }
}