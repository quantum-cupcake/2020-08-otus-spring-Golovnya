package ye.golovnya.quiz.shell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ye.golovnya.quiz.entity.user.User;
import ye.golovnya.quiz.service.QuestioningService;
import ye.golovnya.quiz.service.UserService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShellCommandsTest {

    @Mock
    private UserService userService;

    @Mock
    private QuestioningService questioningService;

    @InjectMocks
    private ShellCommands shellCommands;

    @Test
    void shouldReturnExpectedGreetingAfterLoginCommandEvaluated() {
        when(userService.identifyCurrentUser()).thenReturn(null);
        shellCommands.identifyUser();
        verify(userService, times(1)).identifyCurrentUser();
    }

    @Test
    void shouldReturnExpectedMessageAndFirePublishMethodAfterPublishCommandEvaluated() {
        var user = new User("John", "Doe");
        ReflectionTestUtils.setField(shellCommands, "currentUser", user);

        shellCommands.quiz();
        verify(questioningService, times(1)).promptAllQuestions(ArgumentMatchers.eq(user));
        verify(userService, times(1)).printScores(ArgumentMatchers.eq(user));
    }
}

