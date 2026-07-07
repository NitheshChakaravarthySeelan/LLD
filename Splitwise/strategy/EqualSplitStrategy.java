package Splitwise.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Splitwise.model.Split;
import Splitwise.model.User;

public class EqualSplitStrategy implements SplitStrategy {
    
    @Override
    public List<Split> calculateSplit(Map<User, Double> splitData, int amount, List<User> users) {
        double each = amount / users.size();
        List<Split> splits = new ArrayList<>();
        
        for (User user: users) {
            splits.add(new Split(user, each));
        }

        return splits;
    }
}
