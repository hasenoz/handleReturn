
/*not any implementation required for this class
* if implementation will be required you must add check for
* sale count is not more than the count in stock(description)*/
public class SalesLineItem extends Item{
    public SalesLineItem(ProductCatalog catalog, int itemID, int quantity) {
        super(catalog, itemID, quantity);
    }

    @Override
    public void updateStock() {
        super.getDescription().setCount(super.getDescription().getCount()-super.getQuantity());
    }

    @Override
    public double getSubTotal() {
        return 0;
    }
}
