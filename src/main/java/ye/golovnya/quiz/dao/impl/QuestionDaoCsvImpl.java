package ye.golovnya.quiz.dao.impl;

import org.springframework.core.io.Resource;
import ye.golovnya.quiz.dao.QuestionDao;
import ye.golovnya.quiz.entity.question.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

public class QuestionDaoCsvImpl implements QuestionDao {

    private final BufferedReader bufferedReader;

    public QuestionDaoCsvImpl(Resource csvSource) {
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(
                    csvSource.getInputStream()));
        } catch (IOException e) {
            throw new IllegalStateException("IOException при попытке обратиться к файлу с вопросами", e);
        }
    }

    @Override
    public Optional<Question> findById() {
        return Optional.empty();
    }

    @Override
    public List<Question> getAllQuestions() {
        return null;
    }

    private String parseQuestion(int i) {
        return bufferedReader.lines().skip(i).findFirst().orElseThrow(() -> );
    }

}
