package behavioral.template;

abstract class Template {
    abstract void step1();

    void templateMethod() {
        step1();
        System.out.println("Common step");
    }
}

class ConcreteTemplate extends Template {
    void step1() {
        System.out.println("Step 1");
    }
}