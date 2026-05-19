package lab6;

import lab6.controller.AppController;
import lab6.di.DaggerAppComponent;

public class Main {

    public static void main(String[] args) {
        AppController appController = DaggerAppComponent.create().getAppController();

        appController.startApplication();
    }
}