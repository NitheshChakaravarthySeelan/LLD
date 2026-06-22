package VendingMachine;

public class Product {
    private String productId;
    private String productName; 
    private int productCost;

    public Product(String productId, String productName, int productCost) {
        this.productId = productId;
        this.productName = productName;
        this.productCost = productCost;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public int getProductCost() {
        return this.productCost;
    }

    public void setProductCost(int productCost) {
        this.productCost = productCost;
    }
}