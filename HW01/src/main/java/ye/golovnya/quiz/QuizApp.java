package ye.golovnya.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ye.golovnya.quiz.service.QuestioningService;
import ye.golovnya.quiz.service.UserService;

@SpringBootApplication
public class QuizApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(QuizApp.class, args);

        var userService = applicationContext.getBean(UserService.class);
        var user = userService.identifyCurrentUser();
        var questioningService = applicationContext.getBean(QuestioningService.class);
        questioningService.promptAllQuestions(user);
        userService.printScores(user);

        System.exit(0);
    }
}
