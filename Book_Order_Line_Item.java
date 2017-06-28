package BookIt_IS;

public class Book_Order_Line_Item {
    private int boliLineItemID; 
    private int orderID; 
    private int bookID;
    private int boliQuantity; 

    public int getBoliLineItemID() {
        return boliLineItemID;
    }

    public void setBoliLineItemID(int boliLineItemID) {
        this.boliLineItemID = boliLineItemID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getBoliQuantity() {
        return boliQuantity;
    }

    public void setBoliQuantity(int boliQuantity) {
        this.boliQuantity = boliQuantity;
    }
    
        
}
