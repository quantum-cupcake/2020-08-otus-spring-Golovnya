package ye.golovnya.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan(basePackages = {"ye.golovnya"})
public class BaseConfig {

    @Bean
    public Resource resource() {
        return new ClassPathResource("quiz-source.csv");
    }
}
