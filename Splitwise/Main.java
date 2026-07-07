package Splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Splitwise.enums.StrategyType;
import Splitwise.model.Group;
import Splitwise.model.User;
import Splitwise.service.SplitwiseService;

public class Main {
    public static void main(String[] args) {
        SplitwiseService splitwiseService = SplitwiseService.getInstance();

        // 1. Create Users via Service Factory
        User u1 = splitwiseService.addUser("Alice", "alice@example.com");
        User u2 = splitwiseService.addUser("Bob", "bob@example.com");
        User u3 = splitwiseService.addUser("Charlie", "charlie@example.com");

        System.out.println("Users Created: " + u1.getUserName() + ", " + u2.getUserName() + ", " + u3.getUserName());

        // 2. Create Group via Service Factory
        List<User> members = new ArrayList<>();
        members.add(u1);
        members.add(u2);
        members.add(u3);
        Group group = splitwiseService.addGroup(u1, members);

        System.out.println("\n--- Adding Equal Expense ---");
        // Alice pays 300, split equally among Alice, Bob, Charlie (100 each)
        // Alice paid 300, so Bob owes Alice 100, Charlie owes Alice 100
        splitwiseService.addExpense(group, u1, 300, members, StrategyType.EQUAL, new HashMap<>());
        splitwiseService.printBalances();

        System.out.println("\n--- Adding Exact Expense ---");
        // Bob pays 200, Exact split: Alice=50, Bob=50, Charlie=100
        Map<User, Double> exactSplitData = new HashMap<>();
        exactSplitData.put(u1, 50.0);
        exactSplitData.put(u2, 50.0);
        exactSplitData.put(u3, 100.0);
        splitwiseService.addExpense(group, u2, 200, members, StrategyType.EXACT, exactSplitData);
        splitwiseService.printBalances();

        System.out.println("\n--- Adding Percentage Expense ---");
        // Charlie pays 100, Percentage split: Alice=20%, Bob=30%, Charlie=50%
        // So Alice=20, Bob=30, Charlie=50
        Map<User, Double> percentSplitData = new HashMap<>();
        percentSplitData.put(u1, 20.0);
        percentSplitData.put(u2, 30.0);
        percentSplitData.put(u3, 50.0);
        splitwiseService.addExpense(group, u3, 100, members, StrategyType.PERCENTAGE, percentSplitData);
        splitwiseService.printBalances();

        System.out.println("\n--- Settling up ---");
        // Bob settled 50 with Alice
        splitwiseService.settleUp(u2, u1, 50.0);
        System.out.println("Bob settled 50 with Alice.");
        splitwiseService.printBalances();
    }
}
