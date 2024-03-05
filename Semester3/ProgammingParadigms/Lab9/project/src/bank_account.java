public class bank_account {
    private int balance;
    private int account_number;

    public bank_account(int account_number, int initial_balance) {
        this.balance = initial_balance;
        this.account_number = account_number;
    }

    public synchronized int get_balance() {
        return this.balance;
    }

    public synchronized int get_account_number() {
        return this.account_number;
    }

    public synchronized void deposit(int amount) {
        this.balance += amount;
    }

    public synchronized void withdraw(int amount) {
        this.balance -= amount;
    }

    public synchronized void transfer(bank_account other, int amount) {
        if (this.balance >= amount) {
            this.withdraw(amount);
            other.deposit(amount);
            System.out.println("Transferred " + amount + " from account " + this.account_number + " to account " + other.account_number + ".");
        } else {
            System.out.println("Insufficient funds in account " + this.account_number + " for transfer.");
        }
    }
}
