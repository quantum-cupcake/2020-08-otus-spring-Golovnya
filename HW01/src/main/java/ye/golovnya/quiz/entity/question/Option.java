package ye.golovnya.quiz.entity.question;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Option {
    A('A'), B('B'), C('C'), D('D');

    private final char charValue;

    private static final Map<Character, Option> values = Arrays.stream(values())
            .collect(Collectors.toMap(Option::getCharValue, Function.identity()));

    private static final List<Option> valuesList = Arrays.stream(values())
            .collect(Collectors.toList());

    public static Option fromInt(int intValue) {
        return valuesList.get(intValue);
    }

    public static Option fromString(Character charValue) {
        return values.get(Character.toUpperCase(charValue));
    }
}
