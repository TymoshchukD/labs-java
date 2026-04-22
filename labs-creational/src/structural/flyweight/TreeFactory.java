package structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class TreeFactory {
    private static Map<String, Tree> trees = new HashMap<>();

    public static Tree getTree(String type) {
        trees.putIfAbsent(type, new Tree(type));
        return trees.get(type);
    }
}