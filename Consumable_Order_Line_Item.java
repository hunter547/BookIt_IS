package BookIt_IS;


public class Consumable_Order_Line_Item {
    private int coliID; 
    private static int nextID =0;
    private int poID; 
    private int conID; 
    private int coliQuantity; 
    
    public Consumable_Order_Line_Item(){ 
        this.coliID = 0; 
        this.poID = 0; 
        this.conID =0; 
        this.coliQuantity = 0;
    } 
    
    public Consumable_Order_Line_Item(int poID, int conID, int coliQuantity){ 
        this.coliID = ++nextID;
        this.poID=poID; 
        this.conID=conID; 
        this.coliQuantity=coliQuantity;
    } 
    

    public Consumable_Order_Line_Item(int coliID, int poID, int conID, int coliQuantity) {
        this.coliID = coliID;
        this.poID = poID;
        this.conID = conID;
        this.coliQuantity = coliQuantity;
    }
    
    public int getColiLineItemID() {
        return coliID;
    }

    public void setColiLineItemID(int coliLineItemID) {
        this.coliID = coliLineItemID;
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
