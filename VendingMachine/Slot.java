package VendingMachine;

public class Slot {
    private String slotId;
    private String slotName;
    private Product product;
    private int quantity;

    public Slot(String slotId, String slotName, Product product, int quantity) {
        this.slotId = slotId;
        this.slotName = slotName;
        this.product = product;
        this.quantity = quantity;
    }

    public String getSlotId() {
        return this.slotId;
    }

    public String getSlotName() {
        return this.slotName;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public void setProduct(Product newProduct) {
        this.product = newProduct;
    }
}