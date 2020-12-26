package ye.golovnya.quiz.service.impl;

import org.springframework.stereotype.Service;
import ye.golovnya.quiz.entity.user.ScoreCard;
import ye.golovnya.quiz.entity.user.User;
import ye.golovnya.quiz.service.UserService;

import java.util.Scanner;

@Service
public class UserServiceConsole implements UserService {

    private static final String EMPTY_STRING = "";
    private static final String LAST_NAME = "last name";
    private static final String FIRST_NAME = "first_name";

    private final Scanner scanner;

    public UserServiceConsole() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public User identifyCurrentUser() {
        System.out.println("Greetings!");

        String lastName = prompt(LAST_NAME);
        String firstName = prompt(FIRST_NAME);

        return new User(firstName, lastName);
    }

    @Override
    public void printScores(User user) {
        ScoreCard scoreCard = user.getScoreCard();
        System.out.printf("Quiz results: %nName: %s%nScore: %d out of %d.",
                user.getFullName(), scoreCard.getCorrectResponsesCount(), scoreCard.getQuestionsAskedCount());
    }

    private String prompt(String expectedResponse) {
        System.out.printf("Please provide your %s:%n", expectedResponse);
        String response = scanner.nextLine();
        while (response.equals(EMPTY_STRING)) {
            response = rePrompt();
        }
        return response.trim();
    }

    private String rePrompt() {
        System.out.println("That didn't work. Please try again:");
        return scanner.nextLine();
    }

}
