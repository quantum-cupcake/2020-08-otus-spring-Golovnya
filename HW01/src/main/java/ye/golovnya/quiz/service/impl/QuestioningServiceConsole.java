package ye.golovnya.quiz.service.impl;

import org.springframework.stereotype.Service;
import ye.golovnya.quiz.dao.QuestionDao;
import ye.golovnya.quiz.entity.question.MultipleChoiceQuestion;
import ye.golovnya.quiz.entity.question.Option;
import ye.golovnya.quiz.entity.user.User;
import ye.golovnya.quiz.exception.NoneLeftException;
import ye.golovnya.quiz.service.QuestioningService;

import java.util.Scanner;

@Service(value = "questioningService")
public class QuestioningServiceConsole implements QuestioningService {

    private final QuestionDao<MultipleChoiceQuestion> questionDao;
    private final Scanner scanner;

    public QuestioningServiceConsole(QuestionDao<MultipleChoiceQuestion> questionDao) {
        this.questionDao = questionDao;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void promptQuestion(int questionId, User user) {
        var question = questionDao.findById(questionId)
                .orElseThrow(NoneLeftException::new);

        System.out.println(question.getQuestionString());
        var scoreCard = user.getScoreCard();
        if (getUserResponse().equals(question.getCorrectOption())) {
            scoreCard.addCorrectResponse();
        } else {
            scoreCard.addIncorrectResponse();
        }
    }

    @Override
    public void promptAllQuestions(User user) {
        for (int questionId = 0; questionId < questionDao.getQuestionCount(); questionId++) {
            promptQuestion(questionId, user);
        }
    }

    private Option getUserResponse() {
        return Option.fromString(scanner.nextLine().strip().toCharArray()[0]);
    }
}
