package creational.factory_method;

public class CreatorA extends Creator {
    public Product createProduct() {
        return new ConcreteProductA();
    }
}