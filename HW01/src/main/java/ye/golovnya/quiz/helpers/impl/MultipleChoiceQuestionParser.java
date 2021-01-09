package ye.golovnya.quiz.helpers.impl;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.core.io.Resource;
import ye.golovnya.quiz.entity.question.MultipleChoiceQuestion;
import ye.golovnya.quiz.helpers.CsvQuestionParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MultipleChoiceQuestionParser implements CsvQuestionParser<MultipleChoiceQuestion> {

    private final Resource csvSource;

    public MultipleChoiceQuestionParser(Resource csvSource) {
        this.csvSource = csvSource;
    }

    @Override
    public List<MultipleChoiceQuestion> parseQuestions() {
        CsvSchema csvSchema = CsvSchema.builder()
                .addColumn("questionString", CsvSchema.ColumnType.STRING)
                .addColumn("correctOption", CsvSchema.ColumnType.NUMBER)
                .addArrayColumn("options")
                .setSkipFirstDataRow(true)
                .setArrayElementSeparator(";")
                .build();
        ObjectReader csvReader = new CsvMapper().readerFor(MultipleChoiceQuestion.class).with(csvSchema);
        try {
            List<MultipleChoiceQuestion> questions = new ArrayList<>();
            csvReader.<MultipleChoiceQuestion>readValues(csvSource.getInputStream()).readAll(questions);
            return questions;
        } catch (IOException ioe) {
            log.error("Failed to access question source!", ioe);
            throw new BeanCreationException("Failed to access question source!", ioe);
        }
    }
}
