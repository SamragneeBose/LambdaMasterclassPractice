import org.junit.Test;

import java.util.Objects;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;


public class Test02_Predicate {
    /**
     * Given the predicate p1, write a predicate that
     * returns true if the string it tests is not empty.
     * This is a NOT operation on the predicate p1.
     */
    @Test
    public void predicate_1()
    {
        Predicate<String> predicate = String::isEmpty; // s->s.isEmpty();

        Predicate<String> notPredicate = predicate.negate(); // TODO

        assertThat(notPredicate.test("")).isFalse();
        assertThat(notPredicate.test("Not Empty!")).isTrue();
    }

    /**
     * Given the two predicates p1 and p2, write a predicate
     * that returns true is the string it tests is
     * neither null, neither empty.
     * This is a AND operation on the predicates p1 and p2.
     */
    @Test
    public void predicate_2()
    {
        Predicate<String> p1 = Objects::nonNull; // s -> s!=null;
        Predicate<String> p2 = s -> !s.isEmpty();

        Predicate<String> p3=p1.and(p2); // TODO

        assertThat(p3.test("")).isFalse();
        assertThat(p3.test(null)).isFalse();
        assertThat(p3.test("Not empty!")).isTrue();
    }

    /**
     * Given the two predicates p1 and p2, write a predicate that
     * returns true if the tested string is of length 4, true if
     * it starts with a J, but false if it is of length 4 and starts
     * with a J. This is a XOR operation on the predicates p1 and p2.
     */
    @Test
    public void predicate_3()
    {
        Predicate<String> p1 = s -> s.length()==4;
        Predicate<String> p2 = s -> s.startsWith("J");

        // p1.and(p2).negate();
        Predicate<String> p3 = s -> p1.test(s)^p2.test(s); // TODO

        assertThat(p3.test("True")).isTrue();
        assertThat(p3.test("Julia")).isTrue();
        assertThat(p3.test("Java")).isFalse();
    }
}
