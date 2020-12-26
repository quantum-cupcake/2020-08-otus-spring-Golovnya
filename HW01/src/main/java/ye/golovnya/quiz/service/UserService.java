package ye.golovnya.quiz.service;

import ye.golovnya.quiz.entity.user.User;

public interface UserService {

    User identifyCurrentUser();

    void printScores(User user);
}
