package VendingMachine.state;

import VendingMachine.Product;
import VendingMachine.VendingMachine;
import VendingMachine.enums.Note;

public class HasMoneyState implements State {
    
    @Override
    public void insertMoney(VendingMachine machine, Note note) {
        System.out.println("Inserted additional money: " + note.getValue());
        machine.currentBalance += note.getValue();
    }

    @Override
    public void selectProduct(VendingMachine machine, Product product, int quantity) {
        System.out.println("Product selected: " + product.getProductName());
        machine.currentCart.addProduct(product, quantity);

        int totalCost = machine.currentCart.calculateTotalCost();
        
        if (machine.currentBalance >= totalCost) {
            System.out.println("Sufficient funds. Proceeding to dispense.");
            machine.setState(new DispensingState());
            // Automatically trigger dispensing if funds are enough
            machine.dispenceProduct();
        } else {
            System.out.println("Insufficient funds. Total cost: " + totalCost + ". Current balance: " + machine.currentBalance);
            System.out.println("Please insert more money or cancel.");
        }
    }

    @Override
    public void cancelTransaction(VendingMachine machine) {
        System.out.println("Transaction cancelled. Returning: " + machine.currentBalance);
        machine.currentBalance = 0;
        machine.currentCart.clear();
        machine.setState(new IdleState());
    }

    @Override
    public void dispenceProduct(VendingMachine machine) {
        System.out.println("Error: Please select a product to buy first.");
    }
} 