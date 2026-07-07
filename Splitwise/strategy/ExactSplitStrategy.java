package Splitwise.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Splitwise.model.Split;
import Splitwise.model.User;

public class ExactSplitStrategy implements SplitStrategy {
    
    @Override
    public List<Split> calculateSplit(Map<User, Double> splitData, int amount, List<User> users) {
        // we need to check if the total amount is equal to the split data
        List<Split> splits = new ArrayList<>();
        
        for (User user: users) {
            splits.add(new Split(user, splitData.get(user)));
        } 

        return splits;
    }
}
