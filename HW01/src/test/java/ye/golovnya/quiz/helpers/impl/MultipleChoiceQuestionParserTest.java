package ye.golovnya.quiz.helpers.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ye.golovnya.quiz.entity.question.MultipleChoiceQuestion;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class MultipleChoiceQuestionParserTest {

    @Spy
    private final Resource csvSource = new ClassPathResource("valid-quiz-source.csv");
    @InjectMocks
    private MultipleChoiceQuestionParser multipleChoiceQuestionParser;

    @Test
    void whenValidSourceThenReturnsParsedQuestions() {
        var expected = List.of(new MultipleChoiceQuestion("Кто проживает на дне океана?", 2, "Донателло", "Гарри Поттер", "Губка Боб", "мне надоело придумывать варианты ответа"));
        var actual = multipleChoiceQuestionParser.parseQuestions();
        Assertions.assertIterableEquals(expected, actual);
    }

}