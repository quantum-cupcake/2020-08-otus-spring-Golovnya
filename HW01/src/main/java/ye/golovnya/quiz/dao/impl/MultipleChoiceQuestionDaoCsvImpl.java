package ye.golovnya.quiz.dao.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ye.golovnya.quiz.dao.QuestionDao;
import ye.golovnya.quiz.entity.question.MultipleChoiceQuestion;
import ye.golovnya.quiz.helpers.CsvQuestionParser;

import java.util.List;
import java.util.Optional;

@Log4j2
@Repository
public class MultipleChoiceQuestionDaoCsvImpl implements QuestionDao<MultipleChoiceQuestion> {

    private final List<MultipleChoiceQuestion> questions;

    public MultipleChoiceQuestionDaoCsvImpl(@Qualifier("questionParser") CsvQuestionParser<MultipleChoiceQuestion> questionParser) {
        this.questions = questionParser.parseQuestions();
    }

    @Override
    public Optional<MultipleChoiceQuestion> findById(int id) {
        return questions.size() > id ? Optional.of(questions.get(id)) : Optional.empty();
    }

    @Override
    public List<MultipleChoiceQuestion> findAll() {
        return questions;
    }

    @Override
    public int getQuestionCount() {
        return questions.size();
    }
}
