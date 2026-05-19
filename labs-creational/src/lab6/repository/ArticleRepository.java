package lab6.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ArticleRepository {

    @Inject
    public ArticleRepository() {
    }

    public String getArticleTitleById(int id) {
        return "Вступ до Dependency Injection";
    }

    public int getArticlesCount() {
        return 15;
    }
}