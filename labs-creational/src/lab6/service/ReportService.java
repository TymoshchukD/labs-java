package lab6.service;

import lab6.repository.ArticleRepository;
import lab6.repository.CommentRepository;
import lab6.util.DateFormatter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ReportService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final DateFormatter dateFormatter;

    @Inject
    public ReportService(
            ArticleRepository articleRepository,
            CommentRepository commentRepository,
            DateFormatter dateFormatter
    ) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.dateFormatter = dateFormatter;
    }

    public void generateReport() {
        int articlesCount = articleRepository.getArticlesCount();
        int commentsCount = commentRepository.getCommentsCount();
        String currentDate = dateFormatter.getCurrentDate();

        System.out.println("ReportService:");
        System.out.println("Звіт по блогу згенеровано.");
        System.out.println("Кількість статей: " + articlesCount);
        System.out.println("Кількість коментарів: " + commentsCount);
        System.out.println("Дата звіту: " + currentDate);
        System.out.println();
    }
}