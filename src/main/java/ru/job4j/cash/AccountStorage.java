package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        if (amount <= 0) {
            return false;
        }
        var fromAccount = accounts.get(fromId);
        var toAccount = accounts.get(toId);
        if (fromAccount == null || toAccount == null) {
            return false;
        }
        if (fromAccount.amount() < amount) {
            return false;
        }
        accounts.put(fromId, new Account(fromId, fromAccount.amount() - amount));
        accounts.put(toId, new Account(toId, toAccount.amount() + amount));
        return true;
    }
}
