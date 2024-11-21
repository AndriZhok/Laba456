package coffee.model;

import java.util.HashMap;
import java.util.Map;

public class Truck {
    private double maxVolume;
    private double maxBudget;
    private Map<CoffeeProduct, Integer> products;

    public Truck(double maxVolume, double maxBudget) {
        this.maxVolume = maxVolume;
        this.maxBudget = maxBudget;
        this.products = new HashMap<>();
    }

    public boolean addProduct(CoffeeProduct product, int quantity) {
        double totalVolume = product.getVolume() * quantity;
        double totalPrice = product.getPrice() * quantity;

        if (calculateTotalVolume() + totalVolume <= maxVolume &&
                calculateTotalPrice() + totalPrice <= maxBudget) {
            products.put(product, products.getOrDefault(product, 0) + quantity);
            return true;
        }
        return false;
    }

    public boolean removeProduct(CoffeeProduct product, int quantity) {
        if (products.containsKey(product)) {
            int currentQuantity = products.get(product);
            if (quantity >= currentQuantity) {
                products.remove(product);
            } else {
                products.put(product, currentQuantity - quantity);
            }
            return true;
        }
        return false;
    }

    public int getProductQuantity(CoffeeProduct product) {
        return products.getOrDefault(product, 0);
    }

    private double calculateTotalVolume() {
        return products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getVolume() * entry.getValue())
                .sum();
    }

    private double calculateTotalPrice() {
        return products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public Map<CoffeeProduct, Integer> getProducts() {
        return products;
    }

    public void setMaxVolume(double maxVolume) {
        this.maxVolume = maxVolume;
    }

    public void setMaxBudget(double maxBudget) {
        this.maxBudget = maxBudget;
    }
}