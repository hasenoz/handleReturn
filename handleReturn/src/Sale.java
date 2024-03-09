import java.util.ArrayList;
import java.util.List;

public class Sale {
    private int saleID;

    // for easier implementation used int values for time
    private int time = 0;//sale Date
    private boolean isComplete;
    private List<Item> lineItems;
    private List<Item> returnedItems;//if null; no return process never done before
    private int returnDueDate = 15 + time; // if (present time - sale time)  is greater than 15 no returning processes allowed
    public Sale(int saleID){
        this.saleID = saleID;
        lineItems = new ArrayList<>();
        returnedItems = new ArrayList<>();
        isComplete = false;
    }

    public void setReturnedItems(List<Item> returnedItems) {
        this.returnedItems = returnedItems;
    }

    public List<Item> getReturnedItems() {
        return returnedItems;
    }

    public boolean getIsCompleted(){
        return isComplete;
    }

    public void setLineItems(List<Item> lineItems) {
        this.lineItems = lineItems;
    }

    public void becomeComplete(){
        isComplete = true;
    }
    public int getSaleID(){
        return saleID;
    }

    public int getReturnDueDate() {
        return returnDueDate;
    }
    public Item getItemInLineItems(int itemID){
        for (Item lineItem : lineItems) {
            if (lineItem.getDescription().getItemID() == itemID) {
                return lineItem;
            }
        }
        return null;//aslında bunun kontrolü itemID valid mi değil mi diye bakarken yapıldı ama tedbir olarak koydum
    }
    public List<Item> getLineItems() {
        return lineItems;
    }
    public boolean isInLineItems(int itemID){
        for (Item lineItem : lineItems) {
            if (lineItem.getDescription().getItemID() == itemID) {
                return true;
            }

        }
        return false;
    }

    public void addReturnedItem(Item item){
        returnedItems.add(item);
    }
    public double calculateTotalReturnAmount(){
        double total = 0;
        for (Item returnedItem : returnedItems) {
            total += returnedItem.getSubTotal();
        }
        return total;
    }
    public void updateStockReturned(){
        for (Item returnedItem : returnedItems) {
            returnedItem.updateStock();
        }
    }
    public void makeAllReturnedItemsPaid(){
        for (Item returnedItem : returnedItems) {
            ReturnedItem reItem = (ReturnedItem) returnedItem;
            reItem.makePaid();
        }
    }
}
