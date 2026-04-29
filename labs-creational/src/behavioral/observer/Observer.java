package behavioral.observer;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update();
}

class ConcreteObserver implements Observer {
    public void update() {
        System.out.println("Observer notified");
    }
}

class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void add(Observer o) {
        observers.add(o);
    }

    public void notifyAllObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}