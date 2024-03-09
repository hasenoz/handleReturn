/* hasenoz */
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // creating the catalog
        ProductCatalog productCatalog = new ProductCatalog();
        // creating descriptions of items
        ProductDescription description1 = new ProductDescription(0,10,5);
        ProductDescription description2 = new ProductDescription(1,15,6);
        ProductDescription description3 = new ProductDescription(2,5,7);
        // adding these descriptions to the catalog
        productCatalog.addDescription(description1);
        productCatalog.addDescription(description2);
        productCatalog.addDescription(description3);
        //creating a completedSales list
        List<Sale> completedSales = new ArrayList<Sale>();
        //creating the items
        List<Item> lineItems = new ArrayList<Item>();
        //creating relations between items and descriptions from catalog
        Item lineItem1 = new SalesLineItem(productCatalog,0,4);
        Item lineItem2 = new SalesLineItem(productCatalog,1,3);
        Item lineItem3 = new SalesLineItem(productCatalog,2,6);
        //adding these items to the lineItems list
        lineItems.add(lineItem1);
        lineItems.add(lineItem2);
        lineItems.add(lineItem3);
        //creating Sale object. one sale object will be enough for testing
        Sale sale = new Sale(0);//Sale date is 0. Arranged it in Sale Class for easy implementation.
        /*
        You can change the present date for checking dueDate
         */
        Date.presentDate = 20; //day 20
        //setting lineItems
        sale.setLineItems(lineItems);
        /*this will make the sale completed. if the sale is not completed return process is not allowed
        normally unCompleted sale cannot be added to completedSales list but we are implementing return process
        so I checked this situation in handleReturnProcess, in Register class*/
        sale.becomeComplete();
        //adding the sale to the completedSales list
        completedSales.add(sale);
        //creating the store
        Store store = new Store();
        //setting completedSales
        store.setCompletedSales(completedSales);
        //creating register-store relation and register-catalog relation
        Register register = new Register(store,productCatalog);
        store.setRegister(register);
        //starting the return process
        register.handleReturnProcess();
    }
}
