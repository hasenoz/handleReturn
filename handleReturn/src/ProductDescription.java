public class ProductDescription {
    private int itemID;
    private double price;
    private String description = "very nice item";
    private int count;
    public ProductDescription(int itemID, double price, int count){
        this.price = price;
        this.itemID = itemID;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getItemID(){
        return itemID;
    }
    public double getPrice(){
        return price;
    }
}
