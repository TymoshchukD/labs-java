import creational.singleton.Singleton;
import creational.factory_method.*;
import creational.abstract_factory.*;
import creational.builder.*;
import creational.prototype.*;
import creational.object_pool.*;

public class CreationalMain {
    public static void main(String[] args) {

        // Singleton
        Singleton.getInstance().showMessage();

        // Factory Method
        Creator creator = new CreatorA();
        Product product = creator.createProduct();
        product.use();

        // Abstract Factory
        GUIFactory factory = new WindowsFactory();
        Button button = factory.createButton();
        button.paint();

        // Builder
        House house = new HouseBuilder()
                .buildWalls()
                .buildRoof()
                .getResult();
        house.show();

        // Prototype
        ConcretePrototype original = new ConcretePrototype("data");
        ConcretePrototype copy = (ConcretePrototype) original.clone();
        copy.show();

        // Object Pool
        ObjectPool pool = new ObjectPool();
        ReusableObject obj = pool.acquire();
        obj.use();
        pool.release(obj);
    }
}