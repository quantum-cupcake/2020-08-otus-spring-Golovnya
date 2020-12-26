package ye.golovnya.quiz.service;

import ye.golovnya.quiz.entity.user.User;

public interface QuestioningService {

    void promptQuestion(int questionId, User user);

    void promptAllQuestions(User user);
}
