import org.example.Calculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CalculatorTest {

    @Test
    void testAdd_validArguments_success() {
        // given:
        Calculator calculator = new Calculator();
        int a = 6;
        int b = 4;

        // when:
        int result = calculator.add(a, b);

        // then:
        assertEquals(10, result, "6 + 4 равно 10");
    }

    @Test
    void testSubtract_validArguments_success() {
        Calculator calculator = new Calculator();

        int a = 9;
        int b = 5;

        int result = calculator.subtract(a, b);

        assertEquals(4, result, "9 - 5 равно 4");

    }

    @Test
    void testMultiply_validArguments_success() {
        Calculator calculator = new Calculator();

        int a = 2;
        int b = 3;

        int result = calculator.multiply(a, b);

        assertEquals(6, result, "2 * 3 равно 6");

    }

    @Test
    void testDivide_validArguments_success() {
        Calculator calculator = new Calculator();

        int a = 8;
        int b = 2;

        double result = calculator.divide(a, b);

        assertEquals(4, result, "8 / 2 равно 4");
    }

    @Test
    void testDivide_byZero_throwsException() {
        // given:
        Calculator calculator = new Calculator();
        int a = 10;
        int b = 0;

        // when & then:
        assertThrows(IllegalArgumentException.class,
                () -> calculator.divide(a, b),
                "Калькулятор должен бросать исключение при делении на ноль");
    }
}
