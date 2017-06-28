package BookIt_IS;

public class Consumable {
    private int conID; 
    private String conName; 
    private double conCost; 
    private String conDesc; 

    public int getConID() {
        return conID;
    }

    public void setConID(int conID) {
        this.conID = conID;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public double getConCost() {
        return conCost;
    }

    public void setConCost(double conCost) {
        this.conCost = conCost;
    }

    public String getConDesc() {
        return conDesc;
    }

    public void setConDesc(String conDesc) {
        this.conDesc = conDesc;
    }
    
}
