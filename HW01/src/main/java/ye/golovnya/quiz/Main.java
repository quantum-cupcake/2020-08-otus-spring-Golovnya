package ye.golovnya.quiz;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ye.golovnya.quiz.service.QuestioningService;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        applicationContext.refresh();
        var questioningService = applicationContext.getBean("questioningService", QuestioningService.class);
        questioningService.promptAllQuestions();
        System.exit(0);
    }
}
