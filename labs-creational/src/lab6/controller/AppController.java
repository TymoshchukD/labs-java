package lab6.controller;

import lab6.service.BlogService;
import lab6.service.NotificationService;
import lab6.service.ReportService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppController {

    private final BlogService blogService;
    private final ReportService reportService;
    private final NotificationService notificationService;

    @Inject
    public AppController(
            BlogService blogService,
            ReportService reportService,
            NotificationService notificationService
    ) {
        this.blogService = blogService;
        this.reportService = reportService;
        this.notificationService = notificationService;
    }

    public void startApplication() {
        System.out.println(" Лабораторна робота №6 ");
        System.out.println("Тема: Впровадження ін'єкції залежності за допомогою Dagger");
        System.out.println();

        blogService.createArticle();
        reportService.generateReport();
        notificationService.sendNotification();

        System.out.println("Роботу програми завершено");
    }
}