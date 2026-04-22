package structural.flyweight;

public class Tree {
    private String type;

    public Tree(String type) {
        this.type = type;
    }

    public void draw() {
        System.out.println("Tree: " + type);
    }
}