package behavioral.iterator;

import java.util.Iterator;
import java.util.Arrays;

public class MyIterator {
    public static void demo() {
        Iterator<Integer> it = Arrays.asList(1,2,3).iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}