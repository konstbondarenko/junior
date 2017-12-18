package ru.kbond.change;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 05.12.2017
 * @version 1
 */
public class CoffeeMachineTest {
    /**
     * Test checking the delivery of change.
     */
    @Test
    public void whenValueFiftyThenChangeTenFiveCoins() {
        CoffeeMachine machine = new CoffeeMachine();
        int value = 50;
        int price = 35;
        int[] expected = new int[]{10, 5};
        int[] result = machine.shortChange(value, price);
        assertThat(result, is(expected));
    }
    /**
     * Test checking the delivery of change.
     */
    @Test
    public void whenValueOneHundredThenChangeTenFiveTwoOneCoins() {
        CoffeeMachine machine = new CoffeeMachine();
        int value = 100;
        int price = 82;
        int[] expected = new int[]{10, 5, 2, 1};
        int[] result = machine.shortChange(value, price);
        assertThat(result, is(expected));
    }
}
