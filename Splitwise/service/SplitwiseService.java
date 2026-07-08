package Splitwise.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import Splitwise.enums.StrategyType;
import Splitwise.factory.SplitStrategyFactory;
import Splitwise.model.BalanceSheet;
import Splitwise.model.Expences;
import Splitwise.model.Group;
import Splitwise.model.Split;
import Splitwise.model.User;
import Splitwise.model.Transaction;
import Splitwise.strategy.SplitStrategy;

public class SplitwiseService {
    
    private static SplitwiseService instance;
    private final BalanceSheet globalBalanceSheet;
    private final Map<String, User> users;
    private final Map<String, Group> groups;

    private SplitwiseService() {
        this.globalBalanceSheet = new BalanceSheet();
        this.users = new ConcurrentHashMap<>();
        this.groups = new ConcurrentHashMap<>();
    }

    public static synchronized SplitwiseService getInstance() {
        if (instance == null) {
            instance = new SplitwiseService();
        }
        return instance;
    }

    public User addUser(String userName, String email) {
        User user = new User(userName, email);
        users.put(user.getId(), user);
        return user;
    }

    public Group addGroup(User createdBy, List<User> members) {
        Group group = new Group(createdBy, members, new java.util.ArrayList<>());
        groups.put(group.getId(), group);
        return group;
    }

    public User getUser(String id) {
        return users.get(id);
    }

    public Group getGroup(String id) {
        return groups.get(id);
    }

    public synchronized void addExpense(Group group, User paidBy, int amount, List<User> splitUsers, StrategyType strategyType, Map<User, Double> splitData) {
        SplitStrategy strategy = SplitStrategyFactory.getStrategy(strategyType);
        List<Split> splits = strategy.calculateSplit(splitData, amount, splitUsers);

        Expences expense = new Expences(group, paidBy, splits, amount);
        
        if (group != null) {
            group.addExpence(expense);
        }
        
        globalBalanceSheet.updateBalance(expense);
    }

    public synchronized void settleUp(User payer, User receiver, double amount) {
        globalBalanceSheet.settle(payer, receiver, amount);
    }
    
    public List<Transaction> simplifyGroupDebts(String groupId) {
        Group group = groups.get(groupId);
        if (group == null) throw new IllegalArgumentException("Group not found");

        Map<User, Double> netBalances = new HashMap<>();

        // Calculate balances dynamically from group expenses
        for (Expences exp : group.getExpences()) {
            User paidBy = exp.getPaidBy();
            for (Split split : exp.getSplit()) {
                if (split.getUser().equals(paidBy)) continue;
                
                double amount = split.getAmount();
                netBalances.put(paidBy, netBalances.getOrDefault(paidBy, 0.0) + amount);
                netBalances.put(split.getUser(), netBalances.getOrDefault(split.getUser(), 0.0) - amount);
            }
        }

        List<Map.Entry<User, Double>> creditors = new java.util.ArrayList<>();
        List<Map.Entry<User, Double>> debtors = new java.util.ArrayList<>();

        for (Map.Entry<User, Double> entry : netBalances.entrySet()) {
            if (entry.getValue() > 0.01) creditors.add(entry);
            else if (entry.getValue() < -0.01) debtors.add(entry);
        }

        List<Transaction> transactions = new java.util.ArrayList<>();
        int i = 0, j = 0;
        
        while (i < creditors.size() && j < debtors.size()) {
            Map.Entry<User, Double> creditor = creditors.get(i);
            Map.Entry<User, Double> debtor = debtors.get(j);

            double creditAmount = creditor.getValue();
            double debitAmount = Math.abs(debtor.getValue());
            double settledAmount = Math.min(creditAmount, debitAmount);

            transactions.add(new Transaction(debtor.getKey(), creditor.getKey(), settledAmount));

            creditor.setValue(creditAmount - settledAmount);
            debtor.setValue(debtor.getValue() + settledAmount); // going up to 0

            if (creditor.getValue() < 0.01) i++;
            if (Math.abs(debtor.getValue()) < 0.01) j++;
        }

        return transactions;
    }
    
    public void printBalances() {
        printBalancesForSheet(globalBalanceSheet, "Global Balances:");
    }

    private void printBalancesForSheet(BalanceSheet sheet, String header) {
        System.out.println(header);
        Map<User, Map<User, Double>> allBalances = sheet.getAllBalance();
        boolean hasBalances = false;
        
        for (Map.Entry<User, Map<User, Double>> userEntry : allBalances.entrySet()) {
            User owner = userEntry.getKey();
            for (Map.Entry<User, Double> entry : userEntry.getValue().entrySet()) {
                Double balance = entry.getValue();
                if (balance == 0) continue;
                
                User target = entry.getKey();
                if (balance > 0) {
                    hasBalances = true;
                    System.out.println(target.getUserName() + " owes -> " + owner.getUserName() + ": " + balance);
                }
            }
        }
        
        if (!hasBalances) {
            System.out.println("No balances to settle.");
        }
        System.out.println();
    }
}
