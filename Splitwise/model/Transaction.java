package Splitwise.model;

public class Transaction {
    private final User debtor;
    private final User creditor;
    private final double amount;

    public Transaction(User debtor, User creditor, double amount) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
    }

    public User getDebtor() {
        return debtor;
    }

    public User getCreditor() {
        return creditor;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return debtor.getUserName() + " owes -> " + creditor.getUserName() + ": " + amount;
    }
}
