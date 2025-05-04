import model.InsuranceRequest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsuranceRequestTest {


    @ParameterizedTest
    @MethodSource("invalidAge")
    void shouldThrowExceptionForInvalidAge(int age) {
        assertThrows(IllegalArgumentException.class, () -> {
            new InsuranceRequest(1, age, 500, false, true);
        });
    }


    static Stream<Integer> invalidAge() {
        return Stream.of(
                -1,     // zu niedrig
                0,      // untere Grenze, evtl. ungültig
                1200    // deutlich zu hoch
        );
    }

    static Stream<Integer> validAge() {
        return Stream.of(
                1,
                22,
                99
        );
    }
}
