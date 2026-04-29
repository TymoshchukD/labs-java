package behavioral.state;

interface State {
    void handle();
}

class ConcreteState implements State {
    public void handle() {
        System.out.println("State working");
    }
}