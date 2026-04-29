package behavioral.chain;

class Handler {
    Handler next;

    void setNext(Handler next) {
        this.next = next;
    }

    void handle() {
        System.out.println("Handling...");
        if (next != null) next.handle();
    }
}