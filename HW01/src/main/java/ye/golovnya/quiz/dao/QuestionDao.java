package ye.golovnya.quiz.dao;

import ye.golovnya.quiz.entity.question.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionDao<T extends Question> {

    Optional<T> findById(int id);

    List<T> findAll();

    int getQuestionCount();
}
