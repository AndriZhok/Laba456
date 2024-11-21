package coffee.model;

public class WholeBeanCoffee extends CoffeeProduct {

    public WholeBeanCoffee(String name, double price, double weight, double volume, QualityParams quality) {
        super(name, price, weight, volume, quality);
    }

    @Override
    public String getType() {
        return "Whole Bean";
    }
}
