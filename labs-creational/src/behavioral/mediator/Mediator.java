package behavioral.mediator;

class Mediator {
    void send(String msg, User user) {
        System.out.println(user.name + ": " + msg);
    }
}

class User {
    Mediator mediator;
    String name;

    User(Mediator m, String name) {
        this.mediator = m;
        this.name = name;
    }

    void send(String msg) {
        mediator.send(msg, this);
    }
}