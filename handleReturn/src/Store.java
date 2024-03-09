import java.util.List;

public class Store {
    private final String name = "Store";
    private final String address = "Ege University";
    private List<Sale> completedSales;
    private Register register;
    public List<Sale> getCompletedSales(){
        return completedSales;
    }
    public Sale getSale(int saleID){
        for (int i = 0; i < getCompletedSales().size(); i++) {
            if (getCompletedSales().get(i).getSaleID() == saleID ){
                return getCompletedSales().get(i);
            }
        }
        return null;
    }

    public void setCompletedSales(List<Sale> completedSales) {
        this.completedSales = completedSales;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public Register getRegister() {
        return register;
    }
}

