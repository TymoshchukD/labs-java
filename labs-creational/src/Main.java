public class Main {

    // ===== STRATEGY =====
    interface Strategy {
        void execute();
    }

    static class ConcreteStrategyA implements Strategy {
        public void execute() {
            System.out.println("Strategy A");
        }
    }

    static class Context {
        Strategy strategy;
        Context(Strategy s) { strategy = s; }
        void run() { strategy.execute(); }
    }

    // ===== OBSERVER =====
    interface Observer {
        void update();
    }

    static class ConcreteObserver implements Observer {
        public void update() {
            System.out.println("Observer notified");
        }
    }

    static class Subject {
        java.util.List<Observer> list = new java.util.ArrayList<>();
        void add(Observer o) { list.add(o); }
        void notifyAllObservers() {
            for (Observer o : list) o.update();
        }
    }

    // ===== COMMAND =====
    interface Command {
        void execute();
    }

    static class Light {
        void on() { System.out.println("Light ON"); }
    }

    static class LightOnCommand implements Command {
        Light light = new Light();
        public void execute() { light.on(); }
    }

    // ===== ITERATOR =====
    static class MyIterator {
        static void demo() {
            java.util.Iterator<Integer> it =
                    java.util.Arrays.asList(1,2,3).iterator();
            while(it.hasNext()) {
                System.out.println(it.next());
            }
        }
    }

    // ===== STATE =====
    interface State {
        void handle();
    }

    static class ConcreteState implements State {
        public void handle() {
            System.out.println("State working");
        }
    }

    // ===== TEMPLATE =====
    static abstract class Template {
        abstract void step1();
        void templateMethod() {
            step1();
            System.out.println("Common step");
        }
    }

    static class ConcreteTemplate extends Template {
        void step1() {
            System.out.println("Step 1");
        }
    }

    // ===== MEDIATOR =====
    static class Mediator {
        void send(String msg) {
            System.out.println("Message: " + msg);
        }
    }

    // ===== MEMENTO =====
    static class Memento {
        String state;
        Memento(String s) { state = s; }
    }

    // ===== VISITOR =====
    interface Visitor {
        void visit();
    }

    static class ConcreteVisitor implements Visitor {
        public void visit() {
            System.out.println("Visiting...");
        }
    }

    // ===== CHAIN =====
    static class Handler {
        Handler next;
        void setNext(Handler n) { next = n; }
        void handle() {
            System.out.println("Handling...");
            if (next != null) next.handle();
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        System.out.println("=== PATTERNS ===");

        new Context(new ConcreteStrategyA()).run();

        Subject s = new Subject();
        s.add(new ConcreteObserver());
        s.notifyAllObservers();

        new LightOnCommand().execute();

        MyIterator.demo();

        new ConcreteState().handle();

        new ConcreteTemplate().templateMethod();

        new Mediator().send("Hello");

        Memento m = new Memento("state");

        new ConcreteVisitor().visit();

        Handler h1 = new Handler();
        Handler h2 = new Handler();
        h1.setNext(h2);
        h1.handle();

        System.out.println("=== DONE ===");
    }
}