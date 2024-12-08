package ru.job4j.deadlock;

public class BankTransactionDeadlock {
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
            synchronized (from) {
                System.out.println(Thread.currentThread().getName() + ": Заблокировал счет " + from);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (to) {
                    System.out.println(Thread.currentThread().getName() + ": Заблокировал счет " + to);
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

