package behavioral.command;

interface Command {
    void execute();
}

class Light {
    void on() {
        System.out.println("Light ON");
    }
}

class LightOnCommand implements Command {
    Light light = new Light();

    public void execute() {
        light.on();
    }
}