package ye.golovnya.quiz;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ye.golovnya.quiz.config.BaseConfig;
import ye.golovnya.quiz.service.QuestioningService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BaseConfig.class);

        var questioningService = applicationContext.getBean("questioningService", QuestioningService.class);
        questioningService.promptAllQuestions();
        System.exit(0);
    }
}
