package behavioral.visitor;

interface Visitor {
    void visit();
}

class ConcreteVisitor implements Visitor {
    public void visit() {
        System.out.println("Visiting...");
    }
}