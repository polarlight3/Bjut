package JUnit201604;

/**
 * Created by hadoop on 4/2/16.
 */
public class Test {
//    private AccountManager accountManager;
    public static void main(String[] args){
        AccountService accountService = new AccountService();
        accountService.transfer("1", "2", 23);

    }


}
