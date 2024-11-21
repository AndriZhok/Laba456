package coffee.model;

public class GroundCoffee extends CoffeeProduct {

    public GroundCoffee(String name, double price, double weight, double volume, QualityParams quality) {
        super(name, price, weight, volume, quality);
    }

    @Override
    public String getType() {
        return "Ground Coffee";
    }
}
