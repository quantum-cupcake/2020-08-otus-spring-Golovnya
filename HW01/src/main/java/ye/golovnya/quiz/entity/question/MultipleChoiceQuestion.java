package ye.golovnya.quiz.entity.question;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

@EqualsAndHashCode
public class MultipleChoiceQuestion implements Question {

    private static final int MAX_OPTIONS_COUNT = Option.values().length;

    private final String questionString;
    private final Map<Option, String> optionsMap;
    @Getter
    private final Option correctOption;

    @JsonCreator
    public MultipleChoiceQuestion(@JsonProperty("questionString") String questionString,
                                  @JsonProperty("correctOption") int correctOption,
                                  @JsonProperty("options") String... options) {
        if (options.length > MAX_OPTIONS_COUNT) {
            throw new IllegalArgumentException(
                    String.format("Передано слишком много вариантов ответа, поддерживается до %d вариантов", MAX_OPTIONS_COUNT)
            );
        }

        this.questionString = questionString;
        this.correctOption = Option.fromInt(correctOption);

        var tempOptionsMap = new EnumMap<Option, String>(Option.class);
        for (int i = 0; i < options.length; i++) {
            tempOptionsMap.put(Option.fromInt(i), options[i]);
        }
        this.optionsMap = Collections.unmodifiableMap(tempOptionsMap);
    }

    @Override
    public String getQuestionString() {
        return toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(questionString);
        stringBuilder.append(System.lineSeparator());
        optionsMap.keySet().stream().sorted().forEach(
                option -> stringBuilder.append(option.toString()).append(" ").append(optionsMap.get(option)).append(System.lineSeparator())
        );
        return stringBuilder.toString();
    }
}
