package ye.golovnya.quiz.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ye.golovnya.quiz.entity.user.User;
import ye.golovnya.quiz.service.QuestioningService;
import ye.golovnya.quiz.service.UserService;

@ShellComponent
public class ShellCommands {

    private final UserService userService;
    private final QuestioningService questioningService;

    private User currentUser;

    public ShellCommands(UserService userService, QuestioningService questioningService) {
        this.userService = userService;
        this.questioningService = questioningService;
    }

    @ShellMethod(value = "quiz", key = {"q", "quiz", "start"})
    @ShellMethodAvailability(value = "isUserIdentified")
    public void quiz() {
        questioningService.promptAllQuestions(currentUser);
        userService.printScores(currentUser);
    }

    @ShellMethod(value = "login", key = {"l", "login", "identify"})
    public void identifyUser() {
        currentUser = userService.identifyCurrentUser();
    }

    private Availability isUserIdentified() {
        return currentUser != null ? Availability.available() : Availability.unavailable("Please log in first");
    }
}
