package functional.strategy1;

import java.util.function.BinaryOperator;

public class Main {

    public static void main(String[] args) {

        BinaryOperator<Integer> addition =
                (a, b) -> a + b;

        BinaryOperator<Integer> multiplication =
                (a, b) -> a * b;

        executeStrategy(10, 5, addition);
        executeStrategy(10, 5, multiplication);
    }

    public static void executeStrategy(
            int a,
            int b,
            BinaryOperator<Integer> strategy
    ) {
        int result = strategy.apply(a, b);
        System.out.println("Result: " + result);
    }
}