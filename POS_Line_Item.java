package BookIt_IS;

public class POS_Line_Item {
    private int posliID; 
    private static int nextID = 0;
    private int orderID; 
    private int productID; 
    private int posliQuantity; 

    public POS_Line_Item() {
        this.posliID = 0;
        this.orderID = 0;
        this.productID = 0;
        this.posliQuantity = 0;
    }

    public POS_Line_Item(int orderID, int productID, int posliQuantity) {
        this.posliID = ++nextID;
        this.orderID = orderID;
        this.productID = productID;
        this.posliQuantity = posliQuantity;
    }  

    public POS_Line_Item(int posliID, int orderID, int productID, int posliQuantity) {
        this.posliID = posliID;
        this.orderID = orderID;
        this.productID = productID;
        this.posliQuantity = posliQuantity; 
        if(posliID>nextID){ 
            nextID = posliID;
        }
    }

    public int getPosliLineItemID() {
        return posliID;
    }

    public void setPosliLineItemID(int posliID) {
        this.posliID = posliID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getPosliQuantity() {
        return posliQuantity;
    }

    public void setPosliQuantity(int posliQuantity) {
        this.posliQuantity = posliQuantity;
    }
    
}
