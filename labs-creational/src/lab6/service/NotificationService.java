package lab6.service;

import lab6.util.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NotificationService {

    private final Logger logger;

    @Inject
    public NotificationService(Logger logger) {
        this.logger = logger;
    }

    public void sendNotification() {
        System.out.println("NotificationService:");
        System.out.println("Користувачу надіслано повідомлення.");
        logger.log("Повідомлення успішно надіслано.");
        System.out.println();
    }
}