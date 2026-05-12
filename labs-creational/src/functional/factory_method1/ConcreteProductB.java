package functional.factory_method1;

public class ConcreteProductB implements Product {

    @Override
    public void use() {
        System.out.println("Using Product B");
    }
}