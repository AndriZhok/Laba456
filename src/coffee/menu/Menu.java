package coffee.menu;

import coffee.command.CommandFactory;

import java.util.Scanner;

public class Menu {
    private CommandFactory commandFactory;
    private Scanner scanner;

    public Menu(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            displayMenu();
            int action = getValidIntInput("Оберіть дію: ");
            commandFactory.getCommand(action).execute();
        }
    }

    private void displayMenu() {
        System.out.println("""
                
                --- Меню ---
                1. Відсортувати продукти за співвідношенням ціна/вага
                2. Знайти продукт за параметрами якості
                3. Переглянути всі продукти у фургоні
                4. Видалити продукт з фургону
                5. Завантажити параметри фургона з файлу
                6. Зберегти параметри фургона у файл
                7. Додати продукт на склад
                8. Переглянути всі продукти на складі
                9. Додати продукт зі складу у фургон
                10. Змінити обʼєм та бюджет фургона
                11. Завершити програму
                """);
    }

    private int getValidIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Невірний формат. Будь ласка, введіть ціле число.");
                scanner.next();
            }
        }
    }
}