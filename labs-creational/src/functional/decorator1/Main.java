package functional.decorator1;

import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        // Базова кава
        Function<String, String> simpleCoffee =
                coffee -> coffee;

        // Декоратор молока
        Function<String, String> milkDecorator =
                coffee -> coffee + " + Milk";

        // Декоратор цукру
        Function<String, String> sugarDecorator =
                coffee -> coffee + " + Sugar";

        // Комбінування декораторів
        Function<String, String> decoratedCoffee =
                simpleCoffee
                        .andThen(milkDecorator)
                        .andThen(sugarDecorator);

        String result = decoratedCoffee.apply("Coffee");

        System.out.println(result);
    }
}