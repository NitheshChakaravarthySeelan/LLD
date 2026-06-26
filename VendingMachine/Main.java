package VendingMachine;

import VendingMachine.enums.Note;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Booting up Vending Machine ===");
        VendingMachine machine = new VendingMachine();

        // 1. Create some products
        Product coke = new Product("1", "Coke", 25);
        Product chips = new Product("2", "Lays Chips", 15);

        System.out.println("\n--- SCENARIO 1: Successful Purchase with Change ---");
        // User inserts 50
        machine.insertMoney(Note.FIFTY);
        // User selects Coke (Cost: 25). Should dispense and return 25 change.
        machine.selectProduct(coke, 1);
        
        System.out.println("Machine Total Profit so far: " + machine.totalProfit);

        System.out.println("\n--- SCENARIO 2: Insufficient Funds then adding more ---");
        // User inserts 10
        machine.insertMoney(Note.TEN);
        // User selects Chips (Cost: 15). Should say insufficient funds.
        machine.selectProduct(chips, 1);
        // User realizes they need more, inserts another 10.
        machine.insertMoney(Note.TEN);
        // User selects again. Should dispense and return 5 change.
        machine.selectProduct(chips, 1);

        System.out.println("Machine Total Profit so far: " + machine.totalProfit);

        System.out.println("\n--- SCENARIO 3: Cancelling a Transaction ---");
        // User inserts 100
        machine.insertMoney(Note.HUNDRED);
        // User changes their mind and cancels
        machine.cancelTransaction();

        System.out.println("Machine Total Profit so far: " + machine.totalProfit);
        
        System.out.println("\n=== Shutting down Vending Machine ===");
    }
}
