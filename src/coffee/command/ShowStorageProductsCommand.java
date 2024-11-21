package coffee.command;

import coffee.model.CoffeeProduct;
import coffee.model.CoffeeStorage;

import java.util.Map;

public class ShowStorageProductsCommand implements Command {
    private final CoffeeStorage coffeeStorage;

    public ShowStorageProductsCommand(CoffeeStorage coffeeStorage) {
        this.coffeeStorage = coffeeStorage;
    }

    @Override
    public void execute() {
        if (coffeeStorage.getAllProducts().isEmpty()) {
            System.out.println("Склад порожній.");
        } else {
            System.out.println("Продукти на складі:");
            int index = 1;
            Map<CoffeeProduct, Integer> products = coffeeStorage.getAllProducts();
            for (Map.Entry<CoffeeProduct, Integer> entry : products.entrySet()) {
                CoffeeProduct product = entry.getKey();
                int quantity = entry.getValue();
                System.out.printf("%d. Назва: %s, Тип: %s, Ціна: %.2f, Вага: %.2f кг, Обʼєм: %.2f л, ",
                        index++, product.getName(), product.getType(), product.getPrice(), product.getWeight(), product.getVolume());
                if (product.getQuality() != null) {
                    System.out.printf("Аромат: %.1f, Смак: %.1f, Походження: %s, ",
                            product.getQuality().getAroma(), product.getQuality().getTaste(), product.getQuality().getOrigin());
                } else {
                    System.out.print("Якість: невизначена, ");
                }
                System.out.printf("Кількість: %d%n", quantity);
            }
        }
    }
}