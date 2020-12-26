package ye.golovnya.quiz.dao;

import ye.golovnya.quiz.entity.question.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionDao {

    Optional<Question> findById(int id);

    List<Question> findAll();

    int getQuestionCount();
}
