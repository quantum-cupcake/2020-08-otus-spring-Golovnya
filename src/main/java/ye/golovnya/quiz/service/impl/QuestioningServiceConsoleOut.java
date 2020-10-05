package ye.golovnya.quiz.service.impl;

import ye.golovnya.quiz.service.QuestioningService;

public class QuestioningServiceConsoleOut implements QuestioningService {

    @Override
    public void nextQuestion() {

    }

    @Override
    public boolean hasMoreQuestions() {
        return false;
    }
}
