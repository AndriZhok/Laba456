package coffee.model;

public class PackagedCoffeeProduct extends CoffeeProduct {
    private int quantity;

    public PackagedCoffeeProduct(String name, double price, double weight, double volume, QualityParams quality, int quantity) {
        super(name, price, weight, volume, quality);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getType() {
        return "Packaged";
    }
}