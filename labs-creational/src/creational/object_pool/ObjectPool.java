package creational.object_pool;

import java.util.ArrayList;
import java.util.List;

public class ObjectPool {
    private List<ReusableObject> available = new ArrayList<>();

    public ObjectPool() {
        for (int i = 0; i < 3; i++) {
            available.add(new ReusableObject());
        }
    }

    public ReusableObject acquire() {
        if (available.isEmpty()) {
            return new ReusableObject();
        }
        return available.remove(0);
    }

    public void release(ReusableObject obj) {
        available.add(obj);
    }
}