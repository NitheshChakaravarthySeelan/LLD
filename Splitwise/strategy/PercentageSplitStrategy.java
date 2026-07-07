package Splitwise.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Splitwise.model.Split;
import Splitwise.model.User;

public class PercentageSplitStrategy implements SplitStrategy {
    @Override
    public List<Split> calculateSplit(Map<User, Double> splitData, int amount, List<User> users) {
        List<Split> splits = new ArrayList<>();
        for (User user: users) {
            double percent = splitData.getOrDefault(user, 0.0);
            double exactAmount = (amount * percent) / 100.0;
            splits.add(new Split(user, exactAmount));
        }
        return splits;
    }
}
