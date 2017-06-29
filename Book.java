
package BookIt_IS;


public class Book {
    private int bookID; 
    private static int nextID = 0;
    private String bookTitle; 
    private String bookAuthor; 
    private double bookCost; 
    private double bookSalePrice; 
    private String bookDescription; 

    public Book(){ 
        this.bookID = 0; 
        this.bookTitle=""; 
        this.bookAuthor=""; 
        this.bookCost=0.0; 
        this.bookSalePrice=0.0; 
        this.bookDescription ="";
    } 
    
    public Book(String bookTitle, String bookAuthor, double bookCost, double bookSalePrice, String bookDescription){ 
        this.bookTitle = bookTitle; 
        this.bookAuthor = bookAuthor; 
        this.bookCost = bookCost; 
        this.bookSalePrice = bookSalePrice; 
        this.bookDescription = bookDescription;
    }
    
    
    
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public double getBookCost() {
        return bookCost;
    }

    public void setBookCost(double bookCost) {
        this.bookCost = bookCost;
    }

    public double getBookSalePrice() {
        return bookSalePrice;
    }

    public void setBookSalePrice(double bookSalePrice) {
        this.bookSalePrice = bookSalePrice;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
    
    
}
