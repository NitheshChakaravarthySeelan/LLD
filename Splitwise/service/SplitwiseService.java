package Splitwise.service;

import java.util.List;
import java.util.Map;

import Splitwise.enums.StrategyType;
import Splitwise.factory.SplitStrategyFactory;
import Splitwise.model.BalanceSheet;
import Splitwise.model.Expences;
import Splitwise.model.Group;
import Splitwise.model.Split;
import Splitwise.model.User;
import Splitwise.strategy.SplitStrategy;

public class SplitwiseService {
    
    private final BalanceSheet balanceSheet;

    public SplitwiseService() {
        this.balanceSheet = new BalanceSheet();
    }

    public void addExpense(Group group, User paidBy, int amount, List<User> splitUsers, StrategyType strategyType, Map<User, Double> splitData) {
        SplitStrategy strategy = SplitStrategyFactory.getStrategy(strategyType);
        List<Split> splits = strategy.calculateSplit(splitData, amount, splitUsers);

        Expences expense = new Expences(group, paidBy, splits, amount);
        
        if (group != null) {
            group.addExpence(expense);
        }
        
        balanceSheet.updateBalance(expense);
    }

    public void settleUp(User payer, User receiver, double amount) {
        balanceSheet.settle(payer, receiver, amount);
    }
    
    public void printBalances() {
        Map<User, Map<User, Double>> allBalances = balanceSheet.getAllBalance();
        boolean hasBalances = false;
        
        for (Map.Entry<User, Map<User, Double>> userEntry : allBalances.entrySet()) {
            User owner = userEntry.getKey();
            for (Map.Entry<User, Double> entry : userEntry.getValue().entrySet()) {
                Double balance = entry.getValue();
                if (balance == 0) continue;
                
                User target = entry.getKey();
                // Ensure we only print each relation once, to avoid duplicate outputs.
                // In BalanceSheet: if owner owes target, owner's map has target with negative balance
                // AND target's map has owner with positive balance.
                // Let's print only when balance > 0 (meaning target owes owner).
                if (balance > 0) {
                    hasBalances = true;
                    System.out.println(target.getUserName() + " owes -> " + owner.getUserName() + ": " + balance);
                }
            }
        }
        
        if (!hasBalances) {
            System.out.println("No balances to settle.");
        }
    }
}
