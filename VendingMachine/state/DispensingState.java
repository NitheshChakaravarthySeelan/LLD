package VendingMachine.state;

import VendingMachine.Product;
import VendingMachine.VendingMachine;
import VendingMachine.enums.Note;

public class DispensingState implements State {
    
    @Override
    public void insertMoney(VendingMachine machine, Note note) {
        System.out.println("Error: Currently dispensing product. Please wait.");
    }

    @Override
    public void selectProduct(VendingMachine machine, Product product, int quantity) {
        System.out.println("Error: Currently dispensing product. Please wait.");
    }

    @Override
    public void cancelTransaction(VendingMachine machine) {
        System.out.println("Error: Cannot cancel while dispensing.");
    }

    @Override
    public void dispenceProduct(VendingMachine machine) {
        int totalCost = machine.currentCart.calculateTotalCost();
        
        System.out.println("Dispensing products...");
        // Here you would deduct from Inventory
        
        // Finalize transaction
        machine.totalProfit += totalCost;
        int change = machine.currentBalance - totalCost;
        
        if (change > 0) {
            System.out.println("Returning change: " + change);
        }
        
        // Cleanup for next user
        machine.currentBalance = 0;
        machine.currentCart.clear();
        
        // Return to Idle
        machine.setState(new IdleState());
        System.out.println("Transaction complete. Machine is now IDLE.");
    }
}
