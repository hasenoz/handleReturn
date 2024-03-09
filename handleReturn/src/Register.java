import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Register {
    private ProductCatalog catalog;
    private Store storeSystem;
    public Register(Store storeSystem, ProductCatalog catalog){
        this.catalog = catalog;
        this.storeSystem = storeSystem;
    }
    public void handleReturnProcess(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the saleID of the receipt: ");
        int saleID = scanner.nextInt();
        Sale sale = this.storeSystem.getSale(saleID);
        //if the sale is invalid
        if(sale == null){
            System.out.println("sale cannot found.");
            return;
        }
        // check for sale is completed or not
        if(!sale.getIsCompleted()){
            System.out.println("the sale is not finished yet. you cannot return any item of this process");
            return;
        }
        // dueDate check for return process
        if(Date.presentDate - sale.getReturnDueDate() >= 15){
            System.out.println("return process cannot be made because of due date of return has passed.");
            return;
        }
        //list of lineItems remaining after all passed return processes
        List<Item> removedReturnedLineItems = new ArrayList<>();
        //if there are any return process done on this sale before
        if(sale.getReturnedItems().size() != 0){
            for (int i = 0; i < sale.getLineItems().size(); i++) {
                for (int j = 0; j < sale.getReturnedItems().size(); j++) {
                    Item salesLineItem = sale.getLineItems().get(i);
                    Item returnedItem = sale.getReturnedItems().get(j);
                    //if return process done before at this item
                    if(salesLineItem.getDescription().getItemID() == returnedItem.getDescription().getItemID()){
                        int remainSalesLineItemQuantity = salesLineItem.getQuantity() - returnedItem.getQuantity();
                        Item item = new SalesLineItem(catalog,salesLineItem.getDescription().getItemID(),remainSalesLineItemQuantity);
                        removedReturnedLineItems.add(item);
                    }
                }
            }
        }else{
            removedReturnedLineItems = sale.getLineItems();
        }
        //holding the original line and returned items for reassign it later
        List<Item> originalLineItems;
        List<Item> originalReturnedItems;
        originalReturnedItems = sale.getReturnedItems();
        originalLineItems = sale.getLineItems();
        sale.setLineItems(removedReturnedLineItems);
        boolean isContinue = true;
        while(isContinue){
            System.out.print("enter the itemID of item: ");
            int itemID = scanner.nextInt();
            while(!sale.isInLineItems(itemID)){
                System.out.println("this item is not in this sale. please enter a valid id.");
                itemID = scanner.nextInt();
            }
            System.out.print("enter the quantity of item: ");
            int quantity = scanner.nextInt();
            while (quantity < 0 || sale.getItemInLineItems(itemID).getQuantity() < quantity){
                System.out.println("quantity cannot be negative and cannot be more than the quantity has buyed before. please enter a valid quantity.");
                quantity = scanner.nextInt();
            }
            Item returnedItem = new ReturnedItem(catalog,itemID,quantity);
            sale.addReturnedItem(returnedItem);
            System.out.print("if it is over press 0: ");
            int num = scanner.nextInt();
            if (num == 0){
                isContinue = false;
            }
        }
        //calculating the totalReturnAmount for new returns
        double returnAmount = sale.calculateTotalReturnAmount();
        System.out.println("Pay the costumer the return amount: " + returnAmount);
        //clerk enters if he/she done return payment
        System.out.print("Did you payed the amount to the Costumer? (1 for yes, 0 for no)");
        int num = scanner.nextInt();
        //if payment has done
        if(num == 1){
            sale.updateStockReturned();
            sale.makeAllReturnedItemsPaid();//marking the returned items as paid
            System.out.println("HandleReturn process completed successfully");
        }else{//return process is canceled if payment cannot be done
            System.out.println("Payment cannot be done so return process is canceled.");
            sale.setReturnedItems(originalReturnedItems);//the returned items before process is reassigned
        }
        //reassigning line items
        sale.setLineItems(originalLineItems);
    }
}
