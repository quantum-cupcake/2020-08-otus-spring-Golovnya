package ye.golovnya.quiz.entity.user;

import lombok.Data;

@Data
public class ScoreCard {

    private int questionsAskedCount;
    private int correctResponsesCount;

    public void addCorrectResponse() {
        questionsAskedCount++;
        correctResponsesCount++;
    }

    public void addIncorrectResponse() {
        questionsAskedCount++;
    }
}
