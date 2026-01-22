package application;

import java.io.*;
import java.util.ArrayList;

public class BookList {
	//array list to store books
    private ArrayList<Book> books; 
    //file to save and load books.
    private final String FILE_NAME = "books.txt";
    // constructor is called when a BookList object is created
    public BookList() {
        books = new ArrayList<>();
        //loads existing books from the file.
        loadBooksFromFile(); 
    }

    // Load books from the file into the list
    private void loadBooksFromFile() {
    	//exception for creating the file.
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                // If the file doesn't exist, it returns
                return;
            }
            
            //allows to read from a file line by line.
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            // reads each line and creates a book object
            while ((line = reader.readLine()) != null) {
            	// splits the line of text into parts using | as a separator
                String[] parts = line.split("\\|"); 
                if (parts.length == 5) {
                    String isbn = parts[0];
                    String title = parts[1];
                    String author = parts[2];
                    String genre = parts[3];
                    //used to store an integer as a string.
                    int year = Integer.parseInt(parts[4]);

                    Book book = new Book(isbn, title, author, genre, year);
                    books.add(book); // adds the book to the book list
                }
            }

            reader.close(); //closes bufferreader
        } catch (IOException e) {
            System.out.println("Error saving books." + e.getMessage());
        }
    }

    // Save the current list of books to the file
    private void saveBooksToFile() {
        try {
        	//opens the file and allows to write text on it.
            PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME));

            for (Book book : books) {
                // Write book data in one line separated by |
                writer.println(book.getIsbn() + "|" + book.getTitle() + "|" +
                               book.getAuthor() + "|" + book.getGenre() + "|" + book.getYear());
            }

            writer.close(); // closes the printwriter
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }

    // Add a new book
    public void addBook(Book book) {
        books.add(book);
        saveBooksToFile(); // Save changes to file
        System.out.println("Book added.");
    }

    // Edit a book by its ISBN
    public boolean editBook(String isbn, Book updatedBook) {
    	//loop that goes through each book in the books list using index i.
        for (int i = 0; i < books.size(); i++) {
        	//gets the chosen book at index i and it is stored in a variable
            Book chosen = books.get(i);
            //Checks if the chosen book's ISBN matches the one the user types.
            if (chosen.getIsbn().equalsIgnoreCase(isbn)) {
            	// this replaces the book
                books.set(i, updatedBook); 
                saveBooksToFile();
                return true;
            }
        }
        //returns false if no matching ISBN is found
        return false; 
    }

    // Delete a book by its ISBN
    public boolean deleteBook(String isbn) {
    	//for loop that goes through each book object in the books list
        for (Book book : books) {
        	//checks if the ISBN matches the one the user typed.
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
            	//removes the book
                books.remove(book);
                //saves to the books.txt
                saveBooksToFile();
                return true;
            }
        }
        //returns false if book not found
        return false; 
    }

    // Show all books
    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                System.out.println(book); // Will use Book's toString() method
                System.out.println();
            }
        }
    }

    // Search books by title
    public void searchByTitle(String title) {
    	//used to track if a match is found
        boolean found = false;

        for (Book book : books) {
        	//checks if the book's title matches and is also ensures case insensitivity.
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(book);
                System.out.println();
                found = true;
            }
        }
        
        //prints if no book is found.
        if (!found) {
            System.out.println("No books found with that title.");
        }
    }

    // Search books by author
    public void searchByAuthor(String author) {
    	//used to track if match if found
        boolean found = false;

        for (Book book : books) {
        	//checks if the author matches and also ensures case insensitivity.
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                System.out.println(book);
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with that author.");
        }
    }

    // Search for a single book by ISBN
    public Book searchByISBN(String isbn) {
        for (Book book : books) {
        	//checks if book matches ISBN
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book; // Return the matching book
            }
        }
        //returns null if no match is found
        return null;
    }
}
