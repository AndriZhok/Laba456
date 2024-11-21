package coffee.command;

import coffee.model.CoffeeProduct;
import coffee.model.Truck;
import coffee.service.LoaderService;

import java.util.List;
import java.util.Scanner;

public class FindProductByQualityCommand implements Command {
    private final LoaderService loaderService;
    private final Truck truck;
    private final Scanner scanner;

    public FindProductByQualityCommand(LoaderService loaderService, Truck truck) {
        this.loaderService = loaderService;
        this.truck = truck;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        double minAroma = getValidDoubleInput("Мінімальний аромат (1-10): ");
        double maxAroma = getValidDoubleInput("Максимальний аромат (1-10): ");
        double minTaste = getValidDoubleInput("Мінімальний смак (1-10): ");
        double maxTaste = getValidDoubleInput("Максимальний смак (1-10): ");

        List<CoffeeProduct> results = loaderService.findProductByQuality(truck, minAroma, maxAroma, minTaste, maxTaste);

        if (results.isEmpty()) {
            System.out.println("Немає продуктів, що відповідають заданим параметрам.");
        } else {
            System.out.println("Продукти, що відповідають параметрам:");
            for (CoffeeProduct product : results) {
                if (product.getQuality() != null) {
                    System.out.printf("Назва: %s, Аромат: %.1f, Смак: %.1f, Походження: %s%n",
                            product.getName(),
                            product.getQuality().getAroma(),
                            product.getQuality().getTaste(),
                            product.getQuality().getOrigin());
                } else {
                    System.out.printf("Назва: %s, якість не задана%n", product.getName());
                }
            }
        }
    }

    private double getValidDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Невірний формат. Введіть число.");
                scanner.next();
            }
        }
    }
}