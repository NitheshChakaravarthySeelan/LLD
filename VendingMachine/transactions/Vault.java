package VendingMachine.transactions;

// we need something to calculate and maintain the money inside the machine
public class Vault {
    private int value;

    public Vault(int money) {
        this.value = money;
    }

    public void addMoney(int money) {
        this.value += money;
    }

    public void removeMoney(int money) {
        if (this.value >= money) {
            this.value -= money;
        } else {
            throw new Error("Not enough money");
        }
    }

    public int getMoney() {
        return this.value;
    }
}
