import structural.adapter.*;
import structural.bridge.*;
import structural.composite.*;
import structural.decorator.*;
import structural.facade.*;
import structural.flyweight.*;
import structural.proxy.*;

public class structuralMain {
    public static void main(String[] args) {

        // Adapter
        Printer printer = new PrinterAdapter(new OldPrinter());
        printer.print();

        // Bridge
        Remote remote = new BasicRemote(new TV());
        remote.pressPower();

        // Composite
        Folder folder = new Folder();
        folder.add(new File());
        folder.add(new File());
        folder.show();

        // Decorator
        Coffee coffee = new MilkDecorator(new SimpleCoffee());
        System.out.println(coffee.getDescription());

        // Facade
        ComputerFacade computer = new ComputerFacade();
        computer.start();

        // Flyweight
        Tree t1 = TreeFactory.getTree("Oak");
        Tree t2 = TreeFactory.getTree("Oak");
        t1.draw();

        // Proxy
        Image image = new ProxyImage("photo.jpg");
        image.display();
    }
}