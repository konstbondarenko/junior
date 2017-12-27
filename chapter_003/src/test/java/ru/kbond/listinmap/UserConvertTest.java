package ru.kbond.listinmap;

import org.junit.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test.
 * @author kbondarenko
 * @since 27.12.2017
 * @version 1
 */
public class UserConvertTest {
    /**
     * Test conversion of List<> to HashMap<> with addition id.
     */
    @Test
    public void whenListTomSamThenHashMapIdOneTomIdTwoSam() {
        User tom = new User(1, "Tom", "Moscow");
        User sam = new User(2, "Sam", "Minsk");
        List<User> list = Arrays.asList(tom, sam);
        UserConvert convert = new UserConvert();
        HashMap<Integer, User> result = convert.process(list);
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(1, tom);
        expected.put(2, sam);
        assertThat(result, is(expected));
    }
}