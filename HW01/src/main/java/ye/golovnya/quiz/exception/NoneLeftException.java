package ye.golovnya.quiz.exception;

public class NoneLeftException extends QuestionException {
    public NoneLeftException() {
        super("No more questions left to ask in current questionnaire");
    }
}
