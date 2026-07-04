package Splitwise.strategy;

import java.util.List;
import java.util.Map;

import Splitwise.model.Split;
import Splitwise.model.User;

public interface SplitStrategy {
    List<Split> calculateSplit(Map<User, Double> splitData, int amount);
}
