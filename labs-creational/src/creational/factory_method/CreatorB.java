package creational.factory_method;

public class CreatorB extends Creator {
    public Product createProduct() {
        return new ConcreteProductB();
    }
}