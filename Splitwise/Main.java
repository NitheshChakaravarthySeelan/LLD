package Splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Splitwise.enums.StrategyType;
import Splitwise.model.Group;
import Splitwise.model.User;
import Splitwise.model.Transaction;
import Splitwise.service.SplitwiseService;

public class Main {
    public static void main(String[] args) {
        SplitwiseService splitwiseService = SplitwiseService.getInstance();

        // 1. Create Users via Service Factory
        User u1 = splitwiseService.addUser("Alice", "alice@example.com");
        User u2 = splitwiseService.addUser("Bob", "bob@example.com");
        User u3 = splitwiseService.addUser("Charlie", "charlie@example.com");

        System.out.println("Users Created: " + u1.getUserName() + ", " + u2.getUserName() + ", " + u3.getUserName());

        // 2. Create Groups
        List<User> members1 = new ArrayList<>();
        members1.add(u1);
        members1.add(u2);
        members1.add(u3);
        Group group1 = splitwiseService.addGroup(u1, members1);

        List<User> members2 = new ArrayList<>();
        members2.add(u2);
        members2.add(u3);
        Group group2 = splitwiseService.addGroup(u2, members2);

        System.out.println("\n--- Adding Expenses to Group 1 ---");
        // Alice pays 30 for Alice, Bob, Charlie (10 each). 
        // Bob owes Alice 10, Charlie owes Alice 10
        splitwiseService.addExpense(group1, u1, 30, members1, StrategyType.EQUAL, new HashMap<>());
        
        // A pays 100 for B. (B owes A 100)
        Map<User, Double> exactData1 = new HashMap<>();
        exactData1.put(u2, 100.0);
        splitwiseService.addExpense(group1, u1, 100, List.of(u2), StrategyType.EXACT, exactData1);

        // B pays 100 for C. (C owes B 100)
        Map<User, Double> exactData2 = new HashMap<>();
        exactData2.put(u3, 100.0);
        splitwiseService.addExpense(group1, u2, 100, List.of(u3), StrategyType.EXACT, exactData2);
        
        System.out.println("\n--- Adding Expenses to Group 2 ---");
        // In Group 2, C pays 100 for B (B owes C 100)
        Map<User, Double> exactData3 = new HashMap<>();
        exactData3.put(u2, 100.0);
        splitwiseService.addExpense(group2, u3, 100, List.of(u2), StrategyType.EXACT, exactData3);

        System.out.println("\n[Before Simplify] Printing Global Balances:");
        splitwiseService.printBalances();

        System.out.println("\n--- SIMPLIFYING DEBTS ON-THE-FLY FOR GROUP 1 ---");
        List<Transaction> group1Transactions = splitwiseService.simplifyGroupDebts(group1.getId());
        for(Transaction t : group1Transactions) {
            System.out.println(t.toString());
        }
        
        System.out.println("\n--- SIMPLIFYING DEBTS ON-THE-FLY FOR GROUP 2 ---");
        List<Transaction> group2Transactions = splitwiseService.simplifyGroupDebts(group2.getId());
        for(Transaction t : group2Transactions) {
            System.out.println(t.toString());
        }
    }
}
