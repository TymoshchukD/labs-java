package lab6.util;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Logger {

    @Inject
    public Logger() {
    }

    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}