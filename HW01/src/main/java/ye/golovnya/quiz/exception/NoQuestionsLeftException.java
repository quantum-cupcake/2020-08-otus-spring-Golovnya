package ye.golovnya.quiz.exception;

public class NoQuestionsLeftException extends RuntimeException {
    public NoQuestionsLeftException() {
        super("No more questions left to ask in current questionnaire");
    }
}
