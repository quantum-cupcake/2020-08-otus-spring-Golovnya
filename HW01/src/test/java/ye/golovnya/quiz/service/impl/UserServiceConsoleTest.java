package ye.golovnya.quiz.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ye.golovnya.quiz.entity.user.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceConsoleTest {

    @InjectMocks
    private UserServiceConsole userServiceConsole;

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

        ReflectionTestUtils.setField(userServiceConsole, "scanner", new Scanner(System.in));
    }

    @Test
    void whenUserNamesInputValidThenCreatesNewUser() {
        String firstName = "John";
        String lastName = "Doe";

        ReflectionTestUtils.setField(userServiceConsole, "scanner", new Scanner(lastName + System.lineSeparator() + firstName));

        User user = userServiceConsole.identifyCurrentUser();

        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(0, user.getScoreCard().getCorrectResponsesCount());
        assertEquals(0, user.getScoreCard().getQuestionsAskedCount());
    }

    @Test
    void whenUserNameInputEmptyThenRepromptsCreatesNewUser() {
        String firstName = "John";
        String lastName = "Doe";
        String empty = "";

        ReflectionTestUtils.setField(userServiceConsole, "scanner",
                new Scanner(empty + System.lineSeparator() + lastName + System.lineSeparator() + empty + System.lineSeparator() + firstName));

        User user = userServiceConsole.identifyCurrentUser();

        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(0, user.getScoreCard().getCorrectResponsesCount());
        assertEquals(0, user.getScoreCard().getQuestionsAskedCount());
    }

    @Test
    void printScores() {
        User user = new User("John", "Doe");
        user.getScoreCard().addCorrectResponse(); // 1 из 1
        user.getScoreCard().addCorrectResponse(); // 2 из 2
        user.getScoreCard().addIncorrectResponse(); // 2 из 3
        String expected = String.format("Quiz results: %nName: %s%nScore: %d out of %d.",
                user.getFullName(), user.getScoreCard().getCorrectResponsesCount(), user.getScoreCard().getQuestionsAskedCount());

        userServiceConsole.printScores(user);

        assertEquals(expected, mockedOutStream.toString());
    }

}