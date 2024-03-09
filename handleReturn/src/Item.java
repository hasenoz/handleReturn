public abstract class Item{
    private int quantity;
    private ProductDescription description;
    public Item(ProductCatalog catalog,int itemID, int quantity){
        this.quantity = quantity;
        this.description = catalog.getProductDescription(itemID);
    }
    public abstract void updateStock();
    public abstract double getSubTotal();
    public ProductDescription getDescription(){
        return description;
    }

    public int getQuantity() {
        return quantity;
    }
}
