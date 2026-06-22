package VendingMachine.state;

import VendingMachine.Product;
import VendingMachine.VendingMachine;
import VendingMachine.enums.Note;

public class IdleState implements State {
    
    @Override
    public void insertMoney(VendingMachine machine, Note note) {
        machine.tmpStorage = note.values()
    }

    @Override
    public void selectProduct(VendingMachine machine, Product product, int quantity) {

    }

    @Override
    public void cancelTransaction(VendingMachine machine) {

    }

    @Override
    public void dispenceProduct(VendingMachine machine) {

    }
}
