package coffee.command;

import coffee.model.CoffeeProduct;
import coffee.model.Truck;
import coffee.service.LoaderService;

import java.util.List;

public class SortTruckProductsCommand implements Command {
    private final Truck truck;
    private final LoaderService loaderService;

    // Конструктор, що приймає Truck і LoaderService
    public SortTruckProductsCommand(Truck truck, LoaderService loaderService) {
        this.truck = truck;
        this.loaderService = loaderService;
    }
    @Override
    public void execute() {
        List<CoffeeProduct> sortedProducts = loaderService.sortProductsByPriceWeight(truck);

        if (sortedProducts.isEmpty()) {
            System.out.println("Фургон порожній, немає продуктів для сортування.");
        } else {
            System.out.println("Продукти відсортовано за співвідношенням ціна/вага:");
            int index = 1;
            for (CoffeeProduct product : sortedProducts) {
                System.out.printf("%d. Назва: %s, Ціна: %.2f, Вага: %.2f кг, Ціна/Вага: %.2f, Кількість: %d%n",
                        index++, product.getName(), product.getPrice(), product.getWeight(),
                        product.getPrice() / product.getWeight(), truck.getProductQuantity(product));
            }
        }
    }
}