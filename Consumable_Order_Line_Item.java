package BookIt_IS;


public class Consumable_Order_Line_Item {
    private int coliLineItemID; 
    private int poID; 
    private int conID; 
    private int coliQuantity; 

    public int getColiLineItemID() {
        return coliLineItemID;
    }

    public void setColiLineItemID(int coliLineItemID) {
        this.coliLineItemID = coliLineItemID;
    }

    public int getPoID() {
        return poID;
    }

    public void setPoID(int poID) {
        this.poID = poID;
    }

    public int getConID() {
        return conID;
    }

    public void setConID(int conID) {
        this.conID = conID;
    }

    public int getColiQuantity() {
        return coliQuantity;
    }

    public void setColiQuantity(int coliQuantity) {
        this.coliQuantity = coliQuantity;
    }
    
}
