package Splitwise.factory;

import Splitwise.enums.StrategyType;
import Splitwise.strategy.EqualSplitStrategy;
import Splitwise.strategy.ExactSplitStrategy;
import Splitwise.strategy.PercentageSplitStrategy;
import Splitwise.strategy.SplitStrategy;

public class SplitStrategyFactory {
    
    public static SplitStrategy getStrategy(StrategyType type) {
        switch (type) {
            case EQUAL:
                return new EqualSplitStrategy();
            case EXACT:
                return new ExactSplitStrategy();
            case PERCENTAGE:
                return new PercentageSplitStrategy();
            default:
                throw new IllegalArgumentException("Unknown strategy type: " + type);
        }
    }
}
