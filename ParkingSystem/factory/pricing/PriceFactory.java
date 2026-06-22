package factory.pricing;

import model.ParkingTicket;
import enums.PriceCalculationType;
import strategy.FlatPricing;
import strategy.TimeBased;

public class PriceFactory {
    public static double calculatePrice(ParkingTicket ticket, PriceCalculationType priceCalculationType) {
        switch(priceCalculationType) {
            case TIME_BASED: return new TimeBased().calculateFee(ticket);
            case FLAT_PRICING: return new FlatPricing().calculateFee(ticket);
            default: throw new IllegalArgumentException("Invalid price calculation type: " + priceCalculationType);
        }
    }
}
