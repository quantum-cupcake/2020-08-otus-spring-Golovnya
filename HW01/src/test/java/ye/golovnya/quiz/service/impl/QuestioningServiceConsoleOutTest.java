package ye.golovnya.quiz.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ye.golovnya.quiz.dao.QuestionDao;
import ye.golovnya.quiz.entity.question.MultipleChoiceQuestion;
import ye.golovnya.quiz.entity.question.Question;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class QuestioningServiceConsoleOutTest {

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestioningServiceConsoleOut questioningServiceConsoleOut;

    private OutputStream mockedOutStream;
    private final PrintStream originalOutStream = System.out;

    @BeforeEach
    void mockSystemOut() {
        mockedOutStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mockedOutStream));
    }

    @AfterEach
    void restoreSystemOut() throws IOException {
        if (mockedOutStream != null) {
            mockedOutStream.close();
        }
        System.out.close();
        System.setOut(originalOutStream);
    }

    @Test
    void promptNextQuestion() {
        Optional<Question> question = Optional.of(
                new MultipleChoiceQuestion("Question?", 0, "A", "B"));
        Mockito.when(questionDao.findById(questioningServiceConsoleOut.getCurrentQuestionIdx()))
                .thenReturn(question);
        questioningServiceConsoleOut.promptNextQuestion();

        String expected = question.get().getQuestionString().trim();
        String actual = mockedOutStream.toString().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void promptAllQuestions() {
        Optional<Question> question0 = Optional.of(
                new MultipleChoiceQuestion("Question?", 0, "A", "B"));
        Mockito.when(questionDao.findById(questioningServiceConsoleOut.getCurrentQuestionIdx()))
                .thenReturn(question0);
        Optional<Question> question1 = Optional.of(
                new MultipleChoiceQuestion("Question1?", 0, "D", "C"));
        Mockito.when(questionDao.findById(questioningServiceConsoleOut.getCurrentQuestionIdx() + 1))
                .thenReturn(question1);

        questioningServiceConsoleOut.promptAllQuestions();

        String expected = String.join(System.lineSeparator(), question0.get().getQuestionString(), question1.get().getQuestionString(), "Thanks for playing! That's all, folks!").trim();
        String actual = mockedOutStream.toString().trim();
        Assertions.assertEquals(expected, actual);
    }

}