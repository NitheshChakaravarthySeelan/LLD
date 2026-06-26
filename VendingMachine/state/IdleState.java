package VendingMachine.state;

import VendingMachine.Product;
import VendingMachine.VendingMachine;
import VendingMachine.enums.Note;

public class IdleState implements State {
    
    @Override
    public void insertMoney(VendingMachine machine, Note note) {
        System.out.println("Inserted money: " + note.getValue());
        machine.currentBalance += note.getValue();
        // Transition to next state
        machine.setState(new HasMoneyState());
    }

    @Override
    public void selectProduct(VendingMachine machine, Product product, int quantity) {
        System.out.println("Error: Please insert money first.");
    }

    @Override
    public void cancelTransaction(VendingMachine machine) {
        System.out.println("Error: No transaction in progress.");
    }

    @Override
    public void dispenceProduct(VendingMachine machine) {
        System.out.println("Error: Please insert money and select a product first.");
    }
}
