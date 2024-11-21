package coffee.command;

import coffee.model.Truck;

import java.util.Map;

public class ShowTruckProductsCommand implements Command {
    private final Truck truck;

    public ShowTruckProductsCommand(Truck truck) {
        this.truck = truck;
    }

    @Override
    public void execute() {
        if (truck.getProducts().isEmpty()) {
            System.out.println("Фургон порожній.");
        } else {
            System.out.println("Продукти у фургоні:");
            int index = 1;
            for (Map.Entry<coffee.model.CoffeeProduct, Integer> entry : truck.getProducts().entrySet()) {
                coffee.model.CoffeeProduct product = entry.getKey();
                int quantity = entry.getValue();
                System.out.printf("%d. Назва: %s, Тип: %s, Ціна: %.2f, Вага: %.2f кг, Обʼєм: %.2f л, Кількість: %d%n",
                        index++, product.getName(), product.getType(), product.getPrice(), product.getWeight(), product.getVolume(), quantity);
            }
        }
    }
}