package lab6.service;

import lab6.repository.ArticleRepository;
import lab6.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BlogService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Inject
    public BlogService(
            ArticleRepository articleRepository,
            UserRepository userRepository
    ) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public void createArticle() {
        String author = userRepository.getUserNameById(1);
        String articleTitle = articleRepository.getArticleTitleById(10);

        System.out.println("BlogService:");
        System.out.println("Автор створив статтю.");
        System.out.println("Автор: " + author);
        System.out.println("Стаття: " + articleTitle);
        System.out.println();
    }
}