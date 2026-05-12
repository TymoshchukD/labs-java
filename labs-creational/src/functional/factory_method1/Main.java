package functional.factory_method1;

public class Main {

    public static void main(String[] args) {

        Product productA = FunctionalFactory.createProduct("A");
        Product productB = FunctionalFactory.createProduct("B");

        productA.use();
        productB.use();
    }
}