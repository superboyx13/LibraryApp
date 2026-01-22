package application;

import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookList bookList = new BookList();

        int choice = -1;

        // loop until the user chooses to exit
        do {
            // Display the main menu
            System.out.println("\n Personal Library Menu ");
            System.out.println();
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search by Title");
            System.out.println("4. Search by Author");
            System.out.println("5. Search by ISBN");
            System.out.println("6. Edit Book");
            System.out.println("7. Delete Book");
            System.out.println("0. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            
            //exception handling for invalid choice numbers.
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
                continue;
            }
            
            // handles each menu choice
            switch (choice) {
                case 1:
                    // Add a new book
                    Book newBook = readBookInput(scanner);
                    bookList.addBook(newBook);
                    break;

                case 2:
                    // View all books
                    bookList.viewBooks();
                    break;

                case 3:
                    // Search by title
                    System.out.print("Enter title to search: ");
                    String title = scanner.nextLine();
                    bookList.searchByTitle(title);
                    break;

                case 4:
                    // Search by author
                    System.out.print("Enter author to search: ");
                    String author = scanner.nextLine();
                    bookList.searchByAuthor(author);
                    break;

                case 5:
                    // Search by ISBN
                    System.out.print("Enter ISBN to search: ");
                    String searchIsbn = scanner.nextLine();
                    Book found = bookList.searchByISBN(searchIsbn);
                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("There is no book with that ISBN.");
                    }
                    break;

                case 6:
                    // Edit a book
                    System.out.print("Enter ISBN of the book you want edited: ");
                    String isbnEdit = scanner.nextLine();
                    Book editedBook = readBookInput(scanner);
                    
                    //calls editBook method from BookList class, if found it replaces with editedBook.
                    boolean edited = bookList.editBook(isbnEdit, editedBook);
                    if (edited) {
                        System.out.println("Book updated.");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 7:
                    // Delete a book
                    System.out.print("Enter ISBN of the book you want deleted: ");
                    String deleteIsbn = scanner.nextLine();
                    boolean deleted = bookList.deleteBook(deleteIsbn);
                    if (deleted) {
                        System.out.println("Book deleted.");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 0:
                    // Exit the program
                    System.out.println("bye");
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }

        } while (choice != 0);

    }


	//method to collect book details from the user
    private static Book readBookInput(Scanner scanner) {
  
             System.out.print("Enter ISBN: ");
             String isbn = scanner.nextLine();

             System.out.print("Enter Title: ");
             String title = scanner.nextLine();

             System.out.print("Enter Author: ");
             String author = scanner.nextLine();

             System.out.print("Enter Genre: ");
             String genre = scanner.nextLine();
             
             //exception handling for invalid year.
             int year = 0;
             while (true) {
            	    System.out.print("Enter Year: ");
            	    String yearInput = scanner.nextLine();
            	    try {
            	        year = Integer.parseInt(yearInput);
            	        break; 
            	    } catch (NumberFormatException e) {
            	        System.out.println("Invalid year, enter a number.");
            	    }
            	}

             return new Book(isbn, title, author, genre, year);

    	 }
    }


