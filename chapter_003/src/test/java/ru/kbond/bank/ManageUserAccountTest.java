package ru.kbond.bank;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test.
 * @author kbondarenko
 * @since 18.01.2018
 * @version 1
 */
public class ManageUserAccountTest {
    /**
     * Test checking add account to user.
     */
    @Test
    public void whenAddAccountToUserThenNewAccountByKeyUser() {
        ManageUserAccount manage = new ManageUserAccount();
        User ivan = new User("Ivan", "1234");
        Account ivanAcc = new Account(100.0, "ivanAcc");
        manage.addUser(ivan);
        manage.addAccountToUser("1234", ivanAcc);
        List<Account> result = manage.getUserAccounts("1234");
        List<Account> expected = Arrays.asList(ivanAcc);
        assertThat(result, is(expected));
    }
    /**
     * Test checking delete account to user.
     */
    @Test
    public void deleteAccountFromUserThenDeleteAccountByKeyUser() {
        ManageUserAccount manage = new ManageUserAccount();
        User ivan = new User("Ivan", "1234");
        Account ivanAcc1 = new Account(100.0, "ivanAcc1");
        Account ivanAcc2 = new Account(50.0, "ivanAcc2");
        manage.addUser(ivan);
        manage.addAccountToUser("1234", ivanAcc1);
        manage.addAccountToUser("1234", ivanAcc2);
        manage.deleteAccountFromUser("1234", ivanAcc1);
        List<Account> result = manage.getUserAccounts("1234");
        List<Account> expected = Arrays.asList(ivanAcc2);
        assertThat(result, is(expected));
    }
    /**
     * Test checking transfer amount Same user account.
     */
    @Test
    public void whenTransferAmountSameUserAccountsThenTrue() {
        ManageUserAccount manage = new ManageUserAccount();
        User ivan = new User("Ivan", "1234");
        Account ivanAcc1 = new Account(100.0, "ivanAcc1");
        Account ivanAcc2 = new Account(50.0, "ivanAcc2");
        manage.addUser(ivan);
        manage.addAccountToUser("1234", ivanAcc1);
        manage.addAccountToUser("1234", ivanAcc2);
        boolean result = manage.transferMoney(
                "1234",
                "ivanAcc1",
                "1234",
                "ivanAcc2",
                50.0
        );
        boolean expected = true;
        assertThat(result, is(expected));
    }
    /**
     * Test checking transfer amount different user account.
     */
    @Test
    public void whenTransferAmountDifferentUserAccountsThenTrue() {
        ManageUserAccount manage = new ManageUserAccount();
        User ivan = new User("Ivan", "1234");
        User nikolai = new User("Nikolai", "5678");
        Account ivanAcc = new Account(100.0, "ivanAcc");
        Account nikolaiAcc = new Account(50.0, "nikolaiAcc");
        manage.addUser(ivan);
        manage.addUser(nikolai);
        manage.addAccountToUser("1234", ivanAcc);
        manage.addAccountToUser("5678", nikolaiAcc);
        boolean result = manage.transferMoney(
                "1234",
                "ivanAcc",
                "5678",
                "nikolaiAcc",
                50.0
        );
        boolean expected = true;
        assertThat(result, is(expected));
    }
    /**
     * Test checking unsuccessful transfer amount different user account.
     */
    @Test
    public void whenTransferAmountDifferentUserAccountsThenFalse() {
        ManageUserAccount manage = new ManageUserAccount();
        User ivan = new User("Ivan", "1234");
        User nikolai = new User("Nikolai", "5678");
        Account ivanAcc = new Account(100.0, "ivanAcc");
        Account nikolaiAcc = new Account(50.0, "nikolaiAcc");
        manage.addUser(ivan);
        manage.addUser(nikolai);
        manage.addAccountToUser("1234", ivanAcc);
        manage.addAccountToUser("5678", nikolaiAcc);
        boolean result = manage.transferMoney(
                "1234",
                "ivanAcc",
                "5678",
                "nikolaiAcc",
                110.0
        );
        boolean expected = false;
        assertThat(result, is(expected));
    }
}