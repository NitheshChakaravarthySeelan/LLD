package Splitwise.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import Splitwise.enums.StrategyType;
import Splitwise.factory.SplitStrategyFactory;
import Splitwise.model.BalanceSheet;
import Splitwise.model.Expences;
import Splitwise.model.Group;
import Splitwise.model.Split;
import Splitwise.model.User;
import Splitwise.strategy.SplitStrategy;

public class SplitwiseService {
    
    private static SplitwiseService instance;
    private final BalanceSheet balanceSheet;
    private final Map<String, User> users;
    private final Map<String, Group> groups;

    private SplitwiseService() {
        this.balanceSheet = new BalanceSheet();
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
        
        balanceSheet.updateBalance(expense);
    }

    public synchronized void settleUp(User payer, User receiver, double amount) {
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
