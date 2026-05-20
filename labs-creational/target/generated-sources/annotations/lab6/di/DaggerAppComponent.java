package lab6.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Provider;
import javax.annotation.processing.Generated;
import lab6.controller.AppController;
import lab6.controller.AppController_Factory;
import lab6.repository.ArticleRepository;
import lab6.repository.ArticleRepository_Factory;
import lab6.repository.CommentRepository;
import lab6.repository.CommentRepository_Factory;
import lab6.repository.UserRepository;
import lab6.repository.UserRepository_Factory;
import lab6.service.BlogService;
import lab6.service.BlogService_Factory;
import lab6.service.NotificationService;
import lab6.service.NotificationService_Factory;
import lab6.service.ReportService;
import lab6.service.ReportService_Factory;
import lab6.util.DateFormatter;
import lab6.util.DateFormatter_Factory;
import lab6.util.Logger;
import lab6.util.Logger_Factory;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class DaggerAppComponent {
  private DaggerAppComponent() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static AppComponent create() {
    return new Builder().build();
  }

  public static final class Builder {
    private Builder() {
    }

    public AppComponent build() {
      return new AppComponentImpl();
    }
  }

  private static final class AppComponentImpl implements AppComponent {
    private final AppComponentImpl appComponentImpl = this;

    private Provider<ArticleRepository> articleRepositoryProvider;

    private Provider<UserRepository> userRepositoryProvider;

    private Provider<BlogService> blogServiceProvider;

    private Provider<CommentRepository> commentRepositoryProvider;

    private Provider<DateFormatter> dateFormatterProvider;

    private Provider<ReportService> reportServiceProvider;

    private Provider<Logger> loggerProvider;

    private Provider<NotificationService> notificationServiceProvider;

    private Provider<AppController> appControllerProvider;

    private AppComponentImpl() {

      initialize();

    }

    @SuppressWarnings("unchecked")
    private void initialize() {
      this.articleRepositoryProvider = DoubleCheck.provider(ArticleRepository_Factory.create());
      this.userRepositoryProvider = DoubleCheck.provider(UserRepository_Factory.create());
      this.blogServiceProvider = DoubleCheck.provider(BlogService_Factory.create(articleRepositoryProvider, userRepositoryProvider));
      this.commentRepositoryProvider = DoubleCheck.provider(CommentRepository_Factory.create());
      this.dateFormatterProvider = DoubleCheck.provider(DateFormatter_Factory.create());
      this.reportServiceProvider = DoubleCheck.provider(ReportService_Factory.create(articleRepositoryProvider, commentRepositoryProvider, dateFormatterProvider));
      this.loggerProvider = DoubleCheck.provider(Logger_Factory.create());
      this.notificationServiceProvider = DoubleCheck.provider(NotificationService_Factory.create(loggerProvider));
      this.appControllerProvider = DoubleCheck.provider(AppController_Factory.create(blogServiceProvider, reportServiceProvider, notificationServiceProvider));
    }

    @Override
    public AppController getAppController() {
      return appControllerProvider.get();
    }
  }
}
