package ye.golovnya.quiz.entity.user;

import lombok.Data;

@Data
public class User {

    private String firstName;
    private String lastName;
    private String fullName;
    private ScoreCard scoreCard;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.scoreCard = new ScoreCard();
    }
}
