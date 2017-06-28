package BookIt_IS;

public class POS_Line_Item {
    private int posliLineItemID; 
    private int orderID; 
    private int productID; 
    private int posliQuantity; 

    public int getPosliLineItemID() {
        return posliLineItemID;
    }

    public void setPosliLineItemID(int posliLineItemID) {
        this.posliLineItemID = posliLineItemID;
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
