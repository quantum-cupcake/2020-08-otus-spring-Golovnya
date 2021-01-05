package ye.golovnya.quiz.service.impl;

import lombok.Getter;
import ye.golovnya.quiz.dao.QuestionDao;
import ye.golovnya.quiz.entity.question.Question;
import ye.golovnya.quiz.exception.NoQuestionsLeftException;
import ye.golovnya.quiz.service.QuestioningService;

public class QuestioningServiceConsoleOut implements QuestioningService {

    private static final String OUT_OF_QUESTIONS = "Thanks for playing! That's all, folks!";
    private static final int FIRST_QUESTION_IDX = 0;

    private final QuestionDao questionDao;
    @Getter
    private int currentQuestionIdx;

    public QuestioningServiceConsoleOut(QuestionDao questionDao) {
        this.questionDao = questionDao;
        this.currentQuestionIdx = FIRST_QUESTION_IDX;
    }

    @Override
    public void promptNextQuestion() {
        if (currentQuestionIdx < FIRST_QUESTION_IDX) {
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
        while (this.currentQuestionIdx >= FIRST_QUESTION_IDX) {
            promptNextQuestion();
        }
        currentQuestionIdx = FIRST_QUESTION_IDX;
    }
}
