package lab6.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {

    @Inject
    public UserRepository() {
    }

    public String getUserNameById(int id) {
        return "Дмитро Тимощук";
    }
}