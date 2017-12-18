package ru.kbond.change;

/**
 * Class CoffeMachine - calculates the change.
 * @author kbondarenko
 * @since 27.11.2017
 * @version 1
 */
public class CoffeeMachine {
    /**
     * The method that performs the calculation of the change.
     * Issue coins in nominal value 1, 2, 5, 10.
     * @return - the smallest number of coins.
     */
    public int[] shortChange(int value, int price) {
        int[] totalCoinsChange;
        int calcOfChanges = value - price;
        boolean noChange = false;
        int tenCoin = 0;
        int fiveCoin = 0;
        int twoCoin = 0;
        int oneCoin = 0;
        if (calcOfChanges == 0) {
            noChange = true;
        }
        if (calcOfChanges >= 10) {
            tenCoin = calcOfChanges / 10;
            calcOfChanges %= 10;
        }
        if (calcOfChanges >= 5) {
            fiveCoin = calcOfChanges / 5;
            calcOfChanges %= 5;
        }
        if (calcOfChanges >= 2) {
            twoCoin = calcOfChanges / 2;
            calcOfChanges %= 2;
        }
        if (calcOfChanges == 1) {
            oneCoin = calcOfChanges;
        }
        totalCoinsChange = new int[tenCoin + fiveCoin + twoCoin + oneCoin];
        for (int i = 0; i < totalCoinsChange.length - (fiveCoin + twoCoin + oneCoin); i++) {
            totalCoinsChange[i] = 10;
        }
        for (int i = tenCoin; i < totalCoinsChange.length - (twoCoin + oneCoin); i++) {
            totalCoinsChange[i] = 5;
        }
        for (int i = tenCoin + fiveCoin; i < totalCoinsChange.length - (oneCoin); i++) {
            totalCoinsChange[i] = 2;
        }
        for (int i = tenCoin + fiveCoin + twoCoin; i < totalCoinsChange.length; i++) {
            totalCoinsChange[i] = 1;
        }
        return noChange ? new int[]{0} : totalCoinsChange;
    }
}
