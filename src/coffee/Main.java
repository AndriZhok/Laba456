package coffee;

import coffee.command.CommandFactory;
import coffee.menu.Menu;
import coffee.model.CoffeeStorage;
import coffee.model.Truck;
import coffee.service.ConfigService;
import coffee.service.LoaderService;

public class Main {
    public static void main(String[] args) {
        LoaderService loaderService = new LoaderService();
        Truck truck = new Truck(100.0, 1000.0);
        ConfigService configService = new ConfigService();
        CoffeeStorage coffeeStorage = new CoffeeStorage();

        CommandFactory commandFactory = new CommandFactory(loaderService, truck, configService, coffeeStorage);
        Menu menu = new Menu(commandFactory);
        menu.run();
    }
}
