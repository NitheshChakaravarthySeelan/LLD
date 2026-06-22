package VendingMachine;

import java.util.HashMap;
import java.util.Map;

public class Bag {
    private String bagId;
    private Map<Product, Integer> productWithQuantity = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        this.productWithQuantity.put(product, quantity);
    }

    public void removeProduct(Product product) {
        this.productWithQuantity.remove(product);
    }

    public int calculateTotalCost() {
        int total = 0;
        
       for (Map.Entry<Product, Integer> entry: productWithQuantity.entrySet()) {
        int price = entry.getKey().getProductCost();
        total += (entry.getValue() * price);
       } 
        return total;
    }

    public void clear() {
        this.productWithQuantity = new HashMap<>();
    }
}
