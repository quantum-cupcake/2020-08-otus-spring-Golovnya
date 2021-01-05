package ye.golovnya.quiz.dao.impl;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.core.io.Resource;
import ye.golovnya.quiz.dao.QuestionDao;
import ye.golovnya.quiz.entity.question.MultipleChoiceQuestion;
import ye.golovnya.quiz.entity.question.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Log4j2
public class QuestionDaoCsvImpl implements QuestionDao {

    private final List<Question> questions;

    public QuestionDaoCsvImpl(Resource csvSource) {
        CsvSchema csvSchema = CsvSchema.builder()
                .addColumn("questionString", CsvSchema.ColumnType.STRING)
                .addColumn("correctOption", CsvSchema.ColumnType.NUMBER)
                .addArrayColumn("options")
                .setSkipFirstDataRow(true)
                .setArrayElementSeparator(";")
                .build();
         ObjectReader csvReader = new CsvMapper().readerFor(MultipleChoiceQuestion.class).with(csvSchema);
        try {
            List<Question> questionsTemp = new ArrayList<>();
            csvReader.<MultipleChoiceQuestion>readValues(csvSource.getInputStream()).readAll(questionsTemp);
            questions = Collections.unmodifiableList(questionsTemp);
        } catch (IOException ioe) {
            log.error("Failed to access question source!", ioe);
            throw new BeanCreationException("Failed to access question source!", ioe);
        }
    }

    @Override
    public Optional<Question> findById(int id) {
        return questions.size() > id ? Optional.of(questions.get(id)) : Optional.empty();
    }

    @Override
    public List<Question> findAll() {
        return questions;
    }
}
