package VendingMachine;

import VendingMachine.enums.Note;
import VendingMachine.state.IdleState;
import VendingMachine.state.State;

public class VendingMachine {
    public String vendingMachineId;
    public State state;
    public int currentBalance; 
    public int totalProfit;
    
    public Bag currentCart;

    public VendingMachine() {
        this.state = new IdleState();
        this.currentBalance = 0;
        this.totalProfit = 0;
        this.currentCart = new Bag();
    }

    public void setState(State state) {
        this.state = state;
    }

    // Delegate actions to the current state
    public void insertMoney(Note note) {
        state.insertMoney(this, note);
    }

    public void selectProduct(Product product, int quantity) {
        state.selectProduct(this, product, quantity);
    }

    public void cancelTransaction() {
        state.cancelTransaction(this);
    }

    public void dispenceProduct() {
        state.dispenceProduct(this);
    }
}