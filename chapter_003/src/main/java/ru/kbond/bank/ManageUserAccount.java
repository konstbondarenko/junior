package ru.kbond.bank;

import java.util.*;

/**
 * Class manage users and accounts.
 * @author kbondarenko
 * @since 18.01.2018
 * @version 1
 */
public class ManageUserAccount {
    /**
     * The field storing the name user.
     * @param userAccount  TreeMap storing users and accounts.
     */
    private Map<User, List<Account>> userAccount = new TreeMap<>();
    /**
     * Method add users.
     * @param user  user to add.
     */
    public void addUser(User user) {
        this.userAccount.putIfAbsent(user, new ArrayList<>());
    }
    /**
     * Method delete users.
     * @param user  user to delete.
     */
    public void deleteUser(User user) {
        this.userAccount.remove(user);
    }
    /**
     * Method add accounts to existing users.
     * @param passport  user's passport.
     * @param account  account to add.
     */
    public void addAccountToUser(String passport, Account account) {
        for (User key : userAccount.keySet()) {
            if (key.matchPassport(passport)) {
                this.userAccount.get(key).add(account);
            }
        }
    }
    /**
     * Method delete accounts to existing users.
     * @param passport  user's passport.
     * @param account  account to delete.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        for (User key : userAccount.keySet()) {
            if (key.matchPassport(passport)) {
                this.userAccount.get(key).remove(account);
            }
        }
    }
    /**
     * Method returns a list of all user accounts.
     * @param passport  user's passport.
     * @return  list of all user accounts.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> list = new ArrayList<>(0);
        for (User key : userAccount.keySet()) {
            if (key.matchPassport(passport)) {
                list = this.userAccount.get(key);
            }
        }
        return list;
    }
    /**
     * Method checks the possibility of transferring amount
     * between accounts of the same user and between different users.
     * @param srcPassport  sender's passport.
     * @param srcRequisite  account from which the transfer will be made.
     * @param destPassport  destination user.
     * @param dstRequisite  destination user account.
     * @param amount  transfer amount.
     * @return  true if translation is possible or
     *          false if translation is not possible.
     */
    public boolean transferMoney(
            String srcPassport,
            String srcRequisite,
            String destPassport,
            String dstRequisite,
            double amount
    ) {
        List<Account> srcList;
        List<Account> dstList;
        boolean src = false;
        boolean dst = false;
        boolean transfer = false;
        srcList = getUserAccounts(srcPassport);
        dstList = getUserAccounts(destPassport);
        for (Account ac : srcList) {
            if (ac.getRequisites().equals(srcRequisite)) {
                src = true;
                srcList = Arrays.asList(ac);
            }
        }
        for (Account ac : dstList) {
            if (ac.getRequisites().equals(dstRequisite)) {
                dst = true;
                dstList = Arrays.asList(ac);
            }
        }
        if ((src && dst) && (srcList.get(0).checkTransfer(dstList.get(0), amount))) {
            transfer = true;
        }
        return transfer;
    }
}
