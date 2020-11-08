package ye.golovnya.quiz.entity.question;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MultipleChoiceQuestion implements Question {

    private static final int MAX_OPTIONS_COUNT = Option.values().length;

    private final String questionString;
    private final Map<Option, String> optionsMap;
    private final Option correctOption; // TODO это примерная модель данных на будущее, в бою я бы такое не оставил, но уже в ДЗ2 нужно будет проверять ответы

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
        this.correctOption = Option.values.get(correctOption);

        var tempOptionsMap = new EnumMap<Option, String>(Option.class);
        for (int i = 0; i < options.length; i++) {
            tempOptionsMap.put(Option.fromInt(i), options[i]);
        }
        this.optionsMap = Collections.unmodifiableMap(tempOptionsMap);
    }

    @AllArgsConstructor
    @Getter
    enum Option {
        A, B, C, D;

        private static final List<Option> values = Arrays.stream(values())
                .collect(Collectors.toList());

        public static Option fromInt(int intValue) {
            return values.get(intValue);
        }
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
