package JUnit201604;

/**
 * Created by hadoop on 4/2/16.
 */
public class Account {
    private String accountId;
    private long balance;
    public Account(String accountId, long initialBalance){
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    public void debit(long amount){
        this.balance -= amount;
    }

    public void credit(long amount){
        this.balance += amount;
    }

    public long getBalance(){
        return this.balance;
    }
}
