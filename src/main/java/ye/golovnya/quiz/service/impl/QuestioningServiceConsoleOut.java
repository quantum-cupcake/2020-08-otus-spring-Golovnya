package ye.golovnya.quiz.service.impl;

import ye.golovnya.quiz.dao.QuestionDao;
import ye.golovnya.quiz.entity.question.Question;
import ye.golovnya.quiz.exception.NoQuestionsLeftException;
import ye.golovnya.quiz.service.QuestioningService;

public class QuestioningServiceConsoleOut implements QuestioningService {

    private static final String OUT_OF_QUESTIONS = "Thanks for playing! That's all, folks!";

    private final QuestionDao questionDao;
    private int currentQuestionIdx;

    public QuestioningServiceConsoleOut(QuestionDao questionDao) {
        this.questionDao = questionDao;
        this.currentQuestionIdx = 0;
    }

    @Override
    public void promptNextQuestion() {
        if (currentQuestionIdx < 0) {
            throw new NoQuestionsLeftException();
        }
        var question = questionDao.findById(currentQuestionIdx);
        currentQuestionIdx++;
        System.out.println(question.map(Question::getQuestionString).orElseGet(() -> {
            currentQuestionIdx = -1;
            return OUT_OF_QUESTIONS;
        }));
    }

    @Override
    public void promptAllQuestions() {
        while (this.currentQuestionIdx >= 0) {
            promptNextQuestion();
        }
        currentQuestionIdx = 0;
    }
}
