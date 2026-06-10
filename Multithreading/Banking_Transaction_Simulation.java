/*
Create a Java program to simulate a bank system with a shared account balance.

Task:
Initial account balance = 10,000
Create two threads:
Thread 1 → performs 5 withdrawals (random amounts between 500–2000)
Thread 2 → performs 5 deposits (random amounts between 500–2000)
Requirements:
Both threads should operate on the same account balance
Print updated balance after every transaction
Ensure balance never goes negative
Hint:

Think step-by-step:

Shared resource = balance variable
Use synchronization concept (important idea: avoid race condition)
Each thread modifies same data
Control access carefully
*/
package JavaIgniteDay13;
import java.util.Random;

class BankAccount {
    private int balance = 10000;

    // Synchronized withdrawal
    public synchronized void withdraw(int amount) {

        if (balance >= amount) {
            balance -= amount;
            System.out.println(
                Thread.currentThread().getName()+ " withdrew Rs." + amount+ " | Balance: Rs." + balance);
        } else {
            System.out.println(
                Thread.currentThread().getName()+ " tried to withdraw Rs." + amount+ " | Insufficient Balance");
        }
    }

    // Synchronized deposit
    public synchronized void deposit(int amount) {

        balance += amount;

        System.out.println(
            Thread.currentThread().getName()+ " deposited Rs." + amount+ " | Balance: Rs." + balance);
    }

    public int getBalance() {
        return balance;
    }
}

// Withdrawal Thread
class WithdrawThread extends Thread {

    BankAccount account;
    Random random = new Random();

    WithdrawThread(BankAccount account) {
        this.account = account;
    }

    public void run() {

        for (int i = 1; i <= 5; i++) {

            int amount = random.nextInt(1501) + 500; // 500-2000

            account.withdraw(amount);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

// Deposit Thread
class DepositThread extends Thread {

    BankAccount account;
    Random random = new Random();

    DepositThread(BankAccount account) {
        this.account = account;
    }

    public void run() {

        for (int i = 1; i <= 5; i++) {

            int amount = random.nextInt(1501) + 500; // 500-2000

            account.deposit(amount);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class Banking_Transaction {
	public static void main(String[] args) {
    	// TODO Auto-generated method stub
        BankAccount account = new BankAccount();

        WithdrawThread t1 = new WithdrawThread(account);
        DepositThread t2 = new DepositThread(account);

        t1.setName("Withdrawal Thread\n");
        t2.setName("Deposit Thread\n");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("\nFinal Balance: Rs." + account.getBalance());
    }
}
