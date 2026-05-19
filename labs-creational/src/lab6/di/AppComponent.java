package lab6.di;

import dagger.Component;
import lab6.controller.AppController;

import javax.inject.Singleton;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    AppController getAppController();
}