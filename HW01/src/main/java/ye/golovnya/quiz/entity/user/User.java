package ye.golovnya.quiz.entity.user;

import lombok.Value;

@Value
public class User {

    String firstName;
    String lastName;
    String fullName;
    ScoreCard scoreCard;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.scoreCard = new ScoreCard();
    }
}
