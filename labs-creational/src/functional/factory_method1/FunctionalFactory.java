package functional.factory_method1;

import java.util.Map;
import java.util.function.Supplier;

public class FunctionalFactory {

    // Функціональна фабрика
    private static final Map<String, Supplier<Product>> factoryMap =
            Map.of(
                    "A", ConcreteProductA::new,
                    "B", ConcreteProductB::new
            );

    public static Product createProduct(String type) {

        Supplier<Product> supplier = factoryMap.get(type);

        if (supplier == null) {
            throw new IllegalArgumentException("Unknown product type");
        }

        return supplier.get();
    }
}