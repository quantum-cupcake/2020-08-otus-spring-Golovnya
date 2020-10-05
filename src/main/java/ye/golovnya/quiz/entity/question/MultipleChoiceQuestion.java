package ye.golovnya.quiz.entity.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

@Value
public class MultipleChoiceQuestion implements Question {

    private static int maxOptionsCount = Option.values().length;

    String questionString;
    Map<Option, String> optionsMap;
    Option correctOption;

    public MultipleChoiceQuestion(String questionString, int correctOption, String... options) {
        if (options.length > maxOptionsCount) {
            throw new IllegalArgumentException(
                    String.format("Передано слишком много вариантов ответа, поддерживается до %d вариантов", maxOptionsCount)
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

    @AllArgsConstructor
    @Getter
    enum Option {
        A(0), B(1), C(2), D(3);

        private final int intValue;
        private static final Map<Integer, Option> valuesMap = Arrays.stream(values())
                .collect(Collectors.toMap(Option::getIntValue, x -> x));

        public static Option fromInt(int intValue) {
            return valuesMap.get(intValue);
        }
    }
}
