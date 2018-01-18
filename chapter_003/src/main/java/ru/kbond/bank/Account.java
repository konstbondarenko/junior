package ru.kbond.bank;

import java.util.Objects;

/**
 * Class storing account data from user.
 * @author kbondarenko
 * @since 18.01.2018
 * @version 1
 */
public class Account {
    /**
     * The field storing the value from account.
     * @param value  account value.
     */
    private double value;
    /**
     * The field storing the account requisites.
     * @param requisites  account requisites.
     */
    private final String requisites;
    /**
     * Constructor.
     * @param value  account value.
     * @param requisites  account requisites.
     */
    public Account(double value, final String requisites) {
        this.value = value;
        this.requisites = requisites;
    }
    /**
     * Getter.
     * @return getValue  account value.
     */
    public double getValue() {
        return value;
    }
    /**
     * Getter.
     * @return getRequisites  account requisites.
     */
    public String getRequisites() {
        return requisites;
    }
    /**
     * Method checks the possibility of making a transfer.
     * @param destination  account from which the transfer will be made.
     * @param amount  transfer amount.
     * @return  true if translation is possible or
     *          false if translation is not possible.
     */
    public boolean checkTransfer(Account destination, double amount) {
        boolean success = false;
        if (amount > 0 && amount <= this.value) {
            success = true;
        }
        return success;
    }
    /**
     * toString.
     * @return - string representation of the object.
     */
    @Override
    public String toString() {
        return "Account{"
                +
                "value=" + value
                +
                ", requisites='" + requisites + '\''
                +
                '}';
    }
    /**
     * Equals.
     * @return  override equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisites, account.requisites);
    }
    /**
     * HashCode.
     * @return  override hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }
}
