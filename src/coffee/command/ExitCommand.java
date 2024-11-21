package coffee.command;

public class ExitCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Завершення програми.");
        System.exit(0);
    }
}