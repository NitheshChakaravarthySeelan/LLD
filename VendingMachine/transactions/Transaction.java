package VendingMachine.transactions;

public class Transaction {
    public String transactionId;
    public Vault vault;

    public Transaction(String transactionId, Vault vault) {
        this.transactionId = transactionId;
        this.vault = vault;
    }

    public void addMoney(int value) {
        vault.addMoney(value);
    }

    public void removeMoney(int value) {
        vault.removeMoney(value);
    }

    public int getMoney() {
        return vault.getMoney();
    }
}