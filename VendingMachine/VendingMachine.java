package VendingMachine;

import java.io.ObjectInputFilter.Status;
import VendingMachine.transactions.Transaction;

public class VendingMachine {
    public String vendingMachineId;
    public Status status;
    public Transaction transaction;
    public int tmpStorage;
}