package ru.job4j.deadlock;

public class BankTransactionDeadlockSolution {
    static class Account {
        private int balance;

        public Account(int balance) {
            this.balance = balance;
        }

        public int getBalance() {
            return balance;
        }

        public void withdraw(int amount) {
            balance -= amount;
        }

        public void deposit(int amount) {
            balance += amount;
        }
    }

    static class Transaction {
        public static void transfer(Account from, Account to, int amount) {
            Account firstLock = from.hashCode() < to.hashCode() ? from : to;
            Account secondLock = from.hashCode() < to.hashCode() ? to : from;
            synchronized (firstLock) {
                System.out.println(Thread.currentThread().getName() + ": Заблокировал счет " + firstLock);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (secondLock) {
                    System.out.println(Thread.currentThread().getName() + ": Заблокировал счет " + secondLock);
                    if (from.getBalance() >= amount) {
                        from.withdraw(amount);
                        to.deposit(amount);
                        System.out.println(Thread.currentThread().getName() + ": Перевел " + amount);
                    } else {
                        System.out.println(Thread.currentThread().getName() + ": Недостаточно средств");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Account account1 = new Account(1000);
        Account account2 = new Account(2000);
        Thread thread1 = new Thread(() -> Transaction.transfer(account1, account2, 500), "Thread 1");
        Thread thread2 = new Thread(() -> Transaction.transfer(account2, account1, 300), "Thread 2");
        thread1.start();
        thread2.start();
    }
}

