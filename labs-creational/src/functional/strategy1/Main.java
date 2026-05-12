package functional.strategy1;

public class Main {

    public static void main(String[] args) {

        // Стратегія A
        Runnable strategyA =
                () -> System.out.println("Strategy A");

        // Стратегія B
        Runnable strategyB =
                () -> System.out.println("Strategy B");

        // Контекст
        executeStrategy(strategyA);
        executeStrategy(strategyB);
    }

    public static void executeStrategy(Runnable strategy) {
        strategy.run();
    }
}