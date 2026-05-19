package lab6.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CommentRepository {

    @Inject
    public CommentRepository() {
    }

    public int getCommentsCount() {
        return 42;
    }
}