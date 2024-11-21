package coffee.command;

import coffee.model.CoffeeStorage;
import coffee.model.Truck;
import coffee.service.ConfigService;
import coffee.service.LoaderService;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<Integer, Command> commandMap;

    public CommandFactory(LoaderService loaderService, Truck truck, ConfigService configService, CoffeeStorage coffeeStorage) {
        commandMap = new HashMap<>();
        commandMap.put(1, new SortTruckProductsCommand(truck, loaderService));
        commandMap.put(2, new FindProductByQualityCommand(loaderService, truck));
        commandMap.put(3, new ShowTruckProductsCommand(truck));
        commandMap.put(4, new RemoveTruckProductCommand(truck, coffeeStorage));
        commandMap.put(5, new LoadTruckConfigCommand(configService));
        commandMap.put(6, new SaveTruckConfigCommand(configService));
        commandMap.put(7, new AddProductToStorageCommand(coffeeStorage));
        commandMap.put(8, new ShowStorageProductsCommand(coffeeStorage));
        commandMap.put(9, new AddProductFromStorageCommand(coffeeStorage, truck));
        commandMap.put(10, new UpdateTruckConfigCommand(truck));
        commandMap.put(11, new ExitCommand());
    }

    public Command getCommand(int choice) {
        Command command = commandMap.get(choice);
        if (command == null) {
            throw new IllegalArgumentException("Невірний вибір дії: " + choice);
        }
        return command;
    }
}