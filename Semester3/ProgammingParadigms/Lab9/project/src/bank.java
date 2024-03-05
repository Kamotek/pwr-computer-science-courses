import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Random;

public class bank {
    private List<bank_account> accounts;
    private int initial_total_balance;
    private int initial_number_of_accounts;
    private int final_total_balance;
    private int final_number_of_accounts;

    public bank() {
        this.accounts = new CopyOnWriteArrayList<>();
        this.initial_total_balance = 0;
        this.initial_number_of_accounts = 0;
        this.final_total_balance = 0;
        this.final_number_of_accounts = 0;
    }

    public void add_account(bank_account account) {
        this.accounts.add(account);
    }

    public void transfer(int from_account_number, int to_account_number, int amount) {
        bank_account from_account = this.get_account(from_account_number);
        bank_account to_account = this.get_account(to_account_number);

        if (from_account != null && to_account != null) {
            from_account.transfer(to_account, amount);
        }
    }

    public bank_account get_account(int account_number) {
        for (bank_account account : this.accounts) {
            if (account.get_account_number() == account_number) {
                return account;
            }
        }
        return null;
    }

    public int get_total_balance() {
        int total_balance = 0;
        for (bank_account account : this.accounts) {
            total_balance += account.get_balance();
        }
        return total_balance;
    }

    public int get_number_of_accounts() {
        return this.accounts.size();
    }

    public void bank_init() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int initial_balance = random.nextInt(1000);
            bank_account account = new bank_account(i, initial_balance);
            add_account(account);
            initial_total_balance += initial_balance;
            initial_number_of_accounts++;
        }
    }

    public void run_bank() {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                Random random = new Random();
                int from_account_number = random.nextInt(10);
                int to_account_number = random.nextInt(10);
                int amount = random.nextInt(accounts.get(from_account_number).get_balance());

                synchronized (this) {
                    transfer(from_account_number, to_account_number, amount);
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        final_total_balance = get_total_balance();
        final_number_of_accounts = get_number_of_accounts();
    }

    public void print_bank() {
        System.out.println("Initial total balance: " + initial_total_balance);
        System.out.println("Initial number of accounts: " + initial_number_of_accounts);
        System.out.println("Final total balance: " + final_total_balance);
        System.out.println("Final number of accounts: " + final_number_of_accounts);
    }
}
