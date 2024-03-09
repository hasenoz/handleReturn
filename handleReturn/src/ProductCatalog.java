import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    private List<ProductDescription> productDescriptionList;
    public ProductCatalog(){
        productDescriptionList = new ArrayList<>();
    }
    public List<ProductDescription> getProductDescriptionList() {
        return productDescriptionList;
    }
    public void addDescription(ProductDescription productDescription){
        productDescriptionList.add(productDescription);
    }
    public ProductDescription getProductDescription(int itemID){
        for (ProductDescription productDescription : productDescriptionList) {
            if (productDescription.getItemID() == itemID) {
                return productDescription;
            }
        }
        return null;
    }
}
