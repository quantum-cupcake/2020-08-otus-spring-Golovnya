package ye.golovnya.quiz.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ye.golovnya.quiz.dao.QuestionDao;
import ye.golovnya.quiz.entity.question.MultipleChoiceQuestion;
import ye.golovnya.quiz.entity.user.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestioningServiceConsoleTest {

    private static final int QUESTION_COUNT = 5;

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestioningServiceConsole questioningServiceConsole;

    private static OutputStream mockedOutStream;
    private final static PrintStream originalOutStream = System.out;

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

        ReflectionTestUtils.setField(questioningServiceConsole, "scanner", new Scanner(System.in));
    }

    @Test
    void whenCorrectResponseThenPrompQuestionUpdatesUsersScore() {
        int questionId = 0;
        User user = new User("Name", "Surname");
        String[] options = {"A", "B"};
        int correctOption = 0;
        var question = new MultipleChoiceQuestion("Question?", correctOption, options);

        when(questionDao.findById(questionId)).thenReturn(Optional.of(question));
        ReflectionTestUtils.setField(questioningServiceConsole, "scanner", new Scanner(options[correctOption]));

        questioningServiceConsole.promptQuestion(questionId, user);

        String expected = question.getQuestionString().trim();
        String actual = mockedOutStream.toString().trim();
        Assertions.assertEquals(expected, actual);

        Assertions.assertEquals(1, user.getScoreCard().getQuestionsAskedCount());
        Assertions.assertEquals(1, user.getScoreCard().getCorrectResponsesCount());
    }

    @Test
    void whenIncorrectResponseThenPrompQuestionUpdatesUsersScore() {
        int questionId = 0;
        User user = new User("Name", "Surname");
        String[] options = {"A", "B"};
        int correctOption = 0;
        int incorrectOption = 1;
        var question = new MultipleChoiceQuestion("Question?", correctOption, options);

        when(questionDao.findById(questionId)).thenReturn(Optional.of(question));
        ReflectionTestUtils.setField(questioningServiceConsole, "scanner", new Scanner(options[incorrectOption]));

        questioningServiceConsole.promptQuestion(questionId, user);

        String expected = question.getQuestionString().trim();
        String actual = mockedOutStream.toString().trim();
        Assertions.assertEquals(expected, actual);

        Assertions.assertEquals(1, user.getScoreCard().getQuestionsAskedCount());
        Assertions.assertEquals(0, user.getScoreCard().getCorrectResponsesCount());
    }

    @Test
    void promptAllQuestions() {
        User user = new User("Name", "Surname");
        String[] options = {"A", "B"};
        int correctOption = 0;
        int incorrectOption = 1;

        var question0 = new MultipleChoiceQuestion("Question?", correctOption, options);
        var question1 = new MultipleChoiceQuestion("Question1?", correctOption, options);

        when(questionDao.getQuestionCount()).thenReturn(2);
        when(questionDao.findById(0)).thenReturn(Optional.of(question0));
        when(questionDao.findById(1)).thenReturn(Optional.of(question1));
        ReflectionTestUtils.setField(questioningServiceConsole,
                "scanner",
                new Scanner(String.join(System.lineSeparator(), options[correctOption], options[incorrectOption])));

        questioningServiceConsole.promptAllQuestions(user);

        String expected = String.join(System.lineSeparator(), question0.getQuestionString(), question1.getQuestionString()).trim();
        String actual = mockedOutStream.toString().trim();
        Assertions.assertEquals(expected, actual);

        Assertions.assertEquals(2, user.getScoreCard().getQuestionsAskedCount());
        Assertions.assertEquals(1, user.getScoreCard().getCorrectResponsesCount());
    }

}