package ye.golovnya.quiz.shell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.util.ReflectionTestUtils;
import ye.golovnya.quiz.entity.user.User;
import ye.golovnya.quiz.service.QuestioningService;
import ye.golovnya.quiz.service.UserService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShellCommandsSpringBootTest {

    @MockBean
    private UserService userService;

    @MockBean
    private QuestioningService questioningService;

    @Autowired
    private Shell shell;

    private static final String COMMAND_RANDOM = "random";
    private static final String COMMAND_LOGIN = "login";
    private static final String COMMAND_LOGIN_SHORT = "l";
    private static final String COMMAND_LOGIN_ALT = "identify";

    private static final String COMMAND_QUIZ = "quiz";
    private static final String COMMAND_QUIZ_SHORT = "q";
    private static final String COMMAND_QUIZ_ALT = "start";

    @Test
    void shouldReturnExpectedGreetingAfterLoginCommandEvaluated() {
        shell.evaluate(() -> COMMAND_RANDOM);
        shell.evaluate(() -> COMMAND_LOGIN);
        shell.evaluate(() -> COMMAND_LOGIN_SHORT);
        shell.evaluate(() -> COMMAND_LOGIN_ALT);
        verify(userService, times(3)).identifyCurrentUser();
    }

    @Test
    void whenUserIdentifiedThenQuizStarts() {
        var user = new User("John", "Doe");

        when(userService.identifyCurrentUser())
                .thenReturn(user);

        shell.evaluate(() -> COMMAND_LOGIN);

        shell.evaluate(() -> COMMAND_QUIZ);
        shell.evaluate(() -> COMMAND_QUIZ_SHORT);
        shell.evaluate(() -> COMMAND_QUIZ_ALT);

        verify(questioningService, times(3)).promptAllQuestions(ArgumentMatchers.eq(user));
        verify(userService, times(3)).printScores(ArgumentMatchers.eq(user));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void whenUserUnidentifiedThenQuizWontStart() {
        shell.evaluate(() -> COMMAND_QUIZ);
        shell.evaluate(() -> COMMAND_QUIZ_SHORT);
        shell.evaluate(() -> COMMAND_QUIZ_ALT);

        verify(questioningService, times(0)).promptAllQuestions(ArgumentMatchers.any());
        verify(userService, times(0)).printScores(ArgumentMatchers.any());
    }
}

