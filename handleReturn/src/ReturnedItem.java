public class ReturnedItem extends Item{
    private boolean isPaid;
    public ReturnedItem(ProductCatalog catalog, int itemID, int quantity) {
        super(catalog, itemID, quantity);
        isPaid = false;
    }

    public void makePaid() {
        isPaid = true;
    }
    public boolean getIsPayed(){
        return isPaid;
    }

    @Override
    public void updateStock() { //if it is not paid it will update stock. else it was updated in passed return processes.
        if(!isPaid){
            System.out.println("Stock Updated for this item: ");
            System.out.print("itemID: ");
            System.out.println(super.getDescription().getItemID());
            //updating the stock
            System.out.print("the stock before: ");
            System.out.println(super.getDescription().getCount());
            System.out.print("the stock after return: ");
            super.getDescription().setCount(super.getDescription().getCount()+super.getQuantity());
            System.out.println(super.getDescription().getCount());
        }
    }
    @Override
    public double getSubTotal(){
        if(isPaid){//if it is paid before do not contain this returned items returnPayment amount
            return 0;
        }
        double price = super.getDescription().getPrice();
        int quantity = super.getQuantity();
        return price * quantity;
    }
}
