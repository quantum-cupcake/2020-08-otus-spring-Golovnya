package ye.golovnya.quiz.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import ye.golovnya.quiz.entity.question.MultipleChoiceQuestion;
import ye.golovnya.quiz.helpers.impl.MultipleChoiceQuestionParser;

@Configuration
@ComponentScan(basePackages = {"ye.golovnya"})
public class BaseConfig {

    @Bean
    @Qualifier("questionParser")
    public MultipleChoiceQuestionParser multipleChoiceQuestionParser(@Value("${quiz.csv}") String classPathResourcePath) {
        var csvSource = new ClassPathResource(classPathResourcePath);
        return new MultipleChoiceQuestionParser(csvSource);
    }
}
