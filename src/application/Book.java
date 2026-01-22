package application;

public class Book {
	
	//fields 
	private String isbn;
	private String title;
	private String author;
	private String genre;
	private int year;
	
	//constructor
	public Book(String isbn, String title, String author, String genre, int year) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.year = year;
	}
	
	 // getters and setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    // displays information of the book.
    @Override
    public String toString() {
    	return "ISBN: " + isbn 
    			+ " Title: " + title 
    			+ " Author: " + author 
    			+ " Genre: " + genre 
    			+ " Year: " + year;
    }

}
    


