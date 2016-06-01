package JUnit201604;

/**
 * Created by hadoop on 4/2/16.
 */
public interface AccountManager {
    Account findAccountForUser(String userId);
    void updateAccount(Account account);
}
