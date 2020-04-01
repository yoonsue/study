package interpreter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;

class InterpreterTest {

    private static int failCount = 0;

    private static Stream arguments() {
        return Stream.of(
                Arguments.of("ab", "cab", new String[]{"ab"})
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void check(String regex, String input, String[] expected) {
        System.out.println("Fail : " + failCount);
        List<String> result = new ArrayList<String>();
        MyPattern p = MyPattern.compile(regex);
        MyMatcher m = p.matcher(input);
        while (m.find()) {
            result.add(m.group());
        }
        if (!Arrays.asList(expected).equals(result))
            failCount++;
            System.out.println("Fail : " + failCount);
        System.out.println("Fail : " + failCount);
    }
}