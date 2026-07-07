package Splitwise.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


// we should not use the owner user cause it will become a headache to update all the user ledger when updating for only one expence.
public class BalanceSheet {

    private final Map<User, Map<User, Double>> balanceSheet = new ConcurrentHashMap<>();

    public void updateBalance(Expences expences) {
        User paidBy = expences.getPaidBy();
        List<Split> splits = expences.getSplit();

        for (Split split: splits) {
            User borrower = split.getUser();

            if (borrower.equals(paidBy)) {
                continue;
            }

            double amount = split.getAmount();

            // add money to the owner and remove money from the borrower 
            // we are not actually removing but marking it like that for repayment logic. 
            updatePairBalance(paidBy, borrower, amount);
            updatePairBalance(borrower, paidBy, -amount);


        }
    }

    private void updatePairBalance(User user1, User user2, double amount) {
        balanceSheet.computeIfAbsent(user1, k -> new ConcurrentHashMap<>());
        Map<User, Double> userBalance = balanceSheet.get(user1);
        userBalance.put(user2, userBalance.getOrDefault(user2, 0.0) + amount);
    }

    private List<String> getUserBalances(User user) {
       List<String> report = new ArrayList<>();
       
       Map<User, Double> userBalance = balanceSheet.get(user);

       if (userBalance == null || userBalance.isEmpty()) {
        return report;
       }
    
       // iterate on the dict using this.
       for (Map.Entry<User, Double> entry: userBalance.entrySet()) {
        Double balance = entry.getValue();

        if (balance == 0) continue;

        String targetName = entry.getKey().getUserName();
        String ownerName = user.getUserName();
        if (balance > 0) {
            reportFormat(report, targetName, ownerName, balance);
        } else {
            reportFormat(report, ownerName, targetName, Math.abs(balance));
        }
       }
       return report;
    }

    private void reportFormat(List<String> report,String userName,String targetName, double amount) {
        report.add(userName + "owes -> " + targetName + amount);

    }
    public void settle(
            User payer,
            User receiver,
            double amount
    ){

        updatePairBalance(
            payer,
            receiver,
            amount
        );


        updatePairBalance(
            receiver,
            payer,
            -amount
        );
    }

    public Map<User, Map<User, Double>> getAllBalance() {
        return this.balanceSheet;
    }
}