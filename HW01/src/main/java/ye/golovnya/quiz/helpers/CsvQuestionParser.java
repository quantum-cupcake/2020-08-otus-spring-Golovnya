package ye.golovnya.quiz.helpers;

import ye.golovnya.quiz.entity.question.Question;

import java.util.List;

public interface CsvQuestionParser<T extends Question> {

    List<T> parseQuestions();
}
